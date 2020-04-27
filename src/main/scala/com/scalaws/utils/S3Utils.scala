package com.scalaws.utils

import java.io.File

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import com.scalaws.configs.s3.S3Config
import org.slf4j.LoggerFactory


case class S3Utils(s3Config: S3Config) extends Logger  {

  private val credentials = new ProfileCredentialsProvider(s3Config.profile)
  protected val s3Client = AmazonS3ClientBuilder.standard()
    .withRegion(s3Config.regions)
    .withCredentials(credentials)
    .build()

  protected val tm = TransferManagerBuilder.standard()
    .withS3Client(s3Client)
    .build()

  /**
   *
   * @param dstBucket The destination bucket + can include directory path
   * @param files
   * @param dstName If you want to rename the filename or directory
   */
  def uploadFiles(dstBucket: String, files: File, dstName: Option[String] = None) = {
    try {
      if (files.isDirectory) {
        files.listFiles().foreach { file =>
          s3Client.putObject(s"$dstBucket/${files.getName}", file.getName, file)
        }
      } else {
        s3Client.putObject(dstBucket, dstName.getOrElse(""), files)
      }
    } catch {
      case e: Exception => logger.error("Error in S3Utils upload local file", e)
    }
  }

  def uploadObjects(dstBucket: String, dstDir: String, key: String, content: String) = {
    try {
      s3Client.putObject(s"$dstBucket/$dstDir", key, content)
    } catch {
      case e: Exception => logger.error("Error in S3Utils upload objects", e)
    }
  }

}


object S3Utils {

}
