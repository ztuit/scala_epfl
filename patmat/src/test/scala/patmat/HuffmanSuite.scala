package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }
  
  test("singleton of nil") {
	new TestTrees {
	  assert(singleton(Nil)==false)
	}  
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }
  
  test("maketree") {
      new TestTrees {
    	  val sampleTree = makeCodeTree(Leaf('x', 2), Leaf('e', 3))
    	  assert(weight(sampleTree) === 5)
      }
  }

    test("times") {
      new TestTrees {
    	  
    	  assert(times(List('a', 'b', 'a')) === List(('a', 2), ('b', 1)))
      }
  }
  
  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
     new TestTrees {
	    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
	   
	    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
     }
   }
  
    test("combine of some leaf list ordering") {
     new TestTrees {
	    val leaflist = List(Leaf('e', 3), Leaf('t', 4), Leaf('x', 5))
	   
	    assert(combine(leaflist) === List(Leaf('x',5), Fork(Leaf('e',3),Leaf('t',4),List('e', 't'),7)))
     }
   }
  
    test("combine of nil") {
     new TestTrees {
	    assert(combine(Nil) == Nil)
     }
   }
  
  test("create code tree") {
     new TestTrees {
    	 
    	 assert(createCodeTree(List('c', 'a', 'c', 'b','c', 'a'))==Fork(Fork(Leaf('b',1),Leaf('a',2),List('b', 'a'),3),Leaf('c',3),List('b', 'a', 'c'),6))
     }
  }
  
  test("decode secret") {
    new TestTrees {
     assert(decodedSecret==List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
    }
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }
  
  test("encode some text with frenchCode") {
    new TestTrees {
      assert(decode(frenchCode,encode(frenchCode)(List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l')))==List('h', 'u', 'f', 'f', 'm', 'a', 'n', 'e', 's', 't', 'c', 'o', 'o', 'l'))
    }
  }
  
   test("Quick Encode and decode") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }
}
