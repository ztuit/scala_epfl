package patmat

object patmat {
  val l = List('a','b','d', 'b','a')              //> l  : List[Char] = List(a, b, d, b, a)
  val m = l.groupBy((x)=>x)                       //> m  : scala.collection.immutable.Map[Char,List[Char]] = Map(b -> List(b, b), d
                                                  //|  -> List(d), a -> List(a, a))
  val ml = m.map((x)=>(x._1, x._2.length)).toList //> ml  : List[(Char, Int)] = List((b,2), (d,1), (a,2))
  l.takeRight(2)                                  //> res0: List[Char] = List(b, a)
  l.drop(2)                                       //> res1: List[Char] = List(d, b, a)
  val mlm = ml.toMap                              //> mlm  : scala.collection.immutable.Map[Char,Int] = Map(b -> 2, d -> 1, a -> 2
                                                  //| )
  val l2 = 'z' :: l                               //> l2  : List[Char] = List(z, a, b, d, b, a)
  
}