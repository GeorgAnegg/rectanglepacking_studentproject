package maelys

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

object sensitivityTest extends App {

  def runAllAlgorithms(instance: Instance, upperBound: Double): Vector[String] = {
    println("Computing Greedy")
    val outputGreedy1 = Greedy.run(instance.normSort(1))
    val outputGreedyInf = Greedy.run(instance.normSort(inf))
    println("Computing TilePacking")
    val outputTilePacking1 = TilePacking.run(instance.normSort(1))
    val outputTilePackingInf = TilePacking.run(instance.normSort(inf))
    println("Computing BruteForce")
    //val outputBruteForce = BruteForce.run(instance)
    val outputBruteForce = 1.toDouble

    //val maxObjVal: Double = Vector(outputGreedy1.objectiveValue,outputGreedyInf.objectiveValue,outputTilePacking1.objectiveValue,outputTilePackingInf.objectiveValue,outputBruteForce.objectiveValue).max
    if (outputBruteForce <= upperBound) {
    //if (outputBruteForce.objectiveValue < upperBound) {
      val trace1: Vector[Scatter] = Html.scatterRectangles(outputGreedy1, "l1Greedy", AxisReference.X1, AxisReference.Y1)
      val trace2: Vector[Scatter] = Html.scatterRectangles(outputGreedyInf,"linfGreedy",AxisReference.X2,AxisReference.Y2)
      val trace3: Vector[Scatter] = Html.scatterRectangles(outputTilePacking1, "l1TilePacking", AxisReference.X3, AxisReference.Y3)
      val trace4: Vector[Scatter] = Html.scatterRectangles(outputTilePackingInf,"linfTilePacking",AxisReference.X4,AxisReference.Y4)
      //val trace5: Vector[Scatter] = Html.scatterRectangles(outputBruteForce, "BruteForce", AxisReference.X4, AxisReference.Y4)

      // Subplot
      val subplot: Vector[Scatter] = trace1 ++ trace2 ++ trace3 ++ trace4
      subplot.plot(
        path = "outputFiles/htmlFiles/" + outputGreedy1.uuid + ".html",
        Layout(
          title = "Different Algorithms Plots",
          showlegend = false,
          height = 800,
          width = 800,
          xaxis = Axis(
            title = "l1Greedy",
            anchor = AxisAnchor.Reference(AxisReference.Y1),
            domain = (0, 0.45)),
          yaxis = Axis(
            anchor = AxisAnchor.Reference(AxisReference.X1),
            domain = (0.55, 1)),
          xaxis2 = Axis(
            title = "linfGreedy",
            anchor = AxisAnchor.Reference(AxisReference.Y2),
            domain = (0.55, 1)),
          yaxis2 = Axis(
            anchor = AxisAnchor.Reference(AxisReference.X2),
            domain = (0.55, 1)),
          xaxis3 = Axis(
            title = "l1TilePacking",
            anchor = AxisAnchor.Reference(AxisReference.Y3),
            domain = (0, 0.45)),
          yaxis3 = Axis(
            anchor = AxisAnchor.Reference(AxisReference.X3),
            domain = (0, 0.45)),
          xaxis4 = Axis(
            title = "BruteForce",
            anchor = AxisAnchor.Reference(AxisReference.Y4),
            domain = (0.55, 1)),
          yaxis4 = Axis(
            anchor = AxisAnchor.Reference(AxisReference.X4),
            domain = (0, 0.45))),
        useCdn = false,
        openInBrowser = false,
        addSuffixIfExists = true)


      Vector(
        instance.anchors.toString,
        outputGreedy1.objectiveValue.toString,
        outputGreedyInf.objectiveValue.toString,
        outputTilePacking1.objectiveValue.toString,
        outputTilePackingInf.objectiveValue.toString,
        outputBruteForce.toString,
        //outputBruteForce.objectiveValue.toString,
        "file:///" + System.getProperty("user.dir") + "/outputFiles/htmlFiles/" + outputGreedy1.uuid + ".html",
        // to be continued
      )
    } else
    {Vector()}
  }


  val NumberOfAnchors: Int = 10
  val NumberOfInstances: Int= 20
  val NumberOfPerturbs: Int= 100
  val upperBound: Double = 1
  val epsilon: Double = 0.01

  var totalData: Vector[Vector[String]] = Vector()

  for (j <- 0 until NumberOfInstances){
    println(s"Instance number ${j+1}")
    val inst=Instance.perturbedRandomAntiDiagonal(NumberOfAnchors,epsilon)
    val instances: Vector[Instance] = inst+:(for (_ <- 1 to NumberOfPerturbs) yield inst.perturbed(0.01)).toVector
    val data: Vector[Vector[String]] = instances.map(runAllAlgorithms(_,upperBound)).filterNot(_==Vector()).map(i=>(j+1).toString +: i)

    val data2:Vector[Vector[String]] = data.map(a=>a++Vector((a(2).toDouble-data(0)(2).toDouble)/data(0)(2).toDouble*100,(a(3).toDouble-data(0)(3).toDouble)/data(0)(3).toDouble*100,(a(4).toDouble-data(0)(4).toDouble)/data(0)(4).toDouble*100,(a(5).toDouble-data(0)(5).toDouble)/data(0)(5).toDouble*100,(a(6).toDouble-data(0)(6).toDouble)/data(0)(6).toDouble*100).map(v=>v.toString))
    totalData = totalData ++ data2
  }

  /*
  val numberOfAnchors = 7
  val numberOfInstances = 10
  val upperBound: Double = 0.7
  val instances: Vector[Instance] = (for (i<- 1 to numberOfInstances) yield Instance.createRandomUnitSquareInstance(numberOfAnchors)).toVector

  val data: Vector[Vector[String]] = instances.map(runAllAlgorithms(_,upperBound)).map(i=>i._2).filterNot(_==Vector()) //this gives a Vector of Vector[String] that can be filled into the spreadsheet

   */
  def writeSpreadsheet(data:Vector[Vector[String]]): Unit = {
    val workbook = new XSSFWorkbook()
    val sheet = workbook.createSheet("Results")

    //create header row
    val headerRow = sheet.createRow(0)
    val columns = List(
      "Number of instance",
      "Anchors",
      "l1Greedy",
      "linfGreedy",
      "l1TilePacking",
      "linfTilePacking",
      "BruteForce",
      "Pictures",
      "l1GreedyGap",
      "linfGreedyGap",
      "l1TilePackingGap",
      "linfTilePackingGap",
      "BruteForceGap" //,
    )

    for (i<- columns.indices) {
      val cell = headerRow.createCell(i)
      cell.setCellValue(columns(i))
    }

    val numberOfHeaderRows = 1

    data.indices.foreach{
      i=> {
        val currentRow = sheet.createRow(i+numberOfHeaderRows)
        data(i).indices.foreach{ j=>
          currentRow.createCell(j).setCellValue(data(i)(j))
        }
      }
    }

    val filename = System.getProperty("user.dir") + "/outputFiles/test/"+"sensitivityTestRandAntiDiagPert10A.xlsx"

    val fileOut = new FileOutputStream(filename)
    workbook.write(fileOut)

    fileOut.close()
    workbook.close()

    println("====================")
    println(" XLSX file written to:")
    println(filename)
    println("====================")

  }

  writeSpreadsheet(totalData)

}