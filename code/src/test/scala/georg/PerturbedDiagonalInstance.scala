package georg

import java.io.FileOutputStream

import ch.ethz.math.ifor.rectanglePacking.algorithms.{Greedy, TilePacking}
import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.inf
import org.apache.poi.xssf.usermodel.XSSFWorkbook

object PerturbedDiagonalInstance extends App {

  val spreadsheetName: String = "OutputTest.xlsx"

  def runAllAlgorithms(instance: Instance): Vector[String] = {
    Vector(
      instance.anchors.toString,
      Greedy.run(instance.normSort(1)).objectiveValue.toString,
      Greedy.run(instance.normSort(inf)).objectiveValue.toString,
      Greedy.run(instance.normSort(inf,Point.origin).reverse).objectiveValue.toString,
      TilePacking.run(instance.normSort(1)).objectiveValue.toString,
      TilePacking.run(instance.normSort(inf)).objectiveValue.toString,
      TilePacking.run(instance.normSort(inf,Point.origin).reverse).objectiveValue.toString
      // opt
    )
  }


  // construct perturbed diagonal instances
  val numberOfAnchors = 5
  val numberOfInstances = 20
  val instances: Vector[Instance] = Vector(Instance.equallySpacedDiagonal(numberOfAnchors))++Vector.fill(numberOfInstances-1)(Instance.perturbedDiagonal(numberOfAnchors))

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
      "l-infGreedy",
      "l1TilePacking",
      "linfTilePacking",
      "l-infTilePacking"//,
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