package com.codility.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** Hello world! */
public class StrictlyIncreasingArray {
  
  private static final int ELEMENT_MAX_VALUE = 100;
  private static final int ARRAY_MAX_SIZE = 7;
  private static final int NUM_OF_ARRAYS = 5;
  private static final int ARRAY_MIN_SIZE = 3;
  private static final char[] SUBSCRIPT_DIGITS = {
          '\u2080', '\u2081', '\u2082', '\u2083', '\u2084',
          '\u2085', '\u2086', '\u2087', '\u2088', '\u2089'
  };
  private static final List<int[]> givenArrays = new ArrayList<>();
  
  public static void main(String[] args) {
    addDefaultGivenArrays();
    addRandGivenArrays();
    System.out.println("""
      In my solution, if the current number is smaller than the previous one, a move is confirmed.
      During subsequent iterations, we need to determine whether an addition to the numbers represents a new move or not.
      If the current number is smaller than the previous one, and if the maximum Δ (difference) is sufficient to increase the current number
      so it surpasses the previous number, no new move is counted. Otherwise, a new move is recorded.
    """);
    System.out.println("Definitions:");
    System.out.println("""
      Δ: Represents the difference between two consecutive numbers of the array.
      ε: Represents how much is necessary to add to the i(n) number, to be bigger than i(n-1) - basically Δ+1.
    """);
    
    for (int[] A : givenArrays) {
      int moves = 0, delta, currEpsilon, highestEpsilon = 0;
      var transitions = new StringBuilder();
      
      for (int i = 1; i < A.length; i++) {
        delta = A[i - 1] - A[i];
        transitions.append(System.lineSeparator()).append("i").append(toSubscript(i));
        transitions.append("\t\t").append(arrayToString(A));
        int[] consecutives = Arrays.copyOfRange(A, i - 1, i + 1);
        transitions.append("\t\t").append(consecutives[0]).append(", ").append(consecutives[1]).append(" ");
        transitions.append("\t\t").append("Δ:").append(delta > 9 ? delta : " " + delta);
        transitions.append("\t\t").append("max(ε): ").append(highestEpsilon);
        if (delta >= 0) {
          currEpsilon = delta + 1;
          transitions.append("\t\t").append("ε(").append(i).append("): ").append(currEpsilon);
          if (A[i] + highestEpsilon > A[i - 1]) {
            if (i != A.length - 1) {
              transitions.append("\t\t").append(A[i])
                      .append(" -> ")
                      .append(A[i] + currEpsilon + 1);
              A[i] += currEpsilon + 1;
            }
          } else {
            moves++;
            transitions.append("\t\t").append("\uD83C\uDD7C");
            if (i != A.length - 1) {
              transitions.append("\t\t").append(A[i])
                      .append(" -> ")
                      .append(A[i] + currEpsilon);
              A[i] += currEpsilon;
              highestEpsilon = currEpsilon;
            }
          }
        } else {
          highestEpsilon = 0;
        }
      }
      System.out.println("Moves: " + moves);
      System.out.println(transitions);
      System.out.println();
    }
  }
  
  private static void addDefaultGivenArrays(){
    givenArrays.add(new int[]{4, 2, 4, 1, 3, 5});
    givenArrays.add(new int[]{3, 5, 7, 7});
    givenArrays.add(new int[]{1, 5, 6, 10});
  }
  
  private static void addRandGivenArrays(){
    for (int i = 0; i < NUM_OF_ARRAYS; i++) {
      int randomArrayLength = ThreadLocalRandom.current().nextInt(ARRAY_MIN_SIZE, ARRAY_MAX_SIZE + 1);
      givenArrays.add(generateArray(randomArrayLength));
    }
  }
  
  public static String arrayToString(int[] a) {
    return a == null ? "null" : IntStream.of(a)
            .mapToObj(n -> n < 10 ? " " + n : String.valueOf(n))
            .collect(Collectors.joining(", ", "[", "]"));
  }
  
  public static int[] generateArray(int length) {
    return ThreadLocalRandom.current()
            .ints(1, ELEMENT_MAX_VALUE + 1)
            .distinct()
            .limit(length)
            .toArray();
  }
  
  public static String toSubscript(int number) {
    return String.valueOf(number)
            .chars()
            .mapToObj(c -> String.valueOf(SUBSCRIPT_DIGITS[c - '0']))
            .collect(Collectors.joining());
  }
}
