package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.*;
import com.tim_ohagan.interview.service.DequeService;

@RestController
@RequestMapping("/deque")
public class DequeController {
    
    private final DequeService dequeService;

    public DequeController(DequeService dequeService) {
        this.dequeService = dequeService;
    }

    @GetMapping("/is-empty")
    public boolean isDequeEmpty() {
        return dequeService.isEmpty();
    }

    @PostMapping("/add-front")
    public void addFront(@RequestParam String item) {
        dequeService.addFront(item);
    }

    @PostMapping("/add-rear")
    public void addRear(@RequestParam String item) {
        dequeService.addRear(item);
    }

    @GetMapping("/remove-front")
    public String removeFront() {
        return dequeService.removeFront();
    }

    @GetMapping("/remove-rear")
    public String removeRear() {
        return dequeService.removeRear();
    }

    @GetMapping("/size")
    public int getDequeSize() {
        return dequeService.size();
    }
}