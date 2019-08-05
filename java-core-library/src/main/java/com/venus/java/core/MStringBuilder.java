package com.venus.java.core;

public class MStringBuilder {
  private char[] chars = new char[100];
  private int size;

  public MStringBuilder append(String string) {
    if(size + string.length() > 100)
      increaseCapacity();
    for(char c : string.toCharArray()) {
      chars[size++] = c;
    }
    return this;
  }

  private void increaseCapacity(){}
}
