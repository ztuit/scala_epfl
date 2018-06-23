


package recfun
import common._
import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    
    def rpascal(c : Int, r : Int) : Int =
    		if(c==0 || r==0 || (r==c)) 1 else rpascal(c-1,r-1)+rpascal(c,r-1)
    		
    if(c < 0 || r < 0) { 
      0
    } else {
    	rpascal(c,r)
    }
  }
  


  /**
   * Exercise 2
   */
  
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def rbalance(chars : List[Char], count : Int) : Boolean = {
    	if(count<0) { 
    	  false
    	} else {
		    chars match {
		      case h :: t if(h=='(') => rbalance(t,count+1)
		      case h :: t if( h==')') => rbalance(t,count-1)
		      case h :: nil if( h==')') => count==0
		      case h :: t => rbalance(t,count)
		      case _ => count==0
		    }
    	}
    }
    rbalance(chars,0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
 
    def countChangeRec(money : Int, coins : List[Int], solutions : Int ) : Int = {
    	if(money==0) { 
    	  solutions+1
    	} else {
    		loop(money, coins, solutions)
    	}
    }
    
    def loop(money : Int, coins : List[Int], solutions : Int ) : Int = {
    	if(coins.size==0) {
    	  solutions
    	} else {
	       sum(coins.head,money , coins , solutions ) + loop(money, coins.tail, solutions)
    	}
    }
    
    def sum(coin : Int, money : Int, coins : List[Int], solutions : Int ) : Int = {
    	val index = coins.indexOf(coin)
	      if(money-coin >= 0) {
	        countChangeRec(money-coin, coins.drop(index), solutions)
	      }
	      else {
	        solutions
	      }
    }
    
    if(money<=0 || coins.size==0) {
    	0
    } else {
    	countChangeRec(money, coins, 0)
    }
    
  }
  
}
