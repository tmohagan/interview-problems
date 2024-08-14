package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class QueueService {
    
    private ArrayList<String> queue;

    public QueueService() {
        this.queue = new ArrayList<>();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
