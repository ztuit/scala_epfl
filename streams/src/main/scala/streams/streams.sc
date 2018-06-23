package streams

object streams {
  val v = Vector(Vector('S', 'T'), Vector('o', 'o'), Vector('o', 'o'))
                                                  //> v  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Cha
                                                  //| r]] = Vector(Vector(S, T), Vector(o, o), Vector(o, o))
  
  for{
  			vs <- v
  			ch <- vs
  			} yield ch                //> res0: scala.collection.immutable.Vector[Char] = Vector(S, T, o, o, o, o)
  			
 	 			val s = Set[Int](1,2,3)
                                                  //> s  : scala.collection.immutable.Set[Int] = Set(1, 2, 3)
 	 			val f = 4         //> f  : Int = 4
  			s + 4                     //> res1: scala.collection.immutable.Set[Int] = Set(1, 2, 3, 4)
  			val s2 = Set(7,8)         //> s2  : scala.collection.immutable.Set[Int] = Set(7, 8)
				s ++ s2           //> res2: scala.collection.immutable.Set[Int] = Set(1, 2, 7, 3, 8)
				val l = List(3,5,7)
                                                  //> l  : List[Int] = List(3, 5, 7)
                           
        l(0)                                      //> res3: Int = 3
        !l.contains(5)                            //> res4: Boolean = false
        val l1 = List(1,2)                        //> l1  : List[Int] = List(1, 2)
        val l2 = List(1,2)                        //> l2  : List[Int] = List(1, 2)
        l1==l2                                    //> res5: Boolean = true
}