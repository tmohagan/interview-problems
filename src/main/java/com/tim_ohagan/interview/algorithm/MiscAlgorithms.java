package com.tim_ohagan.interview.algorithm;

import java.util.*;

public class MiscAlgorithms {

    // Two Pointers: Find pair with given sum in sorted array
    public static int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1}; // No solution found
    }

    // Sliding Window: Find max sum subarray of size k
    public static int maxSumSubarray(int[] arr, int k) {
        if (arr.length < k) {
            throw new IllegalArgumentException("Array length should be at least k");
        }
        int maxSum = 0, windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    // Prefix Sum: Range sum query
    public static class PrefixSum {
        private final int[] prefixSum;

        public PrefixSum(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }

    // K-Way Merge: Merge k sorted arrays
    public static List<Integer> kWayMerge(List<List<Integer>> sortedArrays) {
        PriorityQueue<QueueNode> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.value));
        List<Integer> result = new ArrayList<>();

        // Add the first element from each array to the min heap
        for (int i = 0; i < sortedArrays.size(); i++) {
            if (!sortedArrays.get(i).isEmpty()) {
                minHeap.offer(new QueueNode(sortedArrays.get(i).get(0), i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            QueueNode node = minHeap.poll();
            result.add(node.value);

            if (node.index + 1 < sortedArrays.get(node.arrayIndex).size()) {
                minHeap.offer(new QueueNode(
                    sortedArrays.get(node.arrayIndex).get(node.index + 1),
                    node.arrayIndex,
                    node.index + 1
                ));
            }
        }

        return result;
    }

    private static class QueueNode {
        int value;
        int arrayIndex;
        int index;

        QueueNode(int value, int arrayIndex, int index) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.index = index;
        }
    }

    // Longest Common Subsequence
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }

    // Trie (Prefix Tree) Implementation
    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
            }
            node.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return null;
                }
                node = node.children.get(c);
            }
            return node;
        }

        private static class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            boolean isEndOfWord;
        }
    }

    // 0/1 Knapsack Problem
    public static int knapsack(int W, int[] wt, int[] val, int n) {
        int[][] K = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }

    // Kadane's Algorithm: Maximum Subarray Sum
    public static int maxSubArray(int[] nums) {
        int maxSoFar = nums[0], maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    // Fisher-Yates Shuffle Algorithm
    public static void shuffle(int[] arr) {
        Random rand = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}