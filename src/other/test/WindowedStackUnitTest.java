package other.test;



import org.junit.*;

import ruben.common.datastructures.IWindowedStack;
import ruben.common.datastructures.WindowedStack;
import static org.junit.Assert.*;


public class WindowedStackUnitTest {
	
	public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WindowedStackUnitTest.class);
    }
	
	public static void main(String args[]) {
	      org.junit.runner.JUnitCore.main("other.test.WindowedStackUnitTest");
	    }
	
	
	@Test 
	public void WindowedQueue_MinimalWindowSize_Pass()
	{
		int maxSize = 0;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		assertEquals(1, sut.window_size());
		assertEquals(0, sut.size());
	}
	
	
	@Test 
	public void WindowedQueue_Simple_Pass()
	{
		int maxSize = 2;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		
		sut.push(0);
		sut.push(1);
		
		assertEquals(maxSize, sut.window_size());
		assertEquals(2, sut.size());
	}
	
	@Test 
	public void WindowedQueue_WindowSize_Pass()
	{
		int maxSize = 2;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		
		sut.push(0);
		
		assertEquals(2, sut.window_size());
		assertEquals(1, sut.size());
	}
	
	@Test 
	public void WindowedQueue_PushAndGet_Pass()
	{
		int maxSize = 1;
		int expected = 0;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		
		sut.push(expected);
		int actual = sut.current();
		
		assertEquals(expected, actual);
	}
	
	@Test 
	public void WindowedQueue_PushAndGet_Multiple_Pass()
	{
		int maxSize = 2;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		
		sut.push(0);
		sut.push(1);
		
		int actual = sut.current();
		assertEquals(maxSize, sut.window_size());
		assertEquals(1, actual);
	}
	
	@Test 
	public void WindowedQueue_PushAndGet_MoreThanMaxSize_Pass()
	{
		int maxSize = 2;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		
		sut.push(0);
		sut.push(1);
		sut.push(2);
		
		int actual = sut.current();
		assertEquals(maxSize, sut.window_size());
		assertEquals(maxSize, sut.size());
		assertEquals(2, actual);
	}
	
	@Test 
	public void WindowedQueue_PushAndGetUsingIndex_Simple_Pass()
	{
		int maxSize = 2;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		
		sut.push(0);
		sut.push(1);
		
		int actual = sut.current();
		int actualWithGet = sut.get(0);
		
		assertEquals(2, sut.window_size());
		assertEquals(1, actual);
		assertEquals(actual, actualWithGet);
	}
	
	
	@Test 
	public void WindowedQueue_PushAndGetUsingIndex_MoreThanMaxSize_Pass()
	{
		int maxSize = 2;
		IWindowedStack<Integer> sut = new WindowedStack<Integer>(maxSize);
		
		sut.push(0);
		sut.push(1);
		sut.push(2);
		
		int actual = sut.current();
		int actualPrevious = sut.get(1);		
		
		assertEquals(maxSize, sut.window_size());
		assertEquals(2, actual);
		assertEquals(1, actualPrevious);
	}
	
	

}
