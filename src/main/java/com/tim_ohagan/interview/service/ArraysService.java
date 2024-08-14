package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

@Service
public class ArraysService {

    /*
     * If two strings are equal to each other once they are sorted, then they are also anagrams of each other.
    */
    public boolean isAnagram1(String s1, String s2) {
        // Remove spaces and convert to lowercase.
        s1 = s1.replaceAll("\\s+", "").toLowerCase();
        s2 = s2.replaceAll("\\s+", "").toLowerCase();

        // Check if lengths are equal, a necessary condition for anagrams.
        if (s1.length() != s2.length()) {
            return false;
        }

        // Convert strings to character arrays and sort.
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare sorted character arrays.
        return Arrays.equals(charArray1, charArray2);
    }

    /*
     * If two strings each have the same number of occurrences as each character, then they are also anagrams of each other.
    */
    public boolean isAnagram2(String str1, String str2) {
        // Remove spaces and lowercase letters.
        str1 = str1.replaceAll("\\s+", "").toLowerCase();
        str2 = str2.replaceAll("\\s+", "").toLowerCase();

        // Edge Case to check if same number of letters.
        if (str1.length() != str2.length()) {
            return false;
        }

        // Create counting dictionary.
        Map<Character, Integer> count = new HashMap<>();

        // Fill dictionary for first string (add counts).
        for (char letter : str1.toCharArray()) {
            count.put(letter, count.getOrDefault(letter, 0) + 1);
        }

        // Fill dictionary for second string (subtract counts).
        for (char letter : str2.toCharArray()) {
            count.put(letter, count.getOrDefault(letter, 0) - 1);
        }

        // Check that all counts are 0.
        for (int value : count.values()) {
            if (value != 0) {
                return false;
            }
        }

        // Otherwise they're anagrams.
        return true;
    }

    public String pairSum(int[] arr, int k) {
        if (arr.length < 2) {
            return "";
        }
        // Sets for tracking.
        Set<Integer> seen = new HashSet<>();
        Set<String> output = new HashSet<>();

        // For every number in array.
        for (int num : arr) {
            // Set target difference.
            int target = k - num;

            // Add it to set if target hasn't been seen.
            if (!seen.contains(target)) {
                seen.add(num);
            } else {
                // Add the corresponding pair.
                output.add(Math.min(num, target) + " " + Math.max(num, target));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String pair : output) {
            sb.append(pair).append("\n");
        }
        return sb.toString();
    }

    /*
     * If the arrays are sorted, then first occurrence from the first array not found in the second, is missing from the second.
    */
    public int findMissing1(int[] arr1, int[] arr2) {
        // Sort the arrays.
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Compare elements in the sorted arrays.
        for (int i = 0; i < Math.min(arr1.length, arr2.length); i++) {
            if (arr1[i] != arr2[i]) {
                return arr1[i];
            }
        }

        // Otherwise return last element.
        return arr1[arr1.length - 1];
    }

    /*
     * Use a hashtable and store the number of times each element appears in the second array.
     * Then for each element in the first array, decrement its counter.
     * Once hit an element with zero count that’s the missing element.
    */
    public int findMissing2(int[] arr1, int[] arr2) {
        // Using a HashMap to avoid key errors.
        Map<Integer, Integer> countMap = new HashMap<>();

        // Add a count for every instance in Array 1.
        for (int num : arr2) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Check if num not in dictionary.
        for (int num : arr1) {
            if (!countMap.containsKey(num)) {
                return num;
            } else if (countMap.get(num) == 0) {
                return num;
            } else {
                countMap.put(num, countMap.get(num) - 1);
            }
        }

        return -1; // Handle case where no extra number is found.
    }

    /*
     * After adding each element, check whether the current sum is larger than maximum sum encountered so far.
     */
    public int largeContSum(int[] arr) {
        // Check to see if array is length 0.
        if (arr.length == 0) {
            return 0;
        }

        // Start the max and current sum at the first element.
        int maxSum = arr[0];
        int currentSum = arr[0];

        // For every element in array
        for (int i = 1; i < arr.length; i++) {
            // Set current sum as the higher of the two.
            currentSum = Math.max(currentSum + arr[i], arr[i]);

            // Set max as the higher between the currentSum and the current max.
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }

    public String reverse(String str) {
        String[] words = new String[str.length()];
        int wordCount = 0;

        // While index is less than length of string.
        for (int i = 0; i < str.length(); i++) {
            // If element isn't a space
            if (str.charAt(i) != ' ') {
                // The word starts at this index.
                int wordStart = i;
                // Get index where word ends.
                while (i < str.length() && str.charAt(i) != ' ') {
                    i++;
                }
                // Append that word to the list.
                words[wordCount++] = str.substring(wordStart, i);
            }
        }

        // Join the reversed words.
        StringBuilder reversed = new StringBuilder();
        for (int i = wordCount - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }

        return reversed.toString().trim();
    }

    /*
     * If the current character is the same as the previous character, increment the count.
     * Otherwise, append the character and count to the result.
     * Then start over for the next character and count.
     */
    public String compress(String str) {
        if (str.length() == 0) {
            return "";
        }

        if (str.length() == 1) {
            return str + "1";
        }

        StringBuilder result = new StringBuilder();
        char last = str.charAt(0);
        int count = 1;

        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == last) {
                count++;
            } else {
                result.append(last).append(count);

                last = str.charAt(i);
                count = 1;
            }
        }

        result.append(last).append(count);
        return result.toString();
    }

    public boolean isUniqueChars(String str) {
        // Creates a HashSet to store unique characters.
        Set<Character> chars = new HashSet<>();
        
        // Iterates through each character in the string.
        for (char let : str.toCharArray()) {
            // If the character is already in the set, returns false (indicating duplicate characters).
            if (chars.contains(let)) {
                return false;
            // Otherwise, adds the character to the set.
            } else {
                chars.add(let);
            }
        }
        // Only makes it this far if no duplicate characters found.
        return true;
    }
}
