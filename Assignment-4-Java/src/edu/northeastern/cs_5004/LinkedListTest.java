/* LinkedListTest.java
 * Test all function
 * @author yu2749luca, hokang yu
 * @date 02/04/2019
 */

package edu.northeastern.cs_5004;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;




public class LinkedListTest {
	private int[] input = {2,1,6,3,4,7,6,6,6,0};
	
	/*
	 * Test add null to List
	 */
	@Test
	public void test_addNull() {
		int size = 10;
		boolean testTrue = false;
		Integer get = 0;
		LinkedList <Integer> intList = new LinkedList <Integer>();
		for(int i =0; i < size;i++) {
			testTrue = intList.add(null);
			assertTrue(testTrue);
		}
		get = intList.get(3);
		assertNull(get);
		
	}
	/*
	 * Test LinkedList with Integer & String
	 */
	@Test
	public <T> void test_LinkedList() {
		Integer val = 0;
		boolean bool=false;
		boolean testEqual=false;
		int size =0;
		int front=0;
		int back=0;
		
		LinkedList<Integer> intList = new LinkedList<Integer>();
		
		/*
		 * Test Constructor
		 */
		bool = intList.isEmpty();
		size = intList.size();		
		assertTrue(bool);
		assertEquals(0,size);
		
		/*
		 * ArrayList = {2,1,6,3,4,7,6,6,6,0}
		 * Test Add
		 */
		for(int i=0;i<input.length;i++) {
			bool = intList.add((Integer) input[i]);
			assertTrue(bool);
			val = intList.get(i);
			testEqual = val.equals(input[i]);
			assertTrue(testEqual);
			assertFalse(intList.isEmpty());
			bool = intList.add(50, input[i]); //out of bound
			assertFalse(bool); // should not be added
			bool = intList.add(-5, input[i]); //negative index should not be added
			assertFalse(bool);
		}
		/*
		 * Test indexOf & and lastIndexOf
		 */
		assertEquals(input.length,intList.size());
		front = intList.indexOf((Integer) 6);
		back = intList.lastIndexOf((Integer) 6);
		assertEquals(2,front);
		assertEquals(8,back);
		
		bool = intList.add(4,5);
		val = intList.get(4);
		testEqual = val.equals((Integer) 5);
		assertTrue(bool);
		assertTrue(testEqual);
		assertEquals(input.length+1, intList.size());
		
		/*
		 * Test Remove
		 */
		val = intList.remove(4);
		testEqual = val.equals((Integer) 5);
		assertTrue(testEqual);
		val = intList.get(4);
		testEqual = val.equals((Integer) 5);
		assertFalse(testEqual);
		
		/*
		 * Test set
		 */
		val = intList.set(2, 7);
		assertEquals((Integer)6,val);
		val = intList.get(2);
		assertEquals((Integer)7,val);

		
		/*
		 * Test Clear & out of bound
		 */
		intList.clear();
		assertTrue(intList.isEmpty());
		assertEquals(0,intList.size());
		
		for(int i=10;i>=0;i--) {
			val = intList.get(i);
			//make sure return null if not value get be get
			assertNull(val);
		}
		
		/*
		 * Test linkedList with String
		 */
		size = 10;
		LinkedList<String> stringList = new LinkedList<String>();
		for(int i=0;i<size;i++) {
			String newString = "There are "+i+" human(s) in the classroom.";
			boolean test = stringList.add(newString);
			String getString = stringList.get(i);
			boolean equalString = newString.equals(getString);
			assertTrue(equalString);
			assertTrue(test);
			assertFalse(stringList.isEmpty());
		}
		
		assertEquals(size,stringList.size());
		
		String newString = "TA NEIL & TA MA ARE AWESOME";
		stringList.set(5, newString);
		String getString = stringList.get(5);
		boolean equalString = newString.equalsIgnoreCase(getString);
		assertTrue(equalString);
		
		
		stringList.set(8, newString);
		front = stringList.indexOf(newString);
		back = stringList.lastIndexOf(newString);
		assertEquals(5,front);
		assertEquals(8,back);
		

	}
	/*
	 * Test Linked List Iterator
	 */
	@Test
	public void testIterator() {
		
		/*
		 * {2,1,6,3,4,7,6,6,6,0}
		 */
		
		LinkedList <Integer> linkedList = new LinkedList<Integer>();
		for (int i =0;i<input.length;i++) {
			boolean test = linkedList.add((Integer) input[i]);
			assertTrue(test);
			assertFalse(linkedList.isEmpty());
		}
		//create iterator
		ListIterator<Integer> a = linkedList.listIterator();
		
		for (int i =0;i<input.length;i++) {
			Integer val = a.next();
			assertEquals(val,linkedList.get(i));
			assertEquals(input.length,linkedList.size());
		}

		for (int i=linkedList.size()-1;i>=0;i--) {
			Integer tempt = a.previous();
			assertEquals(tempt,linkedList.get(i));
			assertEquals(input.length,linkedList.size());
		}
		
		
		
		/*
		 * call next 4 times, and ensure get previous return last Next()
		 */
		a.next();a.next();a.next();
		Integer val = a.next();
		Integer val2 = a.previous();
		assertEquals(val,val2);
		
		assertEquals(val2,a.next()); // 3 
		
		/* 
		 * Test Iterator Optional Functions
		 * add value at curIndex 
		 * { 2  1  6  3 '5'  4  7  6  6  6  0 }
		 */
		
		a.add((Integer) 5);
		assertEquals((Integer) 5,a.next()); //5 should be read immediately
		assertEquals((Integer) 5,a.previous()); 
		assertEquals((Integer) 5,a.next());
		
		a.remove(); //remove 5
		assertEquals(10,linkedList.size());
		

		assertEquals((Integer) 4,a.next()); //after 5 is removed, 4 should be read
		assertEquals((Integer) 4,a.previous()); //moving forward
		/* 
		 * Due to change of direction, 5 should be added before 4
		 * { 2  1  6  3  '5'  4  7  6  6  6  0 }
		 */
		a.add((Integer) 5); 
		assertEquals((Integer) 5,a.previous()); 
		a.remove(); //{ 2  1  6  3  ' '  4  7  6  6  6  0 }
		assertEquals((Integer) 4, a.next());
		assertEquals((Integer) 4, a.previous()); //previous should get last called next
		assertEquals((Integer) 3, a.previous()); //previous should get last called next
		a.set((Integer) 8);
		assertEquals((Integer) 8,a.next()); // call previous, then call next, index should be the same
		a.set((Integer) 3);
		assertEquals((Integer) 3, a.previous());
		
	}
	
	/*
	 * Test Optional Functions
	 */
	@Test
	public void test_Optional() {
		
		/*
		 * {2 1 6 3 4 7 6 6 6 0}
		 */
		LinkedList <Integer> linkedList = new LinkedList<Integer>();
		for (int i =0;i<input.length;i++) {
			boolean test = linkedList.add((Integer) input[i]);
			assertTrue(test);
			assertFalse(linkedList.isEmpty());
		}
		//create iterator
		ListIterator<Integer> a = linkedList.listIterator();
		
		System.out.println("\n\nTesting LinkedListIterator Optional Function:\n");
		linkedList.print();
		System.out.println("nextIndex: "+a.nextIndex()+", Call next: "+a.next());
		System.out.println("call remove");
		a.remove();
		linkedList.print();
		System.out.println("last called next should be removed\n");
		
		System.out.println("now call next again, index should be re-adjusted");
		System.out.println("nextIndex: "+a.nextIndex()+", Call next: "+a.next());
		System.out.println("now call previous, values should be same as last next");
		System.out.println("prevIndex: "+a.previousIndex()+", Call previous: "+a.previous());
		System.out.println("\nNow add (2), 2 should be added before 1 due to current direction");
		a.add((Integer)2);
		linkedList.print();
		System.out.println("prevIndex: "+a.previousIndex()+", Call previous: "+a.previous());
		System.out.println("nextIndex: "+a.nextIndex()+", Call next: "+a.next());
		
		System.out.println("\nNow set/replace last called item with value 5");
		a.set((Integer) 5); 
		linkedList.print();
		System.out.println("prevIndex: "+a.previousIndex()+", Call previous: "+a.previous());
		System.out.println("\nNow set/replace last called item with value 2");
		a.set((Integer) 2);
		linkedList.print();
		
		
		
		
		
	}
	/**
	 * Run the tests in this class.
	 * 
	 * @param args the program arguments
	 */
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(LinkedListTest.class);
	    
	    System.out.println("[Unit Test Results]");
	    System.out.println();
	    
	    if (result.getFailureCount() > 0) {
	    	System.out.println("Test failure details:");
		    for (Failure failure : result.getFailures()) {
		       System.out.println(failure.toString());
		    }
		    System.out.println();
	    }
	    
	    int passCount = result.getRunCount()-result.getFailureCount()-result.getIgnoreCount(); 
	    System.out.println("Test summary:");
	    System.out.println("* Total tests = " + result.getRunCount());
	    System.out.println("* Passed tests: " + passCount);
	    System.out.println("* Failed tests = " + result.getFailureCount());
	    System.out.println("* Inactive tests = " + result.getIgnoreCount());
	}

	
}
