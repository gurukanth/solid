package com.venus.java.core;

public class Tuple<A, B> {
  private A valA;
  private B valB;

  public Tuple(A valA, B valB) {
    this.valA = valA;
    this.valB = valB;
  }

  public A getValA() {
    return valA;
  }

  public B getValB() {
    return valB;
  }
}
