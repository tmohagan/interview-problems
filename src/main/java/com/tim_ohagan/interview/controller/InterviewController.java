package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim_ohagan.interview.service.InterviewService;

@RestController
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping("/check-anagram")
    public boolean checkAnagram(@RequestParam String str1, @RequestParam String str2) {
        return interviewService.isAnagram(str1, str2);
    }

    @GetMapping("/check-palindrome")
    public boolean checkPalindrome(@RequestParam String str) {
        return interviewService.isPalindrome(str);
    }

}