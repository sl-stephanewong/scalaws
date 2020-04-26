package com.scalaws.utils

import java.io.File

import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import com.scalaws.configs.S3Config

case class S3Utils(s3Config: S3Config) {


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
   * @param pathFile
   * @param dstFileName If you want to rename the filename
   */
  def uploadFile(dstBucket: String, pathFile: String, dstFileName: Option[String] = None) = {
    val upload = tm.upload(dstBucket, dstFileName.getOrElse(""), new File(pathFile))
    upload.waitForCompletion()
  }

}


object S3Utils {

}
