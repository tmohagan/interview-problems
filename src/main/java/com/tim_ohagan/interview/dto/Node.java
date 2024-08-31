package com.tim_ohagan.interview.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    private String data;
    private Node nextNode;

    public Node(String data) {
        this.data = data;
    }
}