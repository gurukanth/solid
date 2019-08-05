package com.venus.java.core;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class MStringBuilderTests {
  private MStringBuilder stringBuilder;
  private Object[] objectA = new Object[2];

  @Before
  public void setUP() {
    stringBuilder = new MStringBuilder();
    objectA[0] = Double.valueOf(200);
    objectA[1] = new Date();
  }

  @Test
  public void test() {
    System.out.println(objectA[0]);
    System.out.println(objectA[1]);

    Assert.assertThat(true, Is.is(true));
  }

}
