package stack;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import collection.StackADT;

public class ArrayStackTest
{
   private StackADT<String> stack;

   @Before
   public void setUp() throws Exception
   {
      stack = new ArrayStack<>(10);
   }

   @After
   public void tearDown() throws Exception
   {
   }

   @Test
   public void testPush()
   {
      stack.push("$A$");
      assertEquals(1, stack.size());
      assertEquals("$A$", stack.peek());
      
      stack.push("$B$");
      assertEquals(2, stack.size());
      assertEquals("$B$", stack.peek());

      stack.push("$C$");
      assertEquals(3, stack.size());
      assertEquals("$C$", stack.peek());

      stack.push("$D$");
      assertEquals(4, stack.size());
      assertEquals("$D$", stack.peek());

      try
      {
         stack.push(null);
         assertEquals(5, stack.size());
         assertEquals(null, stack.peek());

         stack.push("$A$");
         assertEquals(6, stack.size());
         assertEquals("$A$", stack.peek());

         stack.push(null);
         assertEquals(7, stack.size());
         assertEquals(null, stack.peek());
      }
      catch (IllegalArgumentException e)
      {
         // OK
      } catch (Exception e) {
    	  fail("Should either accept null or throw IllegalArgumentException");
      }
   }

   public void testPushAlmostFull()
   {
      stack.push("$A$");
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      stack.push("$E$");
      stack.push("$F$");
      stack.push("$G$");
      stack.push("$H$");
      stack.push("$I$");
      stack.push("$J$");
   }

   @Test(expected = IllegalStateException.class)
   public void testPushFull()
   {
      stack.push("$A$");
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      stack.push("$E$");
      stack.push("$F$");
      stack.push("$G$");
      stack.push("$H$");
      stack.push("$I$");
      stack.push("$J$");
      stack.push("$K$");
   }

   @Test(expected = IllegalStateException.class)
   public void testPushNullFull()
   {
      stack.push("$A$");
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      stack.push("$E$");
      stack.push("$F$");
      stack.push("$G$");
      stack.push("$H$");
      stack.push("$I$");
      stack.push("$J$");
      try
      {
         stack.push(null);
      }
      catch (IllegalArgumentException e)
      {
         stack.push("$K$");
      }
   }

   @Test
   public void testPushNull()
   {
      try
      {
         stack.push(null);
         assertEquals(1, stack.size());
         assertEquals(null, stack.peek());
         stack.push(null);
         stack.push("$A$");
         assertEquals(3, stack.size());
         assertEquals("$A$", stack.peek());
      }
      catch (IllegalArgumentException e)
      {
         assertTrue(stack.size() == 0);
      }
   }

   @Test
   public void testSize() // indirectly testing pop too
   {
      assertEquals(0, stack.size());
      stack.push("$A$");
      assertEquals(1, stack.size());
      assertEquals("$A$", stack.pop());
      assertEquals(0, stack.size()); 
      stack.push("$B$");
      stack.push("$B$");
      stack.push("$D$");
      assertEquals(3, stack.size());
      assertEquals("$D$", stack.pop());
      assertEquals(2, stack.size());
      assertEquals("$B$", stack.pop());
      assertEquals(1, stack.size());
      assertEquals("$B$", stack.pop());
      assertEquals(0, stack.size());
      stack.push("$X$");
      stack.push("$Y$");
      stack.push("$Z$");
      assertEquals(3, stack.size());
      assertEquals("$Z$", stack.pop());
      assertEquals("$Y$", stack.pop());
      assertEquals("$X$", stack.pop());
      assertEquals(0, stack.size());
      try
      {
         stack.push(null);
         assertEquals(1, stack.size());
         assertEquals(null, stack.pop());
         assertEquals(0, stack.size());
         stack.push(null);
         stack.push(null);
         stack.push("$A$");
         assertEquals("$A$", stack.pop());
         assertEquals(2, stack.size());
         assertEquals(null, stack.pop());
         assertEquals(null, stack.pop());
         assertEquals(0, stack.size());
      }
      catch (IllegalArgumentException e)
      {
         // OK
      }

   }

   @Test
   public void testPop()
   {
      stack.push("$A$");
      assertEquals("$A$", stack.pop());
      stack.push("$B$");
      stack.push("$D$");
      assertEquals("$D$", stack.pop());
      assertEquals("$B$", stack.pop());
      try
      {
         stack.push(null);
         assertEquals(null, stack.pop());
         stack.push(null);
         stack.push("$A$");
         assertEquals("$A$", stack.pop());
         assertEquals(null, stack.pop());
      }
      catch (IllegalArgumentException e)
      {
         // OK
      }

   }

   @Test(expected = IllegalStateException.class)
   public void testPopException()
   {
      stack.pop();
   }

   @Test
   public void testPeek()
   {
      stack.push("$A$");
      assertEquals("$A$", stack.peek());
      stack.push("$B$");
      stack.push("$B$");
      stack.push("$D$");
      assertEquals("$D$", stack.peek());
      try
      {
         stack.push(null);
         assertEquals(null, stack.peek());
         stack.push(null);
         stack.push("$A$");
         assertEquals("$A$", stack.peek());
      }
      catch (IllegalArgumentException e)
      {
         // OK
      }

   }

   @Test(expected = IllegalStateException.class)
   public void testPeekException1()
   {
      stack.peek();
   }

   @Test
   public void testIsEmpty()
   {
      assertTrue(stack.isEmpty());
      stack.push("$A$");
      assertFalse(stack.isEmpty());
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      assertFalse(stack.isEmpty());
      stack.pop();
      stack.pop();
      stack.pop();
      stack.pop();
      assertTrue(stack.isEmpty());
      try
      {
         stack.push(null);
         assertFalse(stack.isEmpty());
         stack.push(null);
         stack.push("$C$");
         assertFalse(stack.isEmpty());
         stack.pop();
         stack.pop();
         stack.pop();
         assertTrue(stack.isEmpty());
      }
      catch (IllegalArgumentException e)
      {
         // OK
      }
   }

   @Test
   public void testIndexOfNotFound()
   {
      assertEquals(-1, stack.indexOf("???"));
      stack.push("$A$");
      stack.push("$B$");
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      stack.push("$B$");
      assertEquals(-1, stack.indexOf("$X$"));
   }
   
   @Test
   public void testIndexOfNotFoundNull()
   {
      assertEquals(-1, stack.indexOf(null));
      stack.push("$A$");
      stack.push("$B$");
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      stack.push("$B$");
      assertEquals(-1, stack.indexOf(null));
   }

   @Test
   public void testIndexOfFound()
   {
      stack.push("$A$");
      stack.push("$B$");
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      stack.push("$B$");
      assertEquals(5, stack.indexOf("$A$"));
      assertEquals(0, stack.indexOf("$B$"));
      assertEquals(2, stack.indexOf("$C$"));
      assertEquals(1, stack.indexOf("$D$"));
      try
      {
         stack.push(null);
         assertEquals(0, stack.indexOf(null));
         stack.push(null);
         stack.push("$D$");
         stack.push("$B$");
         assertEquals(2, stack.indexOf(null));
         assertEquals(0, stack.indexOf("$B$"));
         assertEquals(1, stack.indexOf("$D$"));
         assertEquals(9, stack.indexOf("$A$"));
      }
      catch (IllegalArgumentException e)
      {
         // OK
      }
   }

   @Test
   public void testToString()
   {
      // format is not defined only order of elements
      stack.push("$B$");
      stack.push("$D$");
      stack.push("$A$");
      stack.push("$B$");
      stack.push("$C$");
      stack.push("$D$");
      stack.push("$K$");
      assertTrue(contains("$K$", "$D$", "$C$", "$B$", "$A$", "$D$", "$B$"));
      assertFalse(stack.toString().contains("null")); // no null elements shown
   }
   
   @Test
   public void testToStringAndNull()
   {
      // format is not defined only order of elements
      stack.push("$B$");
      stack.push("$D$");
      stack.push("$A$");
      stack.push("$B$");
      try
      {
         stack.push(null);
         stack.push("$D$");
         stack.push("$K$");
         assertTrue(contains("$K$", "$D$", "null", "$B$", "$A$", "$D$", "$B$"));
         assertTrue(stack.toString().contains("null"));
      }
      catch (IllegalArgumentException e)
      {
         assertTrue(contains("$B$", "$A$", "$D$", "$B$"));
         assertFalse(stack.toString().contains("null"));
      }
   }

   private boolean contains(String... s)
   {
      // correct elements and order (cannot check if there are too many elements) 
      String listString = stack.toString();
      if (s.length < 1)
         return false;
      int index1 = listString.indexOf(s[0]);
      if (index1 == -1)
         return false;
      listString = replace(listString, s[0], '£');
      if (s.length == 1)
      {
         return index1 > -1;
      }
      for (int i = 1; i < s.length; i++)
      {
         int index2 = listString.indexOf(s[i]);
         if (index2 == -1)
            return false;
         listString = replace(listString, s[i], '£');
         /*if (index1 > index2)
            return false;*/
         index1 = index2;
      }
      return true;
   }

   private String replace(String s, String target, char replacementChar)
   {
      String replace = "";
      for (int k = 0; k < target.length(); k++)
         replace += replacementChar;
      int index = s.indexOf(target);
      if (index < 0)
         return s;
      String s2 = s.substring(0, index) + replace
            + s.substring(index + replace.length());
      return s2;
   }
}
