package forcomp

object forcomp {
  val list = List(1,2,3)                          //> list  : List[Int] = List(1, 2, 3)
  list.foldLeft(0)(_ + _)                         //> res0: Int = 6
  val m = Map('a'->'1','b'->'2')                  //> m  : scala.collection.immutable.Map[Char,Char] = Map(a -> 1, b -> 2)
  "ab" map m                                      //> res1: String = 12
  m('a')                                          //> res2: Char = 1
}