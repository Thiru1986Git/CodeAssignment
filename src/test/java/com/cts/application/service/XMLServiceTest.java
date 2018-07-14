/*
 * This file was automatically generated by EvoSuite
 * Sat Jul 14 22:41:16 GMT 2018
 */

package com.cts.application.service;

import static org.evosuite.runtime.EvoAssertions.verifyException;
import static org.junit.Assert.fail;

import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class XMLServiceTest extends XMLServiceTest_scaffolding {

//  @Test(timeout = 4000)
//  public void test0()  throws Throwable  {
//      XMLService xMLService0 = new XMLService();
//      Class<Integer> class0 = Class.class;
//      Marshaller marshaller0 = xMLService0.getMarshaller(class0);
//      assertNotNull(marshaller0);
//  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      XMLService xMLService0 = new XMLService();
      // Undeclared exception!
      try { 
        xMLService0.getUnmarshaller((Class<?>) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("javax.xml.bind.JAXBContext", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      XMLService xMLService0 = new XMLService();
      // Undeclared exception!
      try { 
        xMLService0.getJAXBContext((Class<?>) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("javax.xml.bind.JAXBContext", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      XMLService xMLService0 = new XMLService();
      // Undeclared exception!
      try { 
        xMLService0.getMarshaller((Class<?>) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("javax.xml.bind.JAXBContext", e);
      }
  }
}
