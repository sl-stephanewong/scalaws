package com.scalaws.configs.apis

import com.scalaws.configs.ConfigTest
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CommonApiConfigBuilderTest extends AnyFlatSpec with Matchers with ConfigTest {

  it should "get common minimal config" in {
    val commonConfig = new CommonApiConfigBuilder(config, "tmdb")
    commonConfig.url should be ("tmdburl")
  }

}
