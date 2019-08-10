package com.venus.java.core;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Trie {
  private Map<Character, Trie> childNodes;
  private Boolean isWord = Boolean.FALSE;

  private Trie(){
    childNodes = new ConcurrentHashMap<>();
  }

  public static Trie instance(Boolean isWord) {
    return new Trie().setWordEnd(isWord);
  }

  Trie setWordEnd(Boolean isLast) {
    this.isWord = isLast;
    return this;
  }

  public Trie of(Character c) {
    Trie trie = instance(Boolean.FALSE);
    trie.childNodes.put(c, instance(Boolean.FALSE));
    return trie;
  }

  public Trie add(String word) {
    Trie trie = this;
    for(char c: word.toCharArray()) {
      trie = add(c, trie);
    }
    trie.setWordEnd(true);
    return this;
  }

  Trie add(char c, Trie node) {
    Trie value = node.childNodes.computeIfAbsent(c, key -> Trie.instance(false));
    //value.setWordEnd(c.isLast());
    return value;
  }

  @Override
  public String toString() {
    return childNodes.keySet().toString();
  }

  public void display(Trie node) {
    System.out.println(node.toString());
    for (Trie value : node.childNodes.values()) {
      display(value);
    }
  }

  public void printWords(char[] word, Trie node, int level) {
    if(node.isWord) {
      //word[level] = '\0';
      System.out.println(printWord(word, level));
    }

    for (Character c : node.childNodes.keySet()) {
      word[level] = c;
      printWords(word, node.childNodes.get(c), level + 1);
    }

  }

  public List<String> words(char[] word, Trie node, int level, List<String> strings) {
    if(node.isWord) {
      //word[level] = '\0';
      //System.out.println(printWord(word, level));
      strings.add(printWord(word, level));
    }

    for (Character c : node.childNodes.keySet()) {
      word[level] = c;
      words(word, node.childNodes.get(c), level + 1, strings);
    }
    return strings;
  }

  private String printWord(char[] word, int level) {
    return String.valueOf(word).substring(0, level);
  }

  public Boolean searchForWord(String word) {
    Objects.requireNonNull(word);
    Trie node = this;
    for(char c: word.toCharArray()) {
      if(Objects.isNull(node = node.childNodes.get(c)))
        return false;
    }
    return node.isWord;
  }

  public Boolean searchForWordWithTimer(String word) {
    Instant start = Instant.now();
    Boolean value = searchForWord(word);
    Instant finish = Instant.now();
    System.out.println("Time Taken for Search in Nano:" + Duration.between(start, finish).getNano());
    return value;
  }

}
