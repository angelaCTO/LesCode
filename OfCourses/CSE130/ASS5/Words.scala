import scala.collection.immutable.HashMap
import scala.util.matching.Regex
import java.io.PrintWriter
import Lines._

object Words {

  /* Takes as input a filename and returns an iterator over the words
   * in the file converted to lowercase
   */
  // TODO Error
  def apply(file: String) : Iterator[String] =  
    apply(file).toList.filter(_.toLowerCase()).toIterator
  
  // TODO Idk
  def groupFreq[A,B](xs: Iterator[A], f: A => B): HashMap[B, Int] = 
 
  val inti = List(1,21,5,39,12,7,92)

  def isEven(x: Int): String =
    if ((x % 2) == 0) { "Even" } else { "Odd" } 

  def sizeFreq(file: String): HashMap[Int, Int] = 
    groupFreq[String, Int] (apply(file), (x => x.length));

  // TODO Idk
  def charFreq(file: String): HashMap[Char, Int] = 
  {
    val chars   = sys.error("TO BE WRITTEN")
    val grouper = sys.error("TO BE WRITTEN")
    groupFreq(chars, grouper) 
  }

  def wordsOfSize(file: String, size: Int) : Iterator[String] = 
   apply(file).toList.filter(_.length() == size).toIterator

  def wordsWithAllVowels(file: String): Iterator[String] = 
    apply(file).toList.map(_.toLowerCase()).filter((x:String) =>
    (x.contains("a") &&
     x.contains("e") &&
     x.contains("i") &&
     x.contains("o") &&
     x.contains("u"))).toIterator
    
     
  def wordsWithNoVowels(file: String): Iterator[String] = 
    apply(file).toList.map(_.toLowerCase()).filter((x:String) =>
    (!x.contains("a") &&
     !x.contains("e") &&
     !x.contains("i") &&
     !x.contains("o") &&
     !x.contains("u"))).toIterator
 
  // TODO Error
  def wordsMatchingRegexp(file: String, re: Regex): Iterator[String] = 
    apply(file).toList.filter((x:String) => 
      (!((re findAllIn(x)).isEmpty))).toIterator

}

// vim: set ts=2 sw=2 et:f
