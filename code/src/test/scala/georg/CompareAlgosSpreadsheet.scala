package georg

import java.io.FileOutputStream

import ch.ethz.math.ifor.rectanglePacking.Algorithms.{Greedy, TilePacking}
import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.Instance
import ch.ethz.math.ifor.rectanglePacking.inf
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object CompareAlgosSpreadsheet extends App {

  def runAllAlgorithms(instance: Instance): Vector[String] = {
    Vector(
      instance.anchors.toString,
      Greedy.run(instance.normSort(1)).objectiveValue.toString,
      Greedy.run(instance.normSort(inf)).objectiveValue.toString,
      TilePacking.run(instance.normSort(1)).objectiveValue.toString,
      TilePacking.run(instance.normSort(inf)).objectiveValue.toString
      // opt
    )
  }

  // manually construct instances
  // val instance1 = Instance.standardSquare(List(Anchor.pointToAnchor(Point.origin)))
  // val instance2 = Instance.standardSquare(List(Anchor.pointToAnchor(Point.origin),Anchor(Vector(0.3,0.8)),Anchor(Vector(0.8,0.3))))
  // val instances: Vector[Instance] = Vector(instance1,instance2)

  // construct random instances
  val numberOfAnchors = 10
  val numberOfInstances = 20
  val instances: Vector[Instance] = (for (_ <- 1 to numberOfInstances) yield Instance.createRandomUnitSquareInstance(numberOfAnchors)).toVector

  val data: Vector[Vector[String]] = instances.zipWithIndex.map{case (instance, index) => { {println(s"solving instance ${index+1} out of $numberOfInstances")}; runAllAlgorithms(instance)}} //this gives a Vector of Vector[String] that can be filled into the spreadsheet

  writeSpreadsheet(data)


  def writeSpreadsheet(data: Vector[Vector[String]]): Unit = {
    val workbook = new XSSFWorkbook()
    val sheet = workbook.createSheet("Results")

    //create header row
    val headerRow = sheet.createRow(0)
    val columns = List(
      "Anchors",
      "l1Greedy",
      "linfGreedy",
      "l1TilePacking",
      "linfTilePacking" //,
      /*"opt"
      */
    )

    for (i <- columns.indices) {
      val cell = headerRow.createCell(i)
      cell.setCellValue(columns(i))
    }

    val numberOfHeaderRows = 1

    data.indices.foreach {
      i => {
        val currentRow = sheet.createRow(i + numberOfHeaderRows)
        data(i).indices.foreach { j =>
          currentRow.createCell(j).setCellValue(data(i)(j))
        }
      }
    }

    val filename = System.getProperty("user.dir") + "/outputFiles/" + "OutputTest.xlsx"

    val fileOut = new FileOutputStream(filename)
    workbook.write(fileOut)

    fileOut.close()
    workbook.close()

    println("====================")
    println(" XLSX file written to:")
    println(filename)
    println("====================")

  }


}