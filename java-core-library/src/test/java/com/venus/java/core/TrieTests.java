package com.venus.java.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class TrieTests {
  private Trie root;

  @Before
  public void setUp() throws Exception {
    root = Trie.instance(false);
    getText();
  }

  @Test
  public void test() throws IOException {
    Arrays.asList("apple", "ant", "Bat", "Ball", "Hello", "Flabbergasted")
      .forEach(root::add);
    //System.out.println(root.toString());
    //root.display(root);

    //root.printWords(new char[100], root, 0);
    //System.out.println(root.words(new char[100], root, 0, new ArrayList<>()).size());
    System.out.println(root.searchForWordWithTimer("apple"));
    Files.lines(Paths.get("./src/test/resources/usa2.txt"), Charset.defaultCharset())
      .filter(line -> !line.isEmpty())
      .map(String::trim)
      .map(root::searchForWord)
      .forEach(Assert::assertTrue);
    Assert.assertFalse(root.searchForWordWithTimer("abcdefghijklmnopqrst"));
  }

  private void getText() throws Exception{
    Files.list(Paths.get("./src/test/resources"))
      .flatMap(path -> {
        try {
          return Files.lines(path, Charset.defaultCharset());
        } catch (IOException e) {
          e.printStackTrace();
          return Collections.emptyList().stream();
        }
      })
      .map(line -> line.toString())
      .filter(line -> !line.isEmpty())
      .map(String::trim)
      //.flatMap(line -> Arrays.stream(line.split("\\s+")))
      .forEach(root::add);
  }



}
