package forcomp

object forcomp {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  val s = "abcdac"                                //> s  : String = abcdac
  s.groupBy((x) => x) map((y) => (y._1, y._2.length)) toList
                                                  //> res0: List[(Char, Int)] = List((b,1), (d,1), (a,2), (c,2))
}