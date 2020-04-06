package ch.ethz.math.ifor.rectanglePacking

import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Anchor, Point}
import ch.ethz.math.ifor.rectanglePacking.segmentGreedy.BoundarySegment

case class Rectangle(originCorner : Point, topRightCorner: Point ) {

  /**
    * For a rectangle in dimension "dimension", returns the low boundary corresponding to the hyperplane on dimension i
    * @param i
    * @return
    */
  def lowBoundary(i : Int): BoundarySegment = {
    require (i>=0 && i< dimension,"Correct dimension")

    //val boundaryCoordinate = (0 until dimension).filterNot(i==_).map(j=>(anchorOrigin.coordinates(j),anchorTopRight.coordinates(j)))
    var boundaryCoordinates: Vector[Double] = Vector.empty[Double]
    for (j <- 0 until dimension) {
      if (j==i) {
          boundaryCoordinates= boundaryCoordinates :+ originCorner.coordinates(i)
      }
      else {
        boundaryCoordinates = boundaryCoordinates :+ originCorner.coordinates(j)
        boundaryCoordinates = boundaryCoordinates :+ topRightCorner.coordinates(j)
      }
    }
    new BoundarySegment(boundaryCoordinates)
  }

  /** Volume of the rectangle
    *
    * @return
    */
  def volume : Double = ((topRightCorner.coordinates).zip(originCorner.coordinates).map(pair=>pair._1-pair._2).product)

  /** Returns true if the two rectangles intersect (stricly)
    *
    * @param r a rectangle
    * @return
    */
  def intersects(r:Rectangle): Boolean = {
    (originCorner.coordinates zip r.topRightCorner.coordinates).forall(pair => pair._1 < pair._2) && (r.originCorner.coordinates zip topRightCorner.coordinates).forall(pair => pair._1 < pair._2)
  }

  /** Returns true if the rectangle is contained in the box [origin,pt]
    * Origin of rectangle cannot be origin because origin must be one of the anchors
    * @param pt
    * @return
    */
  def contained(pt:Point): Boolean={
    originCorner.coordinates!=Point.origin.coordinates && Point.origin.dominatesLoose(originCorner) && topRightCorner.dominatesLoose(pt)
  }

  /** Updates list of forbidden Rectangles for tilepacking
    * 
    * @param forbidRect
    * @return
    */
  def updateRectanglesTilePacking(forbidRect : List[Rectangle]): List[Rectangle] = {
  if (forbidRect.length == 0) {
    List(this)
  }
  else {
    if (originCorner.dominatesLoose(forbidRect.head.originCorner)) {
      this.updateRectanglesTilePacking(forbidRect.tail)
  }
    else {
      forbidRect.head +: this.updateRectanglesTilePacking(forbidRect.tail)
    }
    }
  }
}
