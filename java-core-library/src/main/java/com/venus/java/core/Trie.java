package com.venus.java.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

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
    IntStream.iterate(0, i->i+1).limit(word.length())
      .mapToObj(i -> Char.of(word.charAt(i), i == word.length()))
      .forEach(this::add);
      return this;
  }

  Trie add(Char iChar) {
    childNodes.compute(iChar.value(), (k, v) -> v == null ? v = instance(iChar.isLast()) : v.add(iChar));
    return this;
  }

/*  List<String> findAll() {
    childNodes.values().stream()
      .flatMap(node -> )
  }*/

  public static class Char extends Tuple<Character, Boolean> {

    private Char(Character valA, Boolean valB) {
      super(valA, valB);
    }

    Character value(){
      return getValA();
    }

    Boolean isLast() {
      return getValB();
    }

    public static Char of(Character c, Boolean isLast) {
      return new Char(c, isLast);
    }
  }
}
