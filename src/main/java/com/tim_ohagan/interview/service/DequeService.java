package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.NoSuchElementException;

@Service
public class DequeService {
    
    private LinkedList<String> deque;

    public DequeService() {
        this.deque = new LinkedList<>();
    }

    public boolean isEmpty() {
        return deque.isEmpty();
    }

    public void addFront(String item) {
        deque.addFirst(item);
    }

    public void addRear(String item) {
        deque.addLast(item);
    }

    public String removeFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return deque.removeFirst();
    }

    public String removeRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return deque.removeLast();
    }

    public int size() {
        return deque.size();
    }
}