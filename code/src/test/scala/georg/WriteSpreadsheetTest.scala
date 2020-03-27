package georg


import java.io.FileOutputStream
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object WriteSpreadsheetTest extends App {

val data = Vector(
Vector("anchors1", "1.0", "0.8", "0.6", "0.2", "0.0"),
Vector("anchors2", "1.0", "1.8", "1.6", "1.2", "1.0"),
Vector("anchors3", "0.2", "0.4", "0.2", "0.4", "0.2")
)


val workbook = new XSSFWorkbook()
val sheet = workbook.createSheet("name of sheet")


//create header row
val headerRow = sheet.createRow(0)
val columns = List(
"Anchors",
"l1Greedy",
"linfGreedy",
"l1TilePacking",
"linfTilePacking",
"opt"
)

for (i<- columns.indices) {
val cell = headerRow.createCell(i)
cell.setCellValue(columns(i))
}

var numberOfHeaderRows = 1

data.indices.foreach{ i=> {
val currentRow = sheet.createRow(i+numberOfHeaderRows)
data(i).indices.foreach{ j=>
currentRow.createCell(j).setCellValue(data(i)(j))
}
}
}


    val filename = System.getProperty("user.dir") + "/outputFiles/"+"writeSpreadsheetTest.xlsx"

    val fileOut = new FileOutputStream(filename)
    workbook.write(fileOut)

    fileOut.close()
    workbook.close()

    println("====================")
    println(" XLSX file written to:")
    println(filename)
    println("====================")








}
