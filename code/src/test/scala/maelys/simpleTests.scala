package maelys

import ch.ethz.math.ifor.rectanglePacking.Rectangle
import ch.ethz.math.ifor.rectanglePacking.algorithms.TilePacking
import ch.ethz.math.ifor.rectanglePacking.problemInstance.{Anchor, Instance, Point}
import ch.ethz.math.ifor.rectanglePacking.inf
object simpleTests extends App {
 val pt=new Anchor(Vector(0.8,0.8))
 val pt2=new Anchor(Vector(0.8,0.2))
 val pt3=new Anchor(Vector(0.2,0.8))
 val pt4=new Anchor(Vector(0.7,0.4))
 val pt5=new Anchor(Vector(0,0))
 val inst=new Instance(Point.topright,List.empty[Rectangle],List(pt,pt2,pt3,pt4,pt5))
 println(inst.normSort(inf).anchors)
 println(inst.normSort(-inf).anchors)
}
