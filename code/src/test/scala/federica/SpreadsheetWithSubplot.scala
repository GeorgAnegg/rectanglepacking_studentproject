package federica

import java.io.FileOutputStream

import ch.ethz.math.ifor.rectanglePacking.algorithms.{BruteForce, Greedy, TilePacking}
import ch.ethz.math.ifor.rectanglePacking.problemInstance.Instance
import ch.ethz.math.ifor.rectanglePacking.inf
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import plotly.Scatter
import plotly._
import layout._
import Plotly._
import ch.ethz.math.ifor.rectanglePacking.htmlVisualization.Html
import plotly.element.{AxisAnchor, AxisReference}

object SpreadsheetWithSubplot extends App {

  def runAllAlgorithms(instance: Instance): Vector[String] = {
    println("Computing Greedy")
    val outputGreedy1 = Greedy.run(instance.normSort(1))
    val outputGreedyInf = Greedy.run(instance.normSort(inf))
    println("Computing TilePacking")
    val outputTilePacking1 = TilePacking.run(instance.normSort(1))
    val outputTilePackingInf = TilePacking.run(instance.normSort(inf))
    println("Computing BruteForce")
    val outputBruteForce = BruteForce.run(instance)

    val trace1: Vector[Scatter] = Html.scatterRectangles(outputGreedy1,"l1Greedy",AxisReference.X1,AxisReference.Y1)
    //val trace2: Vector[Scatter] = Html.scatterRectangles(outputGreedyInf,"linfGreedy",AxisReference.X2,AxisReference.Y2)
    val trace3: Vector[Scatter] = Html.scatterRectangles(outputTilePacking1, "l1TilePacking",AxisReference.X2,AxisReference.Y2)
    //val trace4: Vector[Scatter] = Html.scatterRectangles(outputTilePackingInf,"linfTilePacking",AxisReference.X4,AxisReference.Y4)
    val trace5: Vector[Scatter] = Html.scatterRectangles(outputBruteForce, "BruteForce",AxisReference.X3,AxisReference.Y3)

    // Subplot
    val subplot: Vector[Scatter] = trace1 ++ trace3 ++ trace5
    subplot.plot(
      path = "outputFiles/htmlFiles/"+outputGreedy1.uuid+".html",
      Layout(
        title = "Different Algorithms Plots",
        showlegend = false,
        height = 800,
        width = 800,
        xaxis = Axis(
          anchor = AxisAnchor.Reference(AxisReference.Y1),
          domain = (0, 0.48)),
        yaxis = Axis(
          anchor = AxisAnchor.Reference(AxisReference.X1),
          domain = (0.52, 1)),
        xaxis2 = Axis(
          anchor = AxisAnchor.Reference(AxisReference.Y2),
          domain = (0.52, 1)),
        yaxis2 = Axis(
          anchor = AxisAnchor.Reference(AxisReference.X2),
          domain = (0.52, 1)),
        xaxis3 = Axis(
          anchor = AxisAnchor.Reference(AxisReference.Y3),
          domain = (0, 0.48)),
        yaxis3 = Axis(
          anchor = AxisAnchor.Reference(AxisReference.X3),
          domain = (0, 0.48))/*,
        xaxis4 = Axis(
          anchor = AxisAnchor.Reference(AxisReference.Y4),
          domain = (0.52, 1)),
        yaxis4 = Axis(
          anchor = AxisAnchor.Reference(AxisReference.X4),
          domain = (0, 0.48))*/),
      false,
      false,
      true)


    Vector(
      instance.anchors.toString,
      outputGreedy1.objectiveValue.toString,
      outputGreedyInf.objectiveValue.toString,
      outputTilePacking1.objectiveValue.toString,
      outputTilePackingInf.objectiveValue.toString,
      outputBruteForce.objectiveValue.toString,
      "file:///"+System.getProperty("user.dir") + "/outputFiles/htmlFiles/"+outputGreedy1.uuid+".html",
      // to be continued
    )
  }
  //val instance1 = Instance.standardSquare(List(Anchor.pointToAnchor(Point.origin)))
  //val instance2 = Instance.standardSquare(List(Anchor.pointToAnchor(Point.origin),Anchor(Vector(0.3,0.8)),Anchor(Vector(0.8,0.3))))
  //val instances: Vector[Instance] = Vector(instance1,instance2) //construct vectorOfInstances. we should have that function in the class Instance

  val numberOfAnchors = 7
  val numberOfInstances = 10
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
      "BruteForce",
      "Pictures"//,
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
