package com.venus.java.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TrieTests {
  private Trie root;

  @Before
  public void setUp(){
    root = Trie.instance(false);
  }

  @Test
  public void test(){
    Arrays.asList("apple", "ant", "Bat", "Ball")
      .forEach(root::add);
    //System.out.println(root.toString());
    root.display(root);
  }
}
