package maelys

import ch.ethz.math.ifor.rectanglePacking.problemInstance.Anchor

object simpleTests extends App {
 val L=List(List(0.1,0.4),List(0.4,0.2),List(0.1,0.4))
 // println(L.distinct)
 val v=Vector.range(0,100)
 val v2=v.map(a=>a+util.Random.nextInt(2))
 //println(v2)
 println(Anchor.equallySpaceAntiDiagonal(3))
}
