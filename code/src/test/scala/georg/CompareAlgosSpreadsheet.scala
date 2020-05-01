package georg

import java.io.FileOutputStream

import ch.ethz.math.ifor.rectanglePacking.algorithms.{BruteForce, Greedy, TilePacking}
import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.inf
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object CompareAlgosSpreadsheet extends App {

  val spreadsheetName: String = "CompareAlgos.xlsx"

  def runAllAlgorithms(instance: Instance): Vector[String] = {
    Vector(
      instance.anchors.toString,
      Greedy.run(instance.normSort(1)).objectiveValue.toString,
      Greedy.run(instance.normSort(inf)).objectiveValue.toString,
      Greedy.run(instance.normSort(inf,Point.origin).reverse).objectiveValue.toString,
      Greedy.run(instance.normSort(1, Point.origin)).objectiveValue.toString,
      Greedy.run(instance.normSort(inf, Point.origin)).objectiveValue.toString,
      Greedy.run(instance.normSort(inf).reverse).objectiveValue.toString,
      TilePacking.run(instance.normSort(1)).objectiveValue.toString,
      TilePacking.run(instance.normSort(inf)).objectiveValue.toString,
      TilePacking.run(instance.normSort(inf,Point.origin).reverse).objectiveValue.toString,
      BruteForce.run(instance,withPreprocess = true).objectiveValue.toString
    )
  }

  // construct random instances
  val numberOfAnchors = 6
  val numberOfInstances = 10
  val instances: Vector[Instance] = Vector.fill(numberOfInstances)(Instance.createRandomUnitSquareInstance(numberOfAnchors))

  // runs all algorithms, prints progress and puts outputs in data object
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
      "linfGreedy_from_origin_reverse",
      "l1Greedy_from_origin",
      "linfGreedy_from_origin",
      "linfGreedy_from_topright_reverse",
      "l1TilePacking",
      "linfTilePacking",
      "l-infTilePacking",
      "bruteforce"
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

    val filename = System.getProperty("user.dir") + "/outputFiles/" + spreadsheetName

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