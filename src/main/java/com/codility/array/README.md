
# You are given an array **A** of **N** positive integers

In one move, you can pick a segment (a contiguous fragment) of **A** and a positive integer **X** and then increase all elements within that segment by **X**.
An array is **strictly increasing** if each element (except for the last one) is smaller than the next element.
___
Write a function:

`class Solution { public int solution(intH A); }`

that, given an array **A** of **N** integers, returns the minimum number of moves needed to make the array strictly increasing.



###  Examples:
- Given  **```A = [4, 2, 4, 1, 3, 5]```** the function should return **2**.

One possible solution is to add **X = 3** to the segment **[2, 4]**, and then add **X = 8** to the segment **`[1, 3, 5]`**.

As a result of these two moves, **A** is now strictly increasing:

`[4, 2, 4, 1, 3, 5] ➜ [4, 𝟱, 𝟳, 1, 3, 5] ➜ [4, 5, 7, 𝟴, 𝟭𝟭, 5]`



- Given **`A = [3, 5, 7, 7]`** the function should return **1**.

- Given **`A = [1, 5, 6, 10]`** the function should return **0**.

***

#### **Write an efficient algorithm for the following assumptions:**

- **N** is an integer within the range **[1..100,000]**,
- Each element of array **A** is an integer within the range **[1..1,000,000,000]**,  this assumption does not have to hold after making a move.