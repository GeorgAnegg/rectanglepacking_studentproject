package federica

import java.io.FileOutputStream
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object SpreadsheetTest extends App {

  val testname = "SpreadsheetTest2"

  val outputs = Vector(
    Vector("anchors1", "1.0", "0.8", "0.6"),
    Vector("anchors2", "1.0", "1.8", "1.6"),
    Vector("anchors3", "0.2", "0.4", "0.2")
  )

  def writeOutputToSheet(outputs: Vector[Vector[String]], workbook: XSSFWorkbook): Unit = {
    val sheet = workbook.createSheet(testname)
    //create header Row
    val headerRow = sheet.createRow(0)
    val columns = List("AnchorsList", "GreedyArea", "TilePackingArea", "BruteForceArea")
    for (i <- columns.indices) {
      val cell = headerRow.createCell(i)
      cell.setCellValue(columns(i))
    }
    //fill in parameters
    val numberHeaderRows = 1
    outputs.indices.foreach {
      i => {
        val row = sheet.createRow(numberHeaderRows + i)
        outputs(i).indices.foreach {
          j => row.createCell(j).setCellValue(outputs(i)(j))
        }
      }
    }
  }

  def writeToFile(): Unit = {
    val filename = System.getProperty("user.dir") + "/outputFiles/" + testname + ".xlsx"
    val workbook = new XSSFWorkbook()
    writeOutputToSheet(outputs, workbook)
    val fileOut = new FileOutputStream(filename)
    workbook.write(fileOut)
    fileOut.close()
    workbook.close()

    println("====================")
    println(" XLSX file written to:")
    println(filename)
    println("====================")
  }

  writeToFile()

 }