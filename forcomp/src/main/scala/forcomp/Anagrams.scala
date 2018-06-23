package forcomp

import common._

object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /** `Occurrences` is a `List` of pairs of characters and positive integers saying
   *  how often the character appears.
   *  This list is sorted alphabetically w.r.t. to the character in each pair.
   *  All characters in the occurrence list are lowercase.
   *  
   *  Any list of pairs of lowercase characters and their frequency which is not sorted
   *  is **not** an occurrence list.
   *  
   *  Note: If the frequency of some character is zero, then that character should not be
   *  in the list.
   */
  type Occurrences = List[(Char, Int)]

  /** The dictionary is simply a sequence of words.
   *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  val dictionary: List[Word] = loadDictionary

  /** Converts the word into its character occurence list.
   *  
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   */
  def wordOccurrences(w: Word): Occurrences = (w.groupBy((x) => x.toLower) map((y) => (y._1, y._2.length)) toList) sortWith((x,y)=> x._1 < y._1)

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = ((s flatMap((x) => wordOccurrences(x))).groupBy((x)=>x._1).map((z)=>z._2.reduceLeft((b,a)=>(b._1,b._2+a._2))).map((c) => (c._1,c._2)) toList) sortWith((c,d)=>c._1<d._1)


  /** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   *  the words that have that occurrence count.
   *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *  
   *  For example, the word "eat" has the following character occurrence list:
   *
   *     `List(('a', 1), ('e', 1), ('t', 1))`
   *
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
   *
   */
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = (dictionary map ((x : Word) => (wordOccurrences(x),x))).groupBy((y)=>y._1).map((z)=>(z._1, z._2.map((a)=>a._2)))

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences.get(wordOccurrences(word)).get
  
  
  
  /*{
    
    def wordAnagramLoop(index : Int, w : Char, loopWord : List[Char], accm : List[Word]) : List[Word] = {
    		accm ::: wordAnagramInternal(loopWord.take(index):::loopWord.drop(index+1)).map((x)=> w +: x)
    }
    
    
    def wordAnagramRec(index : Int, lrecword : List[Char], accm : List[Word]) : List[Word] = {
      if(index>=lrecword.length) {
        List()
      } else {
    	wordAnagramLoop(index,lrecword(index), lrecword, accm) ::: wordAnagramRec(index+1, lrecword, accm)
      }
    }
    
    
    def wordAnagramInternal(lword : List[Char]) : List[Word] = 
    {
      lword match {
        case Nil => List()
        case h :: Nil => List(h toString)
        case h :: t => {
          wordAnagramRec(0, lword, List())
        }
        
      }
    }
    wordAnagramInternal(word toList).filter((y)=>dictionary.contains(y)).groupBy((x)=>x).map((y)=>y._1) toList
  }*/

  /** Returns the list of all subsets of the occurrence list.
   *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   *  is a subset of `List(('k', 1), ('o', 1))`.
   *  It also include the empty subset `List()`.
   * 
   *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   *    List(
   *      List(),
   *      List(('a', 1)),
   *      List(('a', 2)),
   *      List(('b', 1)),
   *      List(('a', 1), ('b', 1)),
   *      List(('a', 2), ('b', 1)),
   *      List(('b', 2)),
   *      List(('a', 1), ('b', 2)),
   *      List(('a', 2), ('b', 2))
   *    )
   *
   *  Note that the order of the occurrence list subsets does not matter -- the subsets
   *  in the example above could have been displayed in some other order.
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    
	def occurrenceGen(occurrence : (Char, Int)) : List[Occurrences] = {
	  if(occurrence._2==0) {
	    List(occurrence) :: List[Occurrences]()
	  } else {
	    List(occurrence) :: occurrenceGen((occurrence._1,occurrence._2-1))
	  }
	}
    
    occurrences match {
      case Nil => List(Nil)
      case h :: Nil => occurrenceGen(h)
      case h :: t => for( e1 <- occurrenceGen(h); e2 <- combinations(t) ) yield e1.filter((x) => x._2!=0) ::: e2.filter((y) => y._2!=0)
    } 
		  
  }

  /** Subtracts occurrence list `y` from occurrence list `x`.
   * 
   *  The precondition is that the occurrence list `y` is a subset of
   *  the occurrence list `x` -- any character appearing in `y` must
   *  appear in `x`, and its frequency in `y` must be smaller or equal
   *  than its frequency in `x`.
   *
   *  Note: the resulting value is an occurrence - meaning it is sorted
   *  and has no zero-entries.
   */
  def subtract(xOcc: Occurrences, yOcc: Occurrences): Occurrences = {
    
	   def subtractEntry(occ : (Char, Int), ys : Occurrences) : (Char, Int) = {
	     ys.find((x)=>x._1==occ._1 && occ._2>=x._2) match {
	       case None => occ
	       case Some(v) => (occ._1, occ._2-v._2) 
	     }
	   }
    
		(xOcc match {
		  case Nil => List()
		  case h :: Nil => subtractEntry(h, yOcc) :: List()
		  case h :: t => subtractEntry(h, yOcc) :: subtract(t, yOcc)
		}).filter(_._2>0)
   
  }

  /** Returns a list of all anagram sentences of the given sentence.
   *  
   *  An anagram of a sentence is formed by taking the occurrences of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *  
   *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   *    List(
   *      List(en, as, my),
   *      List(en, my, as),
   *      List(man, yes),
   *      List(men, say),
   *      List(as, en, my),
   *      List(as, my, en),
   *      List(sane, my),
   *      List(Sean, my),
   *      List(my, en, as),
   *      List(my, as, en),
   *      List(my, sane),
   *      List(my, Sean),
   *      List(say, men),
   *      List(yes, man)
   *    )
   *
   *  The different sentences do not have to be output in the order shown above - any order is fine as long as
   *  all the anagrams are there. Every returned word has to exist in the dictionary.
   *  
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    
    def sentenceBuilder(currentOcc : Occurrences) : List[Sentence] = {
    		if(currentOcc.isEmpty) {
        				List(List())
        			} else {
        				for{ 
        						o <- (combinations(currentOcc) filter dictionaryByOccurrences.contains) 
        						w <- dictionaryByOccurrences(o)
        						sl <- sentenceBuilder(subtract(currentOcc,o))
        				} yield w :: sl
        			}
    }
    

    sentence match {
      case Nil => List(sentence)
      case _ => sentenceBuilder(sentenceOccurrences(sentence) )
    }
  }

}