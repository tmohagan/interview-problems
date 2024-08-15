package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim_ohagan.interview.service.ArraysService;
import java.util.Arrays;

/**
 * Spring MVC Annotations Overview:
 *
 * @Controller:
 * - Marks a class as a web controller, handling HTTP requests.
 * - Allows definition of request handler methods for specific URLs and HTTP methods.
 * - Typically used when a view (like a JSP) should be returned.
 *
 * @ResponseBody:
 * - Applied to a method to indicate its return value should be directly serialized to the HTTP response body.
 * - Bypasses view resolution.
 * - Commonly used for returning data formats like JSON or XML in RESTful services.
 *
 * @RestController:
 * - A specialized version of @Controller.
 * - Combines @Controller and @ResponseBody.
 * - Eliminates the need for @ResponseBody on every request handling method.
 * - Automatically serializes return objects into HTTP response bodies.
 * - Ideal for building RESTful web services.
 *
 * @RequestMapping("/arrays"):
 * - all its endpoints will be prefixed with "/arrays".
 *
 * Note: When using @RestController, all handler methods in the controller are treated
 * as if they were annotated with @ResponseBody individually.
 */
@RestController
@RequestMapping("/arrays")
public class ArraysController {

    /*
     * The ArraysController depends on ArraysService to perform its operations.
     * While the controller acts as a traffic director for web requests
     * the service Contains the core business logic of the application and performs operations, calculations, and data processing.
     * The controller is responsibile for determine WHAT service is best equip to handled the request, not HOW
     * Therefore, the service is final, since the controler should not adjust it in anyway.
     */
    private final ArraysService arrayService;

    /*
     * Instead of creating an instance of ArraysService itself, the controller receives it from outside (injected by Spring).
     * With Spring's dependency injection:
     *   You don't create the ArraysService instance in the ArraysController.
     *   Instead, Spring creates and manages instances of both ArraysController and ArraysService.
     *   When Spring creates an instance of ArraysController, it "injects" (provides) an instance of ArraysService into it.
     *   Spring can do this for ArrayService because it's imported : import com.tim_ohagan.interview.service.ArraysService;
     *   AND ArrayService is annotated with @Service
     */
    public ArraysController(ArraysService arrayService) {
        this.arrayService = arrayService;
    }

    /*
     * @GetMapping
     * This is a Spring annotation that maps HTTP GET requests to this specific method.
     * It means this method will handle GET requests to the URL path "/check-anagram1".
     * 
     * In most cases, it defines a function which accepts parameters and then forwards those parameters to the appropriate service and returns the result from the service.
     * The controller is given a url which contains more information than just parameters, @RequestParam is used to extract only the necessary parameters to be passed to the service.
     */

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
        /*
         * The RequestParam cannont be an true array type, it will have to be a String.
         * However, the service is designed to accept an array, so we will have to convert the String to an array.
         */
        
         /*
          * arrStr.substring(1, arrStr.length() - 1)
          *   It's likely assuming the input string is in a format like "[1,2,3,4]", and this step removes the square brackets.
          * .split(",")
          *   This splits the remaining string into an array of strings, using comma as the delimiter.
          * Arrays.stream(...)
          *   This creates a stream from the array of strings.
          * .mapToInt(Integer::parseInt)
          *   This maps each string in the stream to an integer using Integer.parseInt().
          *   It converts each string representation of a number into an actual int.
          *   It's like, "convert foreach"
          * .toArray()
          *   This collects the stream of integers into an int array.
          */
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