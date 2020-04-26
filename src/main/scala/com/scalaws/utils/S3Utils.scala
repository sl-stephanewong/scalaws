package com.scalaws.utils

import java.io.File

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import com.scalaws.configs.S3Config
import org.slf4j.LoggerFactory


case class S3Utils(s3Config: S3Config)  {

  val logger = LoggerFactory.getLogger(getClass)
  private val credentials = new ProfileCredentialsProvider(s3Config.profile)
  private val s3Client = AmazonS3ClientBuilder.standard()
    .withRegion(s3Config.regions)
    .withCredentials(credentials)
    .build()

  private val tm = TransferManagerBuilder.standard()
    .withS3Client(s3Client)
    .build()

  /**
   *
   * @param dstBucket The destination bucket + can include directory path
   * @param path
   * @param dstName If you want to rename the filename or directory
   */
  def uploadFromLocalFiles(dstBucket: String, path: String, dstName: Option[String] = None) = {
    try {
      val files = new File(path)

      if (files.isDirectory) {
        files.listFiles().foreach { file =>
          val upload = tm.upload(s"$dstBucket/${files.getName}", file.getName, file)
          upload.waitForCompletion()
        }
      } else {
        val upload = tm.upload(dstBucket, dstName.getOrElse(""), files)
        upload.waitForCompletion()
      }
    } catch {
      case e: Exception => logger.error("Error in S3Utils upload local file", e)
    }
  }


}


object S3Utils {

}
