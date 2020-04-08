package federica

import java.io.FileOutputStream

import ch.ethz.math.ifor.rectanglePacking.algorithms.{Greedy, TilePacking}
import ch.ethz.math.ifor.rectanglePacking.problemInstance.Instance

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import ch.ethz.math.ifor.rectanglePacking.inf

object SpreadsheetWithPictures extends App {


  def runAllAlgorithms(instance: Instance): Vector[String] = {
    val outputGreedy1 = Greedy.run(instance.normSort(1))
    val outputGreedyInf = Greedy.run(instance.normSort(inf))
    val outputTilePacking1 = TilePacking.run(instance.normSort(1))
    val outputTilePackingInf = TilePacking.run(instance.normSort(inf))
    outputGreedy1.writeHtmlFile("Greedy1")
    outputGreedyInf.writeHtmlFile("Greedyinf")
    outputTilePacking1.writeHtmlFile("TilePacking1")
    outputTilePackingInf.writeHtmlFile("TilePackingInf")
    Vector(
      instance.anchors.toString,
      outputGreedy1.objectiveValue.toString,
      outputGreedyInf.objectiveValue.toString,
      outputTilePacking1.objectiveValue.toString,
      outputTilePackingInf.objectiveValue.toString,
      "file:///"+System.getProperty("user.dir") + "/outputFiles/htmlFiles/Greedy1"+outputGreedy1.uuid+".html",
      "file:///"+System.getProperty("user.dir") +"/outputFiles/htmlFiles/Greedyinf"+outputGreedyInf.uuid+".html",
      "file:///"+System.getProperty("user.dir") +"/outputFiles/htmlFiles/TilePacking1"+outputTilePacking1.uuid+".html",
      "file:///"+System.getProperty("user.dir") +"/outputFiles/htmlFiles/TilePackingInf"+outputTilePackingInf.uuid+".html"
      // to be continued
      )
    }
    //val instance1 = Instance.standardSquare(List(Anchor.pointToAnchor(Point.origin)))
    //val instance2 = Instance.standardSquare(List(Anchor.pointToAnchor(Point.origin),Anchor(Vector(0.3,0.8)),Anchor(Vector(0.8,0.3))))
    //val instances: Vector[Instance] = Vector(instance1,instance2) //construct vectorOfInstances. we should have that function in the class Instance

    val numberOfAnchors = 8
    val numberOfInstances = 1
    val instances: Vector[Instance] = (for (i<- 1 to numberOfInstances) yield Instance.createRandomUnitSquareInstance(numberOfAnchors)).toVector

    val data: Vector[Vector[String]] = instances.map(runAllAlgorithms) //this gives a Vector of Vector[String] that can be filled into the spreadsheet

    def writeSpreadsheet(data:Vector[Vector[String]]): Unit = {
      val workbook = new XSSFWorkbook()
      val sheet = workbook.createSheet("Results")

      //create header row
      val headerRow = sheet.createRow(0)
      val columns = List(
        "Anchors",
        "l1Greedy",
        "linfGreedy",
        "l1TilePacking",
        "linfTilePacking",
        "l1GreedyPicture",
        "linfGreedyPicture",
        "l1TilePackingPicture",
        "linfTilePackingPicture"//,
        /*"l1TilePacking",
        "linfTilePacking",
        "opt"
        */
      )

      for (i<- columns.indices) {
        val cell = headerRow.createCell(i)
        cell.setCellValue(columns(i))
      }

      var numberOfHeaderRows = 1

      data.indices.foreach{
        i=> {
          val currentRow = sheet.createRow(i+numberOfHeaderRows)
          data(i).indices.foreach{ j=>
            currentRow.createCell(j).setCellValue(data(i)(j))
          }
        }
      }

      val filename = System.getProperty("user.dir") + "/outputFiles/test/"+"OutputTest.xlsx"

      val fileOut = new FileOutputStream(filename)
      workbook.write(fileOut)

      fileOut.close()
      workbook.close()

      println("====================")
      println(" XLSX file written to:")
      println(filename)
      println("====================")

    }

    writeSpreadsheet(data)

}
