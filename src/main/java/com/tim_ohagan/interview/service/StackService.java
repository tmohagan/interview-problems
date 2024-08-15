package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;

@Service
public class StackService {
    // Custom implementation of a stack data structure
    private ArrayList<String> stack;

    public StackService() {
        this.stack = new ArrayList<>();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(String item) {
        stack.add(item);
    }

    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    public int size() {
        return stack.size();
    }

    public boolean checkParenthesis(String str) {
        // Check if even number of brackets
        if (str.length() % 2 != 0) {
            return false;
        }
    
        // Set of opening brackets
        HashMap<Character, Character> openingBrackets = new HashMap<>();
        openingBrackets.put('(', ')');
        openingBrackets.put('[', ']');
        openingBrackets.put('{', '}');
    
        // Use the custom stack implementation
        StackService customStack = new StackService();
    
        // Check every parenthesis in string
        for (char c : str.toCharArray()) {
            if (openingBrackets.containsKey(c)) {
                customStack.push(String.valueOf(c));
            } else {
                if (customStack.isEmpty()) {
                    return false;
                }
                char lastOpen = customStack.pop().charAt(0);
                if (openingBrackets.get(lastOpen) != c) {
                    return false;
                }
            }
        }
    
        return customStack.isEmpty();
    }
}