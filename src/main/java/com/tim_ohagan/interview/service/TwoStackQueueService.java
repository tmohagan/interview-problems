package com.tim_ohagan.interview.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class TwoStackQueueService {
    private ArrayList<String> inStack;
    private ArrayList<String> outStack;

    public TwoStackQueueService() {
        this.inStack = new ArrayList<>();
        this.outStack = new ArrayList<>();
    }

    public void enqueue(String item) {
        inStack.add(item);
    }

    public String dequeue() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.add(inStack.remove(inStack.size() -1));
            }
        }
        return outStack.remove(outStack.size() - 1);
    }
}