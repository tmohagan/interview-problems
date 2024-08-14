package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@Service
public class QueueService {
    
    private ArrayList<String> queue;

    public QueueService() {
        this.queue = new ArrayList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(String item) {
        queue.add(item);
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue.remove(0);
    }

    public int size() {
        return queue.size();
    }

}
