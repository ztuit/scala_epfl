package forcomp

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Anagrams._

@RunWith(classOf[JUnitRunner])
class AnagramsSuite extends FunSuite {

  test("wordOccurrences: abcd") {
    assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }



  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }

  
    test("sentenceOccurrences: and and a z") {
     
    assert(sentenceOccurrences(List("and", "and", "a", "z")) === List(('a', 3), ('d',2), ('n', 2), ('z', 1)))
  }

    test("sentenceOccurences: Roberto Carlos") {
      assert(sentenceOccurrences(List("Roberto", "Carlos"))==List(('a',1), ('b',1), ('c',1), ('e',1), ('l',1), ('o',3), ('r',3), ('s',1), ('t',1)))
    }

 
    
  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }



  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }

  test("simple combinations"){
    assert(combinations(List(('a',2), ('b',2)))==List(List(('a',2), ('b',2)), List(('a',2), ('b',1)), List(('a',2)), List(('a',1), ('b',2)), List(('a',1), ('b',1)), List(('a',1)), List(('b',2)), List(('b',1)), List()))
  }
  
   test("subtract: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lad)
  } 
   
  test("sentence combo") {
    val sentence = List("Yes", "man")
  
    assert(sentenceAnagrams(sentence).toSet == (List(
         List("en", "as", "my"),
         List("en", "my", "as"),
         List("man", "yes"),
         List("men", "say"),
         List("as", "en", "my"),
         List("as", "my", "en"),
         List("sane", "my"),
         List("Sean", "my"),
         List("my", "en", "as"),
         List("my", "as", "en"),
         List("my", "sane"),
         List("my", "Sean"),
         List("say", "men"),
         List("yes", "man")
       )).toSet)
   
  }

 
  
  test("subtract: lard - r2") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 2))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1), ('r',1))
    assert(subtract(lard, r) === lad)
  }

   test("subtract: Nil") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = Nil
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lard)
  }


  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }

  test("combinations: abba") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }



  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }  

   test("sentenceAnagrams: Yell Xerxes") {
      assert(sentenceAnagrams(List("Yell", "Xerxes")).toSet==Set(List("yell", "Xerxes"), List("sex", "yell", "Rex"), List("Lyle", "sex", "Rex"), List("Xerxes", "yell"), List("Lyle", "Xerxes"), List("yell", "sex", "Rex"), List("Lyle", "Rex", "sex"), List("Rex", "yell", "sex"), List("Rex", "sex", "yell"), List("sex", "Rex", "yell"), List("yell", "Rex", "sex"), List("sex", "Lyle", "Rex"), List("Rex", "Lyle", "sex"), List("sex", "Rex", "Lyle"), List("Rex", "sex", "Lyle"), List("Xerxes", "Lyle")))
    }
  
}
