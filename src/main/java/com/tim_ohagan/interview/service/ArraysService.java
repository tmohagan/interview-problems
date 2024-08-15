package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/*
 * @Service annotation indicates that this class contains business logic.
 * A controller typically depends on a service to handle the actual business logic.
 * By annotating it as a service it can be easily injected into other components (like controllers) that require its functionality.
 */
@Service
public class ArraysService {

    /*
     * If two strings are equal to each other once they are sorted, then they are also anagrams of each other.
    */
    public boolean isAnagram1(String s1, String s2) {
        // Remove spaces and convert to lowercase.
        // Java String type has both .replaceAll() and .toLowerCase(), which function as expected.
        s1 = s1.replaceAll("\\s+", "").toLowerCase();
        s2 = s2.replaceAll("\\s+", "").toLowerCase();

        // Check if lengths are equal, a necessary condition for anagrams.
        // Java String type has a .length() function to return the length of the string.
        if (s1.length() != s2.length()) {
            return false;
        }
        // Want to sort the characters in the Strings, but there is no way to sort the characters in a Java String type.
        // But Strings are basically just arrays of characters, and arrays can be sorted.
        // Convert strings to character arrays.
        // Java String type does have a .toCharArray() function that we can use to create new char arrays.
        // And the java.util.Arrays class has a .sort() function we can use to sort those arrays.

        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare sorted character arrays.
        // java.util.Arrays has an .equals() function to return if two arrays are equal
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
        /*
         * Map<Character, Integer> count: This part declares a variable named count that will hold a map.
         *  A Map is a data structure that stores key-value pairs.
         *  In this case, the keys will be characters (Character) and the values will be integers (Integer).
         *
         * new HashMap<>();: This part creates a new instance of a HashMap and assigns it to the count variable.
         *   A HashMap is a specific implementation of a Map that uses a hash table for efficient storage and retrieval.
         */
        Map<Character, Integer> count = new HashMap<>();

        // Fill dictionary for first string (add counts).
        /*
         * str1.toCharArray() converts the string str1 into an array of characters
         * The for loop then iterates over each character in this array, assigning each character to the variable letter in each iteration.
         */
        for (char letter : str1.toCharArray()) {
            /*
             * count.getOrDefault(letter, 0):
             *   For the count map, returns the value (occurrence counts) mapped to the specified key (letter),
             *   or 0 if the key (letter) doesn't yet have any value (occurrence counts) mapped to it.
             *
             * count.put(letter, ...):
             *   This part adds or updates the value (occurrence counts) for the  key (character letter) in the count map.
             * 
             * Essentiall, find out what the current value (occurrence counts) for each key (letter) and add 1 to the value mapped to that key
             */
            count.put(letter, count.getOrDefault(letter, 0) + 1);
        }

        // Fill dictionary for second string (subtract counts).
        /*
         * The above fills a Map corelating key (letters) value (letter occurrence counts) for the first string.
         * Use the for loop to iterate over ever character in the second string.
         * For each character, find it as the Key in the map populated above, then deduct one from it's value
         * 
         * Essentially, there is a table (map) with letters (key) and occourence counts of that letter (value) from the first string.
         * This second loop uses the second string to deduct that counts of each letter that was identified by the first loop.
         */
        for (char letter : str2.toCharArray()) {
            count.put(letter, count.getOrDefault(letter, 0) - 1);
        }

        // Check that all counts are 0.
        /*
         * Iterate over each value in the count map and store the value (letter occurrence counts) into the value varable
         * If the second string letter occurrence counts should have negated all the first string letter occurrence counts if they are the same,
         * the remaining value for all letter occurrence counts should be 0, otherwise they have different occurrences of letters.
         */
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
