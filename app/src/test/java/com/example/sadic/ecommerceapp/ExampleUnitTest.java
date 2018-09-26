package com.example.sadic.ecommerceapp;

import com.example.sadic.ecommerceapp.testing.MyClass;
import com.example.sadic.ecommerceapp.testing.Todo;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);
//    }

//    @Test
//    public void testVerify()  {
//        // create and configure mock
//        MyClass test = Mockito.mock(MyClass.class);
//        when(test.getUniqueId()).thenReturn(43);
//
//
//        // call method testing on the mock with parameter 12
//        test.testing(12);
//        test.getUniqueId();
//        test.getUniqueId();
//
//
//        // now check if method testing was called with the parameter 12
//        verify(test).testing(ArgumentMatchers.eq(12));
//
//        // was the method called twice?
//        verify(test, times(2)).getUniqueId();
//
//        // other alternatives for verifiying the number of method calls for a method
//        verify(test, never()).someMethod("never called");
//        verify(test, atLeastOnce()).someMethod("called at least once");
//        verify(test, atLeast(2)).someMethod("called at least twice");
//        verify(test, times(5)).someMethod("called five times");
//        verify(test, atMost(3)).someMethod("called at most 3 times");
//        // This let's you check that no other methods where called on this object.
//        // You call it after you have verified the expected method calls.
//        verifyNoMoreInteractions(test);
//    }

    @Test
    public void testLinkedListSpyWrong() {
        // Lets mock a LinkedList
        List<String> list = new LinkedList<>();
        List<String> spy = spy(list);

        // this does not work
        // real method is called so spy.get(0)
        // throws IndexOutOfBoundsException (list is still empty)
        //when(spy.get(0)).thenReturn("foo");
        // You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

        assertEquals("foo", spy.get(0));
    }



    @Test
    public void test1()  {
        //  create mock
        MyClass test = mock(MyClass.class);

        // define return value for method getUniqueId()
        when(test.getUniqueId()).thenReturn(43);

        // use mock in test....
        assertEquals(test.getUniqueId(), 43);
    }


    // demonstrates the return of multiple values
    @Test
    public void testMoreThanOneReturnValue()  {
        Iterator<String> i= mock(Iterator.class);
        when(i.next()).thenReturn("Mockito").thenReturn("rocks");
        String result= i.next()+" "+i.next();
        //assert
        assertEquals("Mockito rocks", result);
    }

    // this test demonstrates how to return values based on the input
    @Test
    public void testReturnValueDependentOnMethodParameter()  {
        Comparable<String> c= mock(Comparable.class);
        when(c.compareTo("Mockito")).thenReturn(1);
        when(c.compareTo("Eclipse")).thenReturn(2);
        //assert
        assertEquals(1, c.compareTo("Mockito"));
    }

// this test demonstrates how to return values independent of the input value

    @Test
    public void testReturnValueInDependentOnMethodParameter()  {
        Comparable<Integer> c= mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        //assert
        assertEquals(-1, c.compareTo(9));
    }

// return a value based on the type of the provide parameter

    @Test
    public void testReturnValueInDependentOnMethodParameter2()  {
        Comparable<Todo> c= mock(Comparable.class);
        when(c.compareTo(isA(Todo.class))).thenReturn(0);
        //assert
        assertEquals(0, c.compareTo(new Todo(1)));
    }
}