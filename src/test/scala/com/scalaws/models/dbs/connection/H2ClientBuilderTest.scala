package com.scalaws.models.dbs.connection

import com.scalaws.configs.ConfigTest
import com.scalaws.models.dbs.H2DatabaseCreatorTest
import com.scalaws.models.dbs.crud.UserRDSCRUD
import com.scalaws.models.{RDSType, Users}
import io.getquill.SnakeCase
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * Careful the order tests are important
 */
class H2ClientBuilderTest extends AsyncFlatSpec with BeforeAndAfterAll with Matchers with ConfigTest {

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    H2DatabaseCreatorTest.createTables()
  }

  override protected def afterAll(): Unit = {
    super.afterAll()
    H2DatabaseCreatorTest.dropTables()
  }

  val userRDSCRUD = UserRDSCRUD(config, RDSType.H2, SnakeCase)

  val expectedUsers = List(
    Users(1,"foo","bar"),
    Users(2,"hello","world")
  )

  val newUser = Users(3,"insert","insert")
  val updateUser = Users(3, "update", "update")


  it should "get all users data" in {
    val users = userRDSCRUD.find()
    users.map { us => assert(us === expectedUsers)}
  }

  it should "insert new user" in {
    val insert = userRDSCRUD.insert(newUser)
    insert.map { l =>
      assert(l == 1)
    }
  }

  it should "get all users after insert" in {
    val users = userRDSCRUD.find()
    users.map { us => assert(us === (expectedUsers.::(newUser).sortBy(_.id)))}
  }

  it should "update one user" in {
    val update = userRDSCRUD.update(updateUser)
    update.map { u =>
      assert(u == 1)
    }
  }

  it should "get all users with updated data" in {
    val users = userRDSCRUD.find()
    users.map { us => assert(us === (expectedUsers.::(updateUser).sortBy(_.id)))}
  }

  it should "delete one user" in {
    val delete = userRDSCRUD.delete(newUser)
    delete.map { d => assert(d == 1) }
  }

  it should "get users after deleted data" in {
    val users = userRDSCRUD.find()
    users.map { us => assert(us === expectedUsers)}
  }

}
