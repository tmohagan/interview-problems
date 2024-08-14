package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.*;
import com.tim_ohagan.interview.service.QueueService;

@RestController
@RequestMapping("/queue")
public class QueueController {
    
    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @GetMapping("/is-empty")
    public boolean isStackEmpty() {
        return queueService.isEmpty();
    }

    @PostMapping("/enqueue")
    public void enqueue(@RequestParam String item) {
        queueService.enqueue(item);
    }

    @GetMapping("/dequeue")
    public String dequeue() {
        return queueService.dequeue();
    }

    @GetMapping("/size")
    public int getQueueSize() {
        return queueService.size();
    }
}
