package com.tim_ohagan.interview.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tim_ohagan.interview.service.LinkedListService;

@RestController
@RequestMapping("/linked-list")
public class LinkedListController {
    private final LinkedListService linkedListService;

    public LinkedListController(LinkedListService linkedListService){
        this.linkedListService = linkedListService;
    }

    @PostMapping("/insert-begining")
    public void insertAtBeginning(@RequestParam String data){
        linkedListService.insertAtBeginning(data);
    }

    @PostMapping("/insert-end")
    public void insertAtEnd(@RequestParam String data){
        linkedListService.insertAtEnd(data);
    }

    @PostMapping("/insert-after")
    public void insertAfter(@RequestParam String prevNodeData, @RequestParam String newData){
        linkedListService.insertAfter(prevNodeData, newData);
    }

    @PostMapping("/delete")
    public void deleteNode(@RequestParam String data){
        linkedListService.deleteNode(data);
    }

    @GetMapping("/print")
    public String printList(){
        return linkedListService.printList();
    }
}
