package forcomp

object forcomp {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(58); 
  val list = List(1,2,3);System.out.println("""list  : List[Int] = """ + $show(list ));$skip(26); val res$0 = 
  list.foldLeft(0)(_ + _);System.out.println("""res0: Int = """ + $show(res$0));$skip(33); 
  val m = Map('a'->'1','b'->'2');System.out.println("""m  : scala.collection.immutable.Map[Char,Char] = """ + $show(m ));$skip(13); val res$1 = 
  "ab" map m;System.out.println("""res1: String = """ + $show(res$1));$skip(9); val res$2 = 
  m('a');System.out.println("""res2: Char = """ + $show(res$2))}
}
