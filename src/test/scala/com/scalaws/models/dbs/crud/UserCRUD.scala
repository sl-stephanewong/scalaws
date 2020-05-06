package com.scalaws.models.dbs.crud

import com.scalaws.models.RDSType.RDSType
import com.scalaws.models.Users
import com.typesafe.config.Config
import io.getquill.NamingStrategy

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

case class UserRDSCRUD[N <: NamingStrategy](config: Config, rdsType: RDSType, namingStrategy: N)
  extends RDSCRUD[Users,N](config, rdsType, namingStrategy) {

  /**
   *
   * @return list of records
   */
  override def find(): Future[List[Users]] = {
    Future {
      val ctx = client.getConnection
      import ctx._
      try {
        run {
          query[Users]
        }
      } finally {
        ctx.close()
      }
    }
  }

  /**
   *
   * @param record
   * @return a 'primitive' value or nothing after insert data in database
   */
  override def insert(record: Users): Future[Long] = {
    Future {
      val ctx = client.getConnection
      import ctx._
      try {
        run {
          query[Users].insert(lift(record))
        }
      } finally {
        ctx.close()
      }
    }
  }

  /**
   *
   * @param records
   * @return a 'primitive' value or nothing after insert data in database
   */
  override def insert(records: Seq[Users]): Future[_ <: Any] = {
    Future {
      val ctx = client.getConnection
      import ctx._
      try {
        run {
          quote {
            liftQuery(records).foreach(u => query[Users].insert(u))
          }
        }
      } finally {
        ctx.close()
      }
    }
  }

  /**
   *
   * @param record
   * @return a 'primitive' value or nothing after update data in database
   */
  override def update(record: Users): Future[Long] = {
    Future {
      val ctx = client.getConnection
      import ctx._
      try {
        run {
          quote {
            query[Users].filter(_.id == lift(record.id)).update(lift(record))
          }
        }
      } finally {
        ctx.close
      }
    }
  }

  /**
   *
   * @param records
   * @return a 'primitive' value or nothing after update data in database
   */
  override def update(records: Seq[Users]): Future[_ <: Any] = {???
    /*Future {
      run {
        quote {
          liftQuery(records).foreach { u =>
            query[Users].filter(_.id == lift(u.id)).update(lift(u))
          }
        }
      }
    }*/
  }

  /**
   *
   * @param record
   * @return a 'primitive' value after deleted user
   */
  override def delete(record: Users): Future[Long] = {
    Future {
      val ctx = client.getConnection
      import ctx._
      try {
        run {
          quote {
            query[Users].filter(_.id == lift(record.id)).delete
          }
        }
      } finally {
        ctx.close()
      }
    }
  }
}
