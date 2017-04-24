package com.knoldus

import java.io.File

import scala.collection.JavaConversions._

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell

trait ExcelReader {

  def inputFeed(fileName: String,
      sheetName: String,
      columnSize: Int,
      readFirstRow: Boolean = true): List[List[Cell]] = {
    import java.io.FileInputStream
    val file = new File(fileName)
    require(file.exists())
    val fileInputStream = new FileInputStream(file)
    val workbook = new HSSFWorkbook(fileInputStream)
    val sheet = workbook.getSheet(sheetName)
    require(sheet != null)
    val listOfRow = sheet.rowIterator().toIterable.toList.map {
      row => row.cellIterator().toIterable.toList
    }
    if (readFirstRow) {
      listOfRow
    } else {
      listOfRow.tail
    }
  }

}

object ExcelReader extends ExcelReader
