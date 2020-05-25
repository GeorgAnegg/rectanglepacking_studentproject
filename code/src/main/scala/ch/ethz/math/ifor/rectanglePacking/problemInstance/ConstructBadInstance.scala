package ch.ethz.math.ifor.rectanglePacking.problemInstance

import ch.ethz.math.ifor.rectanglePacking.algorithms.TilePacking
import ch.ethz.math.ifor.rectanglePacking.inf

object ConstructBadInstance {

  def makeBad(bottomLeft: Point, topRight: Point,  numberOfAnchors: Int = 4, q: Double = 0.5, t: Double = 0.14): List[Anchor] = {
    val Vector(x0,y0) = bottomLeft.coordinates
    val Vector(x1,y1) = topRight.coordinates
    val List(x,y) = List(0,1).map(i=> topRight.coordinates(i)-bottomLeft.coordinates(i))
    val F = Anchor(Vector(x0+math.sqrt(t*x/y), y0+ math.sqrt(t*y/x)))
    val yF = F.coordinates(1)
    val a:List[Double] = List.tabulate(numberOfAnchors-1)(i=> yF + (y1-yF)*math.pow(q,i+1))++List(yF)
    val b:List[Double] = List(t)++(0 to numberOfAnchors-1).toList.map(i=> t/a(i))

    val badAnchors: List[Anchor] = List(F)++List.tabulate(numberOfAnchors)(i=> Anchor(Vector(b(i),a(i))))
    badAnchors.sortBy(_.coordinates(1)).reverse
  }

  def makeDiagonal(bottomLeft: Point, topRight: Point,  numberOfAnchors: Int = 4): List[Anchor] = {
    val Vector(x0,y0) = bottomLeft.coordinates
    val Vector(x1,y1) = topRight.coordinates
    val xIncrement = (x1-x0)/numberOfAnchors
    val yIncrement = (y1-y0)/numberOfAnchors
    val diagAnchors = List.tabulate(numberOfAnchors)(i=> Anchor(Vector(x0 +i*xIncrement, y0+i*yIncrement)))
    diagAnchors
  }

  def badAnchorsForL: List[Anchor] = {
    val badAnchors = makeBad(Point.origin, Point.topright)
    val aboveDiagonal = badAnchors.zipWithIndex.dropRight(1).flatMap { case (anchor, i) => makeDiagonal(anchor, new Point(Vector(badAnchors(i + 1).coordinates(0), 1))) }
    val baseAnchors = List(Anchor.origin) ++ List(badAnchors.last) ++ aboveDiagonal ++ aboveDiagonal.map(_.reflect2D)
    baseAnchors
  }

  //iterate L-shape
  def pullToCorner(anchor: Anchor,factor: Double): Anchor = Anchor(anchor.coordinates.map(coord => coord*(factor) + 1-factor ))

  def iterateL(base: List[Anchor], iterations: Int = 10): List[Anchor] = {
    val sortedBase = base.sortWith( _.normS(inf)>_.normS(inf))
    val duplicateThis = sortedBase.dropRight(1)
    val centralAnchor = sortedBase.last
    val shrinkFactor = 1-centralAnchor.coordinates(0)
    (0 to iterations).toList.flatMap(i=> duplicateThis.map(anchor => pullToCorner(anchor, math.pow(shrinkFactor,i))))
  }

  def anchorsToBreak_l_30: List[Anchor] = iterateL(badAnchorsForL,2)

  def confirmBreak():Unit = {
    val anchors = anchorsToBreak_l_30
    val inst = new Instance(Point.topright, List(), anchors)
    val out = TilePacking.run(inst.normSort(30))
    println(out.objectiveValue)
    // prints 0.49584852491555126
    // might have to increase JVM stacksize with -Xss3M
  }
}
