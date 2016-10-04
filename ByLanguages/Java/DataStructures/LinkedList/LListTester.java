import java.util.*;
import java.io.*;
import junit.framework.*;
import org.junit.Test;

 /* Class LListTester tests the implemention the Java Collections Interface 
  * List <E>, and implements a test plan using the JUnit Framework. */  
public class LListTester extends junit.framework.TestCase { 

/*---------------------------------------------------------------------------*/
/*					         TEST BOOLEAN ADD                                */
/*---------------------------------------------------------------------------*/
  /** This method testBooleanAddContains() tests to check if item appended by 
    * add() method (of the java.util.List<E> interface) into list is actually 
    * contained in the list after the operation.
    * @see java.util.List<E> 
    */
  public void testBooleanAddContains() { 
    List<Integer> myList= new LList<Integer>();
    myList.add(1);
    myList.add(2);
    myList.add(3);
    assertTrue(myList.contains(1));
    assertFalse(myList.contains(4));
  } 

  /** This method testBooleanAddReturns() tests to check if boolean add() 
    * method (of the java.util.List<E> interface) returns true if element 
    * was successfully appended to list.
    * @see java.util.List<E> */
  public void testBooleanAddReturns() {
    List<Integer> myList= new LList<Integer>();
    assertTrue(myList.add(6));
    assertTrue(myList.add(7));
  }

  /** This method, testBooleanAddSize(), tests to check if boolean add()
    * method (of the java.util.List<E> interface) dynamically 
    * changes in size (an increase in size) when elements are appended 
    * @see java.util.List<E>. */
  public void testBooleanAddSize() {
    List<Integer> myList= new LList<Integer>(); 
    for (int i = 0; i < 3; i++) {
      int myListSize =  myList.size();
      myList.add(i);
      assertEquals(new Integer(myListSize + 1) ,new Integer(myList.size()));
    }
  }

  /** This method, testBooleanAddItemToEnd(), tests to check if boolean 
    * add() method (of the java.util.List<E> interface) appends new 
    * elements to the END of the list as required by the specification
    * @see java.util.List<E> */   
  public void testBooleanAddItemToEnd() {
    List<Integer> myList = new LList<Integer>();
    for(int i = 0; i < 3; i++) {
      myList.add(i);
      assertEquals(new Integer(i), new Integer(myList.get(myList.size() - 1)));
    }
  }

    /** This method, testBooleanAddException(), tests to check if boolean
      * add() method (of the java.util.List<E> interface) throws an exception
      * (specifically a NullPointerException)if a null character is appended
      * to list
      * @see java.util.List<E>
      */
    public void testBooleanAddException() {
	    List<Integer> myList= new LList<Integer>();
	    try{
	      myList.add(null);
	    }
	    catch(NullPointerException e) {
	      // Test Passes
	    }
	    catch(IllegalArgumentException e) {
	      // Test Passes
	    } 
	    catch (Throwable e){
	      fail("An unexpected exception was thrown.");
	    }
  }

/*---------------------------------------------------------------------------*/
/*					           TEST VOID ADD                                 */
/*---------------------------------------------------------------------------*/
  /** This method, testVoidAddCorrectValueAtIndex() tests to check if void
    * add() method (of the java.util.List<E> interface) appends the correct 
    * passed value at the specified passed index by comparing it to the
    * retrieved value
    * @see java.util.List<E> */
  public void testVoidAddCorrectValueAtIndex() { 
    List<Integer> myList= new LList<Integer>();  
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        myList.add(i,j);
        assertEquals(new Integer(j), new Integer(myList.get(i)));
      }
    }
  }

  /** This method, testVoidAddIndexShifts() tests to check if void add() method
   * (of the java.util.List<E> interface) shifts indices right after an element
   * is appended (in an interior index) 
   * @see java.util.List<E> */
  public void testVoidAddIndexShifts() {
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 10; i++) {
       myList.add(i);
    }
    myList.add(1,100);
    for (int i = 2; i < 11; i++) {
      assertEquals(new Integer(i - 1), new Integer(myList.get(i)));
    }
  }

  /* This method, testVoidAddThrowsException(), tests to check if void add() 
   * method (of the java.util.List<E> interface)throws an exception 
   * (specifically an IndexOutOfBoundsexception)if an invalid index is passed.
   * @see java.util.List<E>*/
  public void testVoidAddThrowsException() {
    List<Integer> myList= new LList<Integer>();
    try{
      myList.add(5, 1); // 5 is an invalid index 
      fail();
    }
    catch(IndexOutOfBoundsException e) {
      // test passes
    }
    catch (Throwable e){
      fail("An unexpected exception was thrown.");
    }
  }
  
/*---------------------------------------------------------------------------*/
/*			               TEST BOOLEAN CONTAINS                             */
/*---------------------------------------------------------------------------*/
  /** This method, testContainsReturn(), tests to check if contains() method 
    * (of the java.util.List<E> interface)returns true if passed element 
    * is found in list.
    * @see java.util.List<E>*/
  public void testContainsReturn() {
    List<Integer> myList= new LList<Integer>(); 
    for (int i = 0; i < 4; i++) {
      myList.add(i);
      assertTrue(myList.contains(i));
    }     
    assertFalse(myList.contains(4));
    assertFalse(myList.contains(5));
  }
/*---------------------------------------------------------------------------*/
/*					           TEST <E> GET                                  */
/*---------------------------------------------------------------------------*/
  /** This method, testGetReturnsCorrectValue(), method tests to check if 
    * get() method (of the java.util.List<E> interface) returns
    * the correct value at passed index.
    * @see java.util.List<E> */
  public void testGetReturnsCorrectValue() {
    List<Integer> myList= new LList<Integer>(); 
    for (int i = 0, j = 0; i < 10; i++){
        myList.add(i, j);
        assertEquals(new Integer(j), new Integer(myList.get(i)));
    }
  }

  /** This method, testGetThrowsException() method, tests to check 
    * if get() method (of the java.util.List<E> interface)
    * throws exception (specifically an IndexOutOfBoundsException)
    * if an invalid index is passed.
    * @see java.util.List<E> */
  public void testGetThrowsException() {
    List<Integer> myList= new LList<Integer>();
    try{
      myList.get(myList.size()); // size is an invalid index
      fail("IndexOutOfBoundsException thrown.");
    }
    catch(IndexOutOfBoundsException e) {
      // Test Passes
    }
    catch (Throwable e){
      fail("An unexpected exception was thrown.");
    }
  }

/*---------------------------------------------------------------------------*/
/*					           TEST ITERATOR                                 */
/*---------------------------------------------------------------------------*/
 
  /** This method, testIteratorCorrectSequence(), method tests to check if 
    * Interator object returns the correct sequence of elements
    * @see java.util.List<E>*/
  public void testIteratorCorrectSequence() {
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 10; i++) {
      myList.add(i);
    }
    int i = 0;
    // Creating an iterator object with the elements from myList
    Iterator itr = myList.iterator();
    while(itr.hasNext()) {
      // Move cursor to the next object
      int element = (Integer) itr.next();
      // Check to see if iterated element matches element called by the loop
      assertEquals(new Integer(i), new Integer (element));
      i++;
    }
  }

  /** This method, testIteratorNext(), method tests to check if Interator 
    * object returns the correct next element in list
    * @see java.util.List<E>*/
  public void testIteratorNext() {
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 6; i++) {
      myList.add(i); 
    }
    Iterator itr = myList.iterator();
    /* The iteterator cursor starts at the first gap, i.e before the first 
       element. Thus calling next() on the list should return the first element
       in the list., etc.However, the calling next() on the element on the last
       element would produce an exception, thus we exclude that element from 
       this test. */
    for (int i = 0; i < 6; i++) {
      assertEquals(i, itr.next());
    }
    /* Calling next() on the last item should produce an exception 
       (specifically a NoSuchElementException). Check to make sure
       the exception will be caught. */
    try{
      itr.next();
      fail("NoSuchElementException Thrown.");
    }
    catch(NoSuchElementException e){
      // Test passes   
    }
    catch (Throwable e){
      fail("An unexpected exception was thrown.");
    }
  }
  
  /** This method, testIteratorHasNext(), method tests to check if Interator
    * object returns the correct boolean value depending on if there exists a 
    * "next" element in the list sequence.
    * @see java.util.List<E> */
  public void testIteratorHasNext(){
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 6; i++){
      myList.add(i);
    }
    Iterator itr = myList.iterator();
    /* Calling hasNext() on an interior element (an element that) has a "next"
       element following it should return true because it is true that there 
       exists a new element following it. */
    for (int i = 0; i < 5; i++){
      itr.next();
      assertTrue(itr.hasNext());
    }
    /* However, calling hasNext() on the last element in the iteration should
       return false, because the last element in the list does not have a 
       "next" term. The iteration cursur should now be before the last element. 
    */
    itr.next();
    assertFalse(itr.hasNext());  
    /* Test to see if method will throw an exception should there be no more
       elements to iterate through */
    try{
       itr.hasNext();
    }
    catch(NoSuchElementException e){
       // Test passes
    }
  }
  
  /** This method, testIteratorRemove(), method tests to check if 
    * Interator object removes the correct element, i.e. the last
    * returned value.
    * @see java.util.List<E>*/
  public void testIteratorRemove(){
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 6; i++){
      myList.add(i);
    }
    Iterator itr = myList.iterator();
    // Loop through all the interior elements including the first element
    while (itr.hasNext()){
      int nextI = (Integer) itr.next();
      itr.remove();
      assertFalse(myList.contains(nextI));
    }
  }

    /** This method, testIteratorThrowsException tests to check if remove() 
      * method throws an exception (specifically NoSuchelementException) if 
      * there are no more elements to iterate through.
      * @see java.util.List<E> */
    public void testIteratorRemovesThrowsException(){
    List<Integer> myList = new LList<Integer>();
    Iterator itr = myList.iterator();
    /* Check to see that method will throw an exception (specifically am
       IllegalStateException) remove() was called before or multiple times after
       the next() operation was called */
    try{
         itr.remove(); // No element to be removed
         fail("IllegalStateException Thrown.");
       }
       catch(UnsupportedOperationException e){
          // TestPasses
       }
       catch(IllegalStateException e){
          // Test passes
       }
       catch (Throwable e){
          fail("An unexpected exception was thrown.");
       }
  }

/*---------------------------------------------------------------------------*/
/*					         TEST BOOLEAN REMOVE                             */
/*---------------------------------------------------------------------------*/
  
  /** This method, testBooleanRemovesOnlyFirstOccurence() method (of the 
    * java.util.List<E> interface) tests to check if boolean remove() method 
    * removes the first and only the first occurence of the passed element
    * @see java.util.List<E>
    */
  public void testBooleanRemovesOnlyFirstOccurence(){
    List<Integer> myList = new LList<Integer>();
    myList.add(0);
    myList.add(1);
    myList.add(1);
    myList.add(2);
    myList.remove(1);
    assertTrue(myList.contains(1));
    assertEquals(new Integer(1), new Integer(myList.indexOf(1))); 
  }

  /** This method testBooleanRemoveReturned() method tests
    * to check if boolean remove() method returns true if the passed
    * element was successfully removed from the list
    * @see java.util.List<E>
    */
  public void testBooleanRemoveReturned(){
    List<Integer> myList = new LList<Integer>();
    myList.add(0);
    myList.add(1);
    myList.add(2);
    assertTrue(myList.remove((Integer)1));
  }

  /** This method testBooleanRemoveUnchangedIfNotfound() method tests to
    * check if boolean remove() method does not change the list if the
    * passed element is not found in list
    * @see java.util.List<E>
    */
  public void testBooleanRemoveUnchangedIfNotfound(){
    List<Integer> myList = new LList<Integer>();
    myList.add(0);
    myList.add(1);
    myList.add(2);
    int myListSize = myList.size();
    myList.remove(new Integer(3));
    assertEquals(new Integer(myListSize), new Integer(myList.size())); 
    for (int i = 0; i < 3; i++){
      assertTrue(myList.contains(i));
    } 
  }

/*---------------------------------------------------------------------------*/
/*					           TEST <E> REMOVE                               */
/*---------------------------------------------------------------------------*/
  /** This method testERemoveContains() tests to check if E remove() method 
    * actually removes the element from the list by checking if the removed
    * element  is still contained in the list (should return false)
    * @see java.util.List<E>
    */
  public void testERemoveContains() {
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 10; i++){
      myList.add(i);
    }
    myList.remove(0); 
    assertFalse(myList.contains(0));
    myList.remove(0); 
    assertFalse(myList.contains(1));
    myList.remove(0); 
    assertFalse(myList.contains(2));
  }

  /** This method, testERemoveContains() tests to check if E 
    * remove() (of the java.util.List<E> interface) method shifts the
    * indices left after the removal of an element
    */
  public void testERemoveIndexShifts(){
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 3; i++){
      myList.add(i);
    }
    myList.remove(0);
    assertEquals(new Integer(1), new Integer(myList.get(0)));
    assertEquals(new Integer(2), new Integer(myList.get(1))); 
  }

  /** This method, testERemoveContains() tests to check if E remove
    * () method returns the value of the element that was removed
    * from the list
    * @see java.util.List<E> @see java.util.List<E>
    */
  public void testERemoveReturned(){
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 3; i++){
      myList.add(i);
    }
    assertEquals(new Integer(0), new Integer(myList.remove(0)));
    assertEquals(new Integer(1), new Integer(myList.remove(0)));
  }

  /** This method, testERemoveSizeDecreases() tests to check if 
    * the E remove() method decreases in size after the removal 
    * of an element.
    * @see java.util.List<E>*/
  public void testERemoveSizeDecreases(){
    List<Integer> myList = new LList<Integer>();
    for (int i = 0; i < 3; i++){
      myList.add(i);
    }
    int myListSize = myList.size();
    myList.remove(0);
    assertEquals(new Integer(myListSize-1), new Integer(myList.size()));
  }

/*---------------------------------------------------------------------------*/
/*					              TEST SET                                   */
/*------------------------------------------- --------------------------------*/  
  /** This method, testSetReturned() tests to check if set() method returns the 
    * correct value, i.e. the item replaced, after a replacement is performed.
    * @see java.util.List<E> 
    * */
  public void testSetReturned(){
    List<Integer> myList = new LList<Integer>(); 
    for (int i = 0; i < 3; i++){
      myList.add(i);
    }
    assertEquals(new Integer(0),new Integer(myList.set(0,1)));
  }

  /** This method, testSetReplaced() tests to check if set() method
    * sets into the list the correct passed value by comparing the passed
    * with the retrieved value.
    *  @see java.util.List<E>
    * */
  public void testSetReplaced() {
    List<Integer> myList = new LList<Integer>(); 
    for (int i = 0; i < 3; i++){
      myList.add(i);
    }
    myList.set(0,1);
    assertEquals(new Integer(1), new Integer(myList.get(0)));
  }

  /** This method, testSetSizeNotChanged() tests to check if the 
    * method set() does not change the size of the list after
    * a replacement is performed. The list should contain the same
    * number of elements after the operation.
    * @see java.util.List<E>*/
  public void testSetSizeNotChanged() {
    List<Integer> myList = new LList<Integer>(); 
    for (int i = 0; i < 3; i++){
      myList.add(i);
    }
    int myListSize = myList.size();
    myList.set(0, 1);
    myList.set(1, 2);
    myList.set(2, 3);
    assertEquals(new Integer(myListSize), new Integer(myList.size()));
  }

  /** This method, testSetThrowsException(), tests to check if set() method
    * throws an exception (specifically an IndexOutOfBoundsException) if an 
    * invalid index is passed.  
    * @see java.util.List<E>*/
  public void testSetThrowsException(){
     List<Integer> myList = new LList<Integer>(); 
     for (int i = 0; i < 3; i++){
       myList.add(i);
     }
     try{
        myList.set(5, 10); // 5 is an invalid index
        fail("IndexOutOfBoundsException thrown.");
     }
     catch (IndexOutOfBoundsException e){
        // Test passes
    }
  }
   
/*---------------------------------------------------------------------------*/
/*					              TEST SIZE                                  */
/*---------------------------------------------------------------------------*/
  /**This method, testSizeIsCorrectNumberOfElements() tests to check that 
    * size() produces the correct number of elements in the list.
    * @see java.util.List<E>
    * */
  public void testSizeIsCorrectNumberOfElements(){
    List<Integer> myList = new LList<Integer>();
    int numElements = 0;
    for (int i = 0; i < 10; i++){
      myList.add(i);
    }
    for (int e: myList){
      numElements++;
    }
    assertEquals(new Integer(numElements), new Integer(myList.size())); 
  }

  /** This method, testSizeIncreasesWhenAppending() tests to check if size() 
    * method produces the right number of elements, and dynamically changes when 
    * there is an increase in the number of elements in the list, i.e. when the 
    * add()  methods are called on the list.
    * @see java.util.List<E> */
  public void testSizeIncreasesWhenAppending(){
    List<Integer> myList= new LList<Integer>(); 
    for (int i = 0; i < 10; i++){
      myList.add(i);
      assertEquals(new Integer(i+1), new Integer(myList.size()));
    }
  }

  /** This method, testSizeDecreasesWhenRemoving() tests to check if size() 
    * method produces the right number of elements, and dynamically changes 
    * to conform to the right number of elements when there is a decrease in the
    * number of elements in the list, i.e. when the remove() methods are called 
    * on the list.
    * @see java.util.List<E>*/
  public void testSizeDecreasesWhenRemoving(){
    List<Integer> myList= new LList<Integer>(); 
    for (int i = 0; i < 10; i++){
      myList.add(i);
    }
    int myListSize = 9;
    for (int i = 0; i < 5; i++ ){
      myList.remove(i);
      assertEquals(new Integer(myListSize--), new Integer(myList.size()));  
    }
  }

/*---------------------------------------------------------------------------*/
/*					                MAIN                                     */
/*---------------------------------------------------------------------------*/
  // Driver for the tester
  public static void main(String[] args) {
    junit.swingui.TestRunner.main(new String[] {"LListTester"});
  } 
} 
