package patmat

object patmat {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(68); 
  val l = List('a','b','d', 'b','a');System.out.println("""l  : List[Char] = """ + $show(l ));$skip(28); 
  val m = l.groupBy((x)=>x);System.out.println("""m  : scala.collection.immutable.Map[Char,List[Char]] = """ + $show(m ));$skip(50); 
  val ml = m.map((x)=>(x._1, x._2.length)).toList;System.out.println("""ml  : List[(Char, Int)] = """ + $show(ml ));$skip(17); val res$0 = 
  l.takeRight(2);System.out.println("""res0: List[Char] = """ + $show(res$0));$skip(12); val res$1 = 
  l.drop(2);System.out.println("""res1: List[Char] = """ + $show(res$1));$skip(21); 
  val mlm = ml.toMap;System.out.println("""mlm  : scala.collection.immutable.Map[Char,Int] = """ + $show(mlm ));$skip(20); 
  val l2 = 'z' :: l;System.out.println("""l2  : List[Char] = """ + $show(l2 ))}
  
}
