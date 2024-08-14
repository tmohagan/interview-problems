package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim_ohagan.interview.service.ArraysService;

import java.util.Arrays;

@RestController
@RequestMapping("/arrays")
public class ArraysController {

    private final ArraysService arrayService;

    public ArraysController(ArraysService arrayService) {
        this.arrayService = arrayService;
    }

    // Given two strings, check to see if they are anagrams by checking equality after sorting.
    @GetMapping("/check-anagram1")
    public boolean checkAnagram1(@RequestParam String str1, @RequestParam String str2) {
        return arrayService.isAnagram1(str1, str2);
    }

    // Given two strings, check to see if they are anagrams by checking equality by number of occurrences of characters.
    @GetMapping("/check-anagram2")
    public boolean checkAnagram2(@RequestParam String str1, @RequestParam String str2) {
        return arrayService.isAnagram2(str1, str2);
    }

    // Given an integer array, output all the * *unique ** pairs that sum up to a specific value k.
    @GetMapping("/pair-sum")
    public String pairSum(@RequestParam String arrStr, @RequestParam int k) {
        int[] arr = Arrays.stream(arrStr.substring(1, arrStr.length() - 1).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        return arrayService.pairSum(arr, k);
    }

    // Given two arrays, find which element is missing in the second array by sorting and comparing.
    @GetMapping("/find-missing1")
    public int findMissing1(@RequestParam String arrStr1, @RequestParam String arrStr2) {
        int[] arr1 = Arrays.stream(arrStr1.substring(1, arrStr1.length() - 1).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        int[] arr2 = Arrays.stream(arrStr2.substring(1, arrStr2.length() - 1).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        return arrayService.findMissing1(arr1, arr2);
    }

    // Given two arrays, find which element is missing in the second array by counting occurrences.
    @GetMapping("/find-missing2")
    public int findMissing2(@RequestParam String arrStr1, @RequestParam String arrStr2) {
        int[] arr1 = Arrays.stream(arrStr1.substring(1, arrStr1.length() - 1).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        int[] arr2 = Arrays.stream(arrStr2.substring(1, arrStr2.length() - 1).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        return arrayService.findMissing2(arr1, arr2);
    }

    // Given an array of integers (positive and negative) find the largest continuous sum.
    // update to track start and end points of the sum
    @GetMapping("/largest-continuous-sum")
    public int largeContSum(@RequestParam String arrStr) {
        int[] arr = Arrays.stream(arrStr.substring(1, arrStr.length() - 1).split(","))
            .mapToInt(Integer::parseInt)
            .toArray();
        return arrayService.largeContSum(arr);
    }

    // Given a string of words, reverse all the words (not the letters).
    @GetMapping("/reverse")
    public String reverse(@RequestParam String str) {
        return arrayService.reverse(str);
    }

    // Given a string in the form 'AAAABBBBCCCCCDDEEEE' compress it to become 'A4B4C5D2E4'.
    @GetMapping("/compress")
    public String compress(@RequestParam String str) {
        return arrayService.compress(str);
    }

    // Given a string,determine if it is compreised of all unique characters.
    @GetMapping("/check-unique-characters")
    public boolean checkUniqueChars(@RequestParam String str) {
        return arrayService.isUniqueChars(str);
    }
}