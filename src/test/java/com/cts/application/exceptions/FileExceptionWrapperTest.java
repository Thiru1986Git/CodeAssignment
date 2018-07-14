/*
 * This file was automatically generated by EvoSuite
 * Sat Jul 14 22:42:49 GMT 2018
 */

package com.cts.application.exceptions;

import org.junit.Test;
import static org.junit.Assert.*;
import com.cts.application.exceptions.FileExceptionWrapper;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class FileExceptionWrapperTest extends FileExceptionWrapperTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      FileExceptionWrapper fileExceptionWrapper0 = new FileExceptionWrapper();
      String string0 = fileExceptionWrapper0.toString();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      FileExceptionWrapper fileExceptionWrapper0 = new FileExceptionWrapper("b&K)x0R<");
      String string0 = fileExceptionWrapper0.toString();
      assertEquals("b&K)x0R<", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      FileExceptionWrapper fileExceptionWrapper0 = new FileExceptionWrapper();
      String string0 = fileExceptionWrapper0.getMessage();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      FileExceptionWrapper fileExceptionWrapper0 = new FileExceptionWrapper("");
      String string0 = fileExceptionWrapper0.getMessage();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      FileExceptionWrapper fileExceptionWrapper0 = new FileExceptionWrapper("b&K)x0R<");
      String string0 = fileExceptionWrapper0.getMessage();
      assertEquals("b&K)x0R<", string0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      FileExceptionWrapper fileExceptionWrapper0 = new FileExceptionWrapper();
      FileExceptionWrapper fileExceptionWrapper1 = new FileExceptionWrapper(fileExceptionWrapper0);
      assertFalse(fileExceptionWrapper1.equals((Object)fileExceptionWrapper0));
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      FileExceptionWrapper fileExceptionWrapper0 = new FileExceptionWrapper("");
      String string0 = fileExceptionWrapper0.toString();
      assertEquals("", string0);
  }
}