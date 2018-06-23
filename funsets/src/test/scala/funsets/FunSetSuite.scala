package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s7 = singletonSet(7)
    val s401 = singletonSet(401)
    val s1000 = singletonSet(1000)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  

  
  test("intesection contains elements in both") {
    new TestSets {
      val sU1 = union(s1, s2)
      val sU2 = union(s3, s1)
      val sI = intersect(sU1,sU2)
      assert(contains(sI, 1), "Union 1")
      assert(!contains(sI, 2), "Union 2")
      assert(!contains(sI, 3), "Union 2")
    }
  }
  
  test("difference contains elements in s but not in t") {
    new TestSets {
      val sU1 = union(s1, s2)
      val sU2 = union(s3, s5)
      val sI = diff(sU1,sU2)
      assert(contains(sI, 1), "Union 1")
      assert(contains(sI, 2), "Union 1")
      assert(!contains(sI, 3), "Union 2")
      assert(!contains(sI, 4), "Union 2")
    }
  }
  
  test("filter by predicate over unioned sets") {
    new TestSets {
      val sU1 = union(s1, s2)
      val sU2 = union(s3, s5)
      val sU3 = union(sU1, sU2)
      def p = (x : Int) => if(x >1 && x < 4) true else false
      val sF = filter(sU3, p )
      assert(!contains(p, 1), "filter 1")

      assert(!contains(sF, 1), "filter 1a")
      assert(contains(sF, 2), "filter 1b")
      assert(contains(sF, 3), "filter 2a")
      assert(!contains(sF, 4), "filter 2b")
    }
  }
  
  test("for all sets") {
    new TestSets {
      val sU1 = union(s1, s2)
      val sU2 = union(s3, s4)
      val sU3 = union(sU1, sU2)
      def p = (x : Int) => if(x >1 && x < 4) true else false
      assert(!forall(sU3, p ))
      def p1 = (x : Int) => if(x >=1 && x <= 4) true else false
      assert(forall(sU3, p1 ))
      def p2 = (x : Int) => if(x <= 400) true else false
      assert(forall(sU3, p2 ))
      def p3 = (x : Int) => if(x > 10) true else false
      assert(!forall(sU3, p3 ))
      val s401Plus = union(sU3, s401)
      assert(!forall(s401Plus, p2 ))
    }
  }
  
  test("exists in the set") {
    new TestSets {
      val sU1 = union(s1, s2)
      val sU2 = union(s3, s5)
      val sU3 = union(sU1, sU2)
      def p = (x : Int) => if(x >1 && x < 4) true else false
      assert(exists(sU3, p ), " 2 and 3 exist")
      def p1 = (x : Int) => if(x ==1 ) true else false
      assert(exists(sU3, p1 ),"x==1 exist")
      def p2 = (x : Int) => if(x <= 400) true else false
      assert(exists(sU3, p2 )," less than 400 exist")
      def p3 = (x : Int) => if(x > 10) true else false
      assert(!exists(sU3, p3 )," Nothing greater than 10")
      val s401Plus = union(sU3, s401)
      assert(exists(s401Plus, p3 )," Now has greater than 10")
    }
  }
  
  test("map the set") {
    new TestSets {
      val sU1 = union(s1, s2)
      val sU2 = union(s3, s5)
      val sU3 = union(sU1, sU2)
      def f = (x : Int) => 500
      val mapped = map(sU3, f)
      
      assert(!exists(mapped, x => x < 500), " Nothing less than 500 ")
      assert(exists(mapped, x => x == 500), " 500 exists ")
    }
  }
  
    test("map the set2") {
    new TestSets {
      val sU1 = union(s1, s3)
      val sU2 = union(s4, s5)
      val sU3 = union(s7, s1000)
      val sU4 = union(sU1,sU2)
      val sU5 = union(sU4,sU3)
      def f = (x : Int) => x-1
      val mapped = map(sU5, f)
      assert(exists(mapped, x => x == 0), " 0 exist ")
      assert(exists(mapped, x => x == 999), " 999 exists ")
    }
  }
}
