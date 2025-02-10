package com.codility.array;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/** Hello world! */
public class StrictlyIncreasingArray {

  private static final int ELEMENT_MAX_VALUE = 100;
  private static final int ARRAY_MAX_SIZE = 7;
  private static final int NUM_OF_ARRAYS = 5;
  private static final String SEPARATOR = "    ";
  
  
  public static void main(String[] args) {

    
    //int[][] seqCol= {{3, 5, 7, 7}, {4, 2, 4, 1, 3, 5}, {1, 5, 6, 10}};
    
    int[] sequence = IntStream.range(1, NUM_OF_ARRAYS+1).map(i -> i + 1).toArray();
    //int[] sequence = {0,1,2};

    // optimize with streams
    for (Integer ignored : sequence) {
      //int[] A = seqCol[ignored];
      int randomArrayLength = ThreadLocalRandom.current().nextInt(3, ARRAY_MAX_SIZE + 1);
      int[] A = generateArray(randomArrayLength);

      StringBuilder transitions = new StringBuilder();
      
      transitions.append(arrayToString(A));
      
      int moves = 0;
      int delta = 0;
      int currEpsilon = 0;
      int baseLineEpsilon = 0;

      for (int i = 1; i < A.length; i++) {
        transitions.append(SEPARATOR).append("(").append(i).append(")").append(SEPARATOR);
        
        
        transitions.append(arrayToString(Arrays.copyOfRange(A, i-1, i+1)));
        delta = A[i - 1] - A[i];
        transitions.append("\t").append(" β: ").append(baseLineEpsilon).append(SEPARATOR);
        if (delta >= 0) {
         

          currEpsilon = delta + 1;
          
          transitions.append(SEPARATOR).append(" ε: ").append(currEpsilon).append(SEPARATOR);

          
          if (A[i] + baseLineEpsilon > A[i - 1]) {
            
            // increment with epsilon that wasn't changed in last iteration
            if (i != A.length - 1) {
              transitions.append(A[i]).append(" -> ").append(A[i] + currEpsilon +1 ).append(SEPARATOR);
              A[i] += currEpsilon + 1;
            }
            
          } else {
            // Increment moves
            // increment with epsilon of this iteration
            moves++;
            if (i != A.length - 1) {
              transitions.append(A[i]).append(" -> ").append(A[i] + currEpsilon).append(SEPARATOR);
              A[i] += currEpsilon;
              baseLineEpsilon = currEpsilon;
            }
            transitions.append(SEPARATOR).append("Ⓜ");
          }
        }else{
          baseLineEpsilon = 0;
        }
        
        if (i != A.length - 1) {
          transitions.append(System.lineSeparator()).append(arrayToString(A));
        }
      }

      out.println("Moves: " + moves);
      out.println(transitions);
      out.println();
    }
    
    
    
  }


  public static int[] generateArray(int length) {

    return IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, ELEMENT_MAX_VALUE + 1))
        .distinct()
        .limit(length)
        .toArray();
  }
  
  public static String arrayToString(int[] a) {
    if (a == null)
      return "null";
    int iMax = a.length - 1;
    if (iMax == -1)
      return "[]";
    
    StringBuilder b = new StringBuilder();
    b.append('[');
    for (int i = 0; ; i++) {
      if(a[i]<10){
        b.append(" ").append(a[i]);
      }else{
        b.append(a[i]);
      }
      
      if (i == iMax)
        return b.append(']').toString();
      b.append(", ");
    }
  }
}
