package maelys

import ch.ethz.math.ifor.rectanglePacking.Algorithms.{Greedy, TilePacking}
import ch.ethz.math.ifor.rectanglePacking.ProblemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.{Rectangle, cross, dimension}

object HelloRectanglePacking extends App {
  val anchors=List(new Anchor(Vector(0,0)),new Anchor(Vector(0.3,0.1)),new Anchor(Vector(0.4,0.8)),new Anchor(Vector(0.7,0.6)),new Anchor(Vector(0.8,0.8)))
  val fRect=List(new Rectangle(new Point(Vector(0.4,0.4)),new Point(Vector(0.6,0.5))))
  val inst=new Instance(Point.topright,fRect,anchors).normSort(1)
  print(inst.anchors)
  val inst2= new Instance(inst.topRightBox,inst.forbiddenRectangles,inst.anchors.tail.tail)
  val res=Greedy.run(inst).rectangles
  val res2=TilePacking.run(inst)
  println("Greedy")
  println(res.map(r=>(r._2.originCorner.coordinates,r._2.topRightCorner.coordinates)))
  println("Tilepacking")
  println(res2.rectangles.map(r=>(r._2.originCorner.coordinates,r._2.topRightCorner.coordinates)))

  //println(testI.topRightBox.coordinates)
  //println(testI.anchors.map(a=>a.coordinates))
  //println(testI.forbiddenRectangles.map(r=>(r.originCorner.coordinates,r.topRightCorner.coordinates)))
 // val out=Greedy.run(inst).rectangles

}
