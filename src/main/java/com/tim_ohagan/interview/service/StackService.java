package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.EmptyStackException;

@Service
public class StackService {
    
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
}