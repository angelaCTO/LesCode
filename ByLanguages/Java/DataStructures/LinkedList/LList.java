/* Angela To
 */
import java.util.*;

 public class LList<E> implements java.util.List<E> {
 	private SLNode <E> head;
 	int modified = 0;
 	int size = 0;

 	public LList() {
 		// Empty Constructur
 	}

 	/* Method Implementations for List Operations */ 
 	public boolean addAll(Collection<? extends E> c) {
 		throw new UnsupportedOperationException();
 	}

 	public boolean containsAll(Collection<?> c) {
 		throw new UnsupportedOperationException();
 	}

 	public boolean equals(Object o) {
 		throw new UnsupportedOperationException();
 	}

 	public int hashCode() {
 		throw new UnsupportedOperationException();
 	}

 	public int indexOf(Object o) {
 		throw new UnsupportedOperationException();
 	}
 	public boolean isEmpty() {
     	throw new UnsupportedOperationException ();
  	}
  
    public int lastIndexOf (Object o) {
     throw new UnsupportedOperationException ();
  	}

  	public ListIterator<E> listIterator() {
    	throw new UnsupportedOperationException ();
  	}

  	public ListIterator<E> listIterator (int index) {
     	throw new UnsupportedOperationException ();
  	}

  	public boolean removeAll (Collection <?> c) {
    	 throw new UnsupportedOperationException ();
  	}

  	public boolean retainAll (Collection<?> c) {
    	throw new UnsupportedOperationException ();
  	}
 
   	public List<E> subList (int fromIndex, int toIndex) {
    	throw new UnsupportedOperationException ();
  	}

  	public Object[] toArray() {
    	throw new UnsupportedOperationException ();
  	}
  
  	public <T> T[] toArray(T[] a) {
    	throw new UnsupportedOperationException ();
  	}


    /**
     * Implementation for clear() method
     * Pre-condition:	 None
     * Responsibilities: Removes all the elements from the list
     * Post-condition:   List is empty. Size of list is 0
     */ 	
    public void clear() {
    	/* Set head node to null so rest of list will be GC */
    	head.setNext(clear);
    }


    /**
     * Implementation for add(Object o) method
     * pre-condition: none
     * responsibilities: appends element into the list at the LAST index
     * post-conditions: element is inserted into the list at index 
     *                  list.size() - 1
     * @param	o 		object to be added to the END of the list
     * @return			returns a boolean value TRUE if add operation was
     *					successfully
     */
    public boolean add (E o) {
    	/* Keeps track of mutator methoid add was called during iteration
    	   else throws a ConcurrentModificationException */
    	modified++;
    	/* Create a new node using the passed object. Declare the new node's
    	   "next" pointer to null, as it currently points to nothing */
    	SLNode<E> newNode = new SLNode<E>(o, null);
    	/* If the list is empty, set the new node as the HEAD of the list */
    	if (size() == 0) {
    		head = newNode;
    		size++;
    	}
    	else {
    		/* Set the head node to be referenced by "current" node */
    		SLNode<E> current = head;
    		/* Loop through the list, setting each node in the next iteration 
    		   to be the "current" node until the last element has been 
    		   reached */
    		while (current.getNext() != null) {
    			current = current.getNext();
    		}
    		/* Set the last node to point to the new node */
    		current.setNext(newNode);
    		size++;
    	}
    	/* If operation was successful, return true */
    	return true;
    }


	/**
	 * Implementation for add(Index i, E e) method
	 * pre-condition: 0 <= index <= size
	 * responsibilities: inserts the passed element at the passed index
	 * post-conditions: element is inserted into the list at the correct 
	 *					index
   	 *  @param     e 	element to be inserted in to the list
   	 *  @return         void 
   	 *  @throws    IndexOutOfBoundsException  throws if passed index is out 
   	 *             of range 
	 */
	public void add(int index, E e) {
		modified++;
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		/* Handle the event in which the client is trying to append element
		   to the END of the list. We call add(e) to append the passed 
		   element to the end of the list. */
		else if (index == size()) {
			add(e);
			size++;
			return;
		}
		else {
			/* Create a new node object to hold the passed element */
			SLNode<E> newNode = new SLNode<E>(e, null);
			/* Trying to add to the first index in a non-empty list */
			if (index == 0) {
				newNode.setNext(head);
				head = newNode;
				size++;
			}
			/* Trying to add to the interior of the list*/
			else {
				SLNode<E> current = head;
				/* Iterate through the list until we reach the node just 
				   before the passed index */
				for (int listIndex = 0; listIndex < index - 1; listIndex++) {
					current = current.getNext();
				}
				/* If current is not the FIRST item, then set the newNode in
				   between the current node and the current's next node */
				if (current.getNext() != null) {
					newNode.setNext(current.getNext());
					size++;
				}
				/* Set current's next to reference the newNode */
				current.setNext(newNode);
				size++;
			}
		}
	}


	/** 
	 * Implementation for contains(o) method
	 * pre-condition: 	 none
	 * responsibilities: tests if passed element is found within the list
	 * post-conditions:	 returns a boolean value to indicate whether element 
	 *					 is found in list 
	 *  @param      o        object to test if contained in the list
   	 *  @return     true     returns true if passed object is found
   	 *  @return     false    returns false if passed object is not found 
	 */
	public boolean contains (Object o) {
		/* Check if list is empty */
		if (head == null) {
			return false;
		}
		else {
			/* Create a new node, "current", and set it to reference the 
			   head */
			SLNode<E> current = head;
			/* Loop through the list to get to the last element */
			while (current.getNext() != null) {
				/* Check if the element stored in the current node is equal 
				   to the passed element value */
				if ((current.getItem()).equals(o)) {
					return true;
				}
				/* Increment to the next node */
				current = current.getNext();
			}
			/* Check if the last node contains the element */
			if ((current.getItem()).equals(o)) {
				return true;
			}
		}
		/* The list dies not contain the value */
		return false;
	}


	/**
	 * Implementation for get(i) method
	 * pre-condition:	 0 <= index <= size
	 * responsibilities: retrieves the node's value at the passed index
	 * post-condition: 	 the list remains unchanged
	 *  @param   index      index of list from which the element should
     *                      retrieved from
     *  @return  element    returns the element stored at the passed 
     *                      index
     *  @throws             an IndexOutOfBoundsException if 
     *                      passed index is not within range
	 */
	public E get (int index) {
		/* Check if index is invalid */ 
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		else {
			/* Create a new node, "current" and set it to reference the head 
			   node */
			SLNode<E> current = head;
			/* Loop through the list until passed index */
			for (int listIndex = 0; listIndex < index; listIndex++) {
				/* Increment to get to the last node */
				if (currnet.getNext() != null) {
					current.getNext();
				}
			}
			/* Return element of node at passed index */
			return current.getItem();
		}
	}


   /**
    *  Implementation for iterator, which creates an iterator object which
    *  iterates through the list collection
    *  pre-condition:      0 <= index <= size
    *  responsibilities:   create a ListIterator object to access the elements 
    *                      in the list
    *  post-condition:     a ListIterator is created, the list is unchanged
    *  @return             a ListIterator
    *  @throws             an IndexOutOfBoundsException if precondition is not
    *                      met
    */
   public iterator<E> iterator() {
   		return new ListIterator();
   }


  /**
   *  Method implementation for remove(o) method
   *  pre-condition:      none
   *  responsibilities:   removes the FIRST occurence of the object o in the 
   *                      list
   *  post-conditions:    list is removed of the FIRST occurence of the element
   *                      if found in list. Size of list decrements by one.
   *  @param     o        first occurence of object o is removed
   *  @return    true     returns a boolean value, true, if the object
   *                      was found and removed
   *  @return    false    returns a boolean value, false, if the object
   *                      was not found. Nothing is removed, the list 
   *                      remains unchanged
   */
  public boolean remove(Object o) {
  	boolean found = false;
  	/* Create a new node, "current", and set to reference the head node */
  	SLNode<E> current = head;
  	modified++;
  	while (current.getNext() != null) {
  		/* Store the previous node into current node */
  		SLNode<E> prevNode = current;
  		/* Check to see if current element is equal to the passed object */
  		if ((current.getItem()).equals(o)) {
  			found = true;
  			SLNode<E> toBeRemoved = current;
  			SLNode<E> newNode = toBeRemoved.getNext();
  			prevNode.setNext(newNode);
  			/* Remove node */
  			toBeRemoved.setNext(null);
  			size--;
  			break;
  		}
  		/* Continue to iterate through the rest of the list */
  		current = current.getNext();
  	}
  	return found;
  }


  /** Implementation for remove(i) method, which removes the elment 
   *  found at the passed index
   *  pre-condition:      0 < index < size
   *  responsibilities:   removes the element found at the passed index
   *  post-condition:     element is removed from index in list. Size decrements
   *                      by one
   *  @return             the element removed from the list
   *  @throws             an IndexOutOfBoundsException if precondition is not
   *                      met
   */
  public E remove (int index) {
  	modified++;
  	/* Check if passed index is invalid */
  	if (index < 0 || index >= size()) {
  		throw new IndexOutOfBoundsException();
  	}
  	else {
  		SLNode<E> current = head;
  		/* Handle the case in which we are trying to remove the first element 
  		   in the list */
  		if (index == 0) {
  			head = current.getNext();
  			current.setNext(null);
  			/* Get the value of the node to be removed to be returned */
  			E valueRemoved = current.getItem();
  			size--;
  			return valueRemoved;
  		}
  		/* Handle other cases */
  		for (int listIndex = 0; listIndex < index; listIndex++) {
  			current = current.getNext();
  		}
  		/* Set the next node following the cursor to be the item set to be 
  		   removed */
  		SLNode<E> toBeRemoved = current.getNext();
  		SLNode<E> newNext = toBeRemoved.getNext();
  		/* Set the current node to reference the new "next" node, bypassing 
  		   the node to be removed */
  		current.setNext(newNext);
  		E valueRemoved = toBeRemoved.getItem();
  		toBeRemoved.setNext(null);
  		size--;
  		return valueRemoved;
  	}
  }


  /** Implementation for set(i, e) method
   *  pre-condition:     0 < index < size
   *  responsibilities:  sets into the list a new node containing the passed
   *                     element, but replaces the previous node at passed index
   *  post-conditions:   new node is appended into list at passed index. Size
   *                     of list is unchanged
   *  @param    index    the index the new node is set to replace at
   *  @param    e        the new element contained in the new node
   *  @return            the value replaced by the new element
   *  @throws            an IndexOutOfBoundsException if the preconditions are
   *                     not met  
   */
public E set (int index, E e) {
	modified++;
	if (index < 0 || index >= size()) {
		throw new IndexOutOfBoundsException();
	}
	else {
		SLNode<E> current = head;
		/* Loop through the list until passed index */
		for (int listIndex = 1; listIndex <= index; listIndex++) {
			current = current.getNext();
		}
		E valueReplaced = current.getItem();
		current.setItem(e);
		return valueReplaced;
	}
}


  /** 
   *  Implementation for size() method
   *  Precondition:      size >= 0 at all times
   *  Responsibilities:  returns the number of elements in the list
   *  Post-condition:    list is unchanged
   *  @return            the size of the list  
   */
   public int size(){
      return size;
  }
  

  /* ---------------------------------------------- */
  /*		Inner class SLNode of LList Class		*/
  /* ---------------------------------------------- */
  public class SLNode<E> {
  	E item;
  	SLNode<E> next;

  	public SLNode(E item, SLNode<E> next) {
  		this.item = item;
  		this.next = next;
  	}

  	public void setItem(E item) {
  		this.item = item;
  	}

  	public E getItem() {
  		return item;
  	}

  	public void setNext(SLNode<E> next) {
  		this.next = next;
  	}

  	public SLNode<E> getNext() {
  		return next;
  	}
  } 


  /* --------------------------------------------------------------------- */
  /* Inner class LstIterator of LList Class implements iterator interface */
  /* --------------------------------------------------------------------- */
  public class LstIterator implements Iterator<E> {
  	SLNode<E> itPrev; 
  	SLNode<E> itCurr;
  	SLNode<E> itNext;

  	/* Keep track of how many times next() is called */
  	int nextCount = 0; 
  	/* Keep track of how many times a L
ListMutator method has been called */
  	int expectedModCount = modified;

    /* Keep track of whether a call to an iterator operation has been called. 
  	   If it is false then we are removing the head node */
  	boolean firstCall = false;
  	boolean nextCalled = false;
  	boolean removeCalled = false;

  	/**
  	 * Constructor for LstIterator
  	 */
  	public LstIterator() {
  		itPrev = null;
  		itCurr = head;
  		itNext = null;
  	}

  	/**
     *  Method hasNext() of LstIterator interface
     *  Pre-conditions:    None
     *  Responsibilities:  Checks if there exists a "next" element.
     *  PostConditions:    List is unchanged
     *  @return    true    if there are additional elements following current
     *                     node
     *  @return    false   if node is the last in the list, or if list is empty
     */
  	public boolean hasNext() {
  		if (itCurr.getNext() == null) {
  			return false;
  		}
  		else {
  			return true;
  		}
  	}

    /**
     *  Iterator method, next() of LstIterator
     *  Pre-conditions:   None
     *  Responsibilties:  Returns the next item contained in the next node if
     *                    there exists one. Throws exception if next() is called
     *                    on a node with no following nodes
     *  PostConditions:   List is unchanged
     *  @return           Returns the next item contained in the next node
     *  @throw            a NoSuchElementException if no more elements for  
     *                    next() to be called on
     */
    public E next() {
    	itPrev = itCurr;
    	if (itCurr.getNext() != null) {
    		if (!firstCall) {
    			nextCount++;
    			firstCall = true;
    			/* Return the head element */
    			return itPrev.getItem();
    		}
    		itCurr = itCurr.getNext();
    		nextCount++;
    		return itCurr.getItem();
    	}
    	else {
    		throw new NoSuchElementException();	
    	}
    }

   /**
     *  Iterator method, remove(), of LstIterator
     *  Pre-conditions:   A previous call to next() and NO other previous call
     *                    to itself per call to next()
     *  Responsibilties:  Iterator method remove() removes the last item 
     *                    returned by a call to iterator's next() method
     *  PostConditions:   List is removed of last returned node
     *  @return           the value removed
     *  @throw            a ConcurrentModificationException if a LList mutator 
     *                    method is called during iteration
     */
  	public void remove() {
  		if (expectedModCount != modified) {
  			throw new ConcurrentModificationException();
  		}
  		if (nextCount != 0) {
  			/* Handle the case in which it is the first node that is being
  			   removed */
  			if (itCurr.equals(head)) {
  				itNext = itCurr.getNext();
  				head = itNext;
  				itCurr.setNext(null);
  				size--;
  			}
  			/* Handle the case that it is an interior node that is being 
  			   removed */
  			else {
  				itNext = itCurr.getNext();
  				/* Bypass the current node to be removed */
  				itPrev.setNext(itNext);
  				/* Remove current node */
  				itcurr.setNext(null);
  				itCurr = itNext;
  				size--;
  			}
  		}
  		else {
  			throw new IllegalStateException();
  		}
  	}

  } // Close LstIterator
 } // EOF
