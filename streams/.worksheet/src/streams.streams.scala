package streams

object streams {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(104); 
  val v = Vector(Vector('S', 'T'), Vector('o', 'o'), Vector('o', 'o'));System.out.println("""v  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Char]] = """ + $show(v ));$skip(53); val res$0 = 
  
  for{
  			vs <- v
  			ch <- vs
  			} yield ch;System.out.println("""res0: scala.collection.immutable.Vector[Char] = """ + $show(res$0));$skip(36); 
  			
 	 			val s = Set[Int](1,2,3);System.out.println("""s  : scala.collection.immutable.Set[Int] = """ + $show(s ));$skip(16); 
 	 			val f = 4;System.out.println("""f  : Int = """ + $show(f ));$skip(11); val res$1 = 
  			s + 4;System.out.println("""res1: scala.collection.immutable.Set[Int] = """ + $show(res$1));$skip(23); 
  			val s2 = Set(7,8);System.out.println("""s2  : scala.collection.immutable.Set[Int] = """ + $show(s2 ));$skip(12); val res$2 = 
				s ++ s2;System.out.println("""res2: scala.collection.immutable.Set[Int] = """ + $show(res$2));$skip(24); 
				val l = List(3,5,7);System.out.println("""l  : List[Int] = """ + $show(l ));$skip(41); val res$3 = 
                           
        l(0);System.out.println("""res3: Int = """ + $show(res$3));$skip(23); val res$4 = 
        !l.contains(5);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(27); 
        val l1 = List(1,2);System.out.println("""l1  : List[Int] = """ + $show(l1 ));$skip(27); 
        val l2 = List(1,2);System.out.println("""l2  : List[Int] = """ + $show(l2 ));$skip(15); val res$5 = 
        l1==l2;System.out.println("""res5: Boolean = """ + $show(res$5))}
}
