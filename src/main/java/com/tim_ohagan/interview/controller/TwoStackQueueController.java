package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.*;
import com.tim_ohagan.interview.service.TwoStackQueueService;

@RestController
@RequestMapping("/two-stack-queue")
public class TwoStackQueueController {
     private final TwoStackQueueService twoStackQueueService;

     public TwoStackQueueController(TwoStackQueueService twoStackQueueService) {
        this.twoStackQueueService = twoStackQueueService;
    }

    @PostMapping("/enqueue")
    public void enqueue(@RequestParam String item) {
        twoStackQueueService.enqueue(item);
    }

    @GetMapping("/dequeue")
    public String dequeue() {
        return twoStackQueueService.dequeue();
    }
}
