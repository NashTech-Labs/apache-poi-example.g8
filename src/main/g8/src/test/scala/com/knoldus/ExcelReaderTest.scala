package com.knoldus

import org.scalatest.FunSuite

class ExcelReaderTest extends FunSuite with ExcelReader {

  test("Read Excel File with header") {
    assert(
      inputFeed("/home/prabhat/my-work/apache-poi-example/src/test/resources/test.xls", "sheet1", 3)
        .size == 3)
  }

  test("Read Excel File without header") {
    assert(
      inputFeed("/home/prabhat/my-work/apache-poi-example/src/test/resources/test.xls", "sheet1", 3, readFirstRow = false)
        .size == 2)
  }

  test("Read Excel File with invalid path") {
    intercept[IllegalArgumentException] {
      inputFeed("/home/prabhat/my-work/apache-poi-example/src/test/resources/test1.xls",
        "sheet1",
        3)
    }
  }

  test("Read Excel File with invalid sheet") {
    intercept[IllegalArgumentException] {
      inputFeed("/home/prabhat/my-work/apache-poi-example/src/test/resources/test.xls",
        "invalidsheet",
        3)
    }
  }

}
