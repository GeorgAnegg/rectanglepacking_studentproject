package maelys

import ch.ethz.math.ifor.rectanglePacking.algorithms.{BruteForce, Greedy, TilePacking}
import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.{Rectangle, cross, dimension}

object OptimTests extends App {

  val anchors=List(new Anchor(Vector(0,0)),new Anchor(Vector(0.8,0.8)),new Anchor(Vector(0.8,1)),new Anchor(Vector(0.4,0.8)))
  val anchors2=List(new Anchor(Vector(0.6,0.2)),new Anchor(Vector(0.4,0.6)),new Anchor(Vector(0.4,0.6)),new Anchor(Vector(0.6,0.8)))
  val anchors3=List(new Anchor(Vector(0,0)),new Anchor(Vector(0.7,0.6)),new Anchor(Vector(0.4,0.8)),new Anchor(Vector(0.8,0.8)))
  val fRect=List(new Rectangle(new Point(Vector(0.4,0.4)),new Point(Vector(0.6,0.5))))
  val inst=new Instance(Point.topright,List[Rectangle](),anchors).normSort(1)

  println("Bruteforce")
  val res=BruteForce.run(inst).rectangles
  println(res.map(r=>(r._2.originCorner.coordinates,r._2.topRightCorner.coordinates)))
  println(res.values.map(r=>r.volume).sum)

  println("Greedy")
 val res2=Greedy.run(inst).rectangles
  println(res2.map(r=>(r._2.originCorner.coordinates,r._2.topRightCorner.coordinates)))
  println(res2.values.map(r=>r.volume).sum)

  println("TilePacking")
  val res3=TilePacking.run(inst).rectangles
  println(res3.map(r=>(r._2.originCorner.coordinates,r._2.topRightCorner.coordinates)))
  println(res3.values.map(r=>r.volume).sum)





}
