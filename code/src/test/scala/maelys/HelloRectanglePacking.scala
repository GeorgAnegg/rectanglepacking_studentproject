package maelys

import ch.ethz.math.ifor.rectanglePacking.Algorithms.Greedy
import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.{Rectangle, cross, dimension}

object HelloRectanglePacking extends App {
  val anchors=List(new Anchor(Vector(0,0)),new Anchor(Vector(0.1,0.6)),new Anchor(Vector(0,0.2)))
  val fRect=List(new Rectangle(new Point(Vector(0.2,0.5)),new Point(Vector(0.3,0.7))))
  val inst=new Instance(Point.topright,fRect,anchors).normSort(1)
  val inst2= new Instance(inst.topRightBox,inst.forbiddenRectangles,inst.anchors.tail.tail)
  val res=Greedy.run(inst).rectangles
  println(res.map(r=>(r._2.originCorner.coordinates,r._2.topRightCorner.coordinates)))

  //println(testI.topRightBox.coordinates)
  //println(testI.anchors.map(a=>a.coordinates))
  //println(testI.forbiddenRectangles.map(r=>(r.originCorner.coordinates,r.topRightCorner.coordinates)))
 // val out=Greedy.run(inst).rectangles

}
