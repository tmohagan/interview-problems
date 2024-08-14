package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.*;
import com.tim_ohagan.interview.service.StackService;

@RestController
@RequestMapping("/stack")
public class StackController {
    
    private final StackService stackService;

    public StackController(StackService stackService) {
        this.stackService = stackService;
    }

    @GetMapping("/is-empty")
    public boolean isStackEmpty() {
        return stackService.isEmpty();
    }

    @PostMapping("/push")
    public void pushToStack(@RequestParam String item) {
        stackService.push(item);
    }

    @GetMapping("/pop")
    public String popFromStack() {
        return stackService.pop();
    }

    @GetMapping("/peek")
    public String peekStack() {
        return stackService.peek();
    }

    @GetMapping("/size")
    public int getStackSize() {
        return stackService.size();
    }
}