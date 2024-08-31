package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;

import com.tim_ohagan.interview.dto.Node;


@Service
public class LinkedListService {
    private Node head;

    public LinkedListService(){
        this.head = null;
    }
    public void insertAtBeginning(String data) {
        Node newNode = new Node(data);
        newNode.setNextNode(head);

        head = newNode;
    }

    public void insertAtEnd(String data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.getNextNode() != null) {
            current = current.getNextNode();
        }
        current.setNextNode(newNode);
    }

    public void insertAfter(String prevNodeData, String newData) {
        Node prevNode = findNode(prevNodeData);
        if (prevNode == null) {
            System.out.println("Previous node with data " + prevNodeData + " not found");
            return;
        }
        Node newNode = new Node(newData);
        newNode.setNextNode(prevNode.getNextNode());
        prevNode.setNextNode(newNode);
    }


    public void deleteNode(String data) {
        if (head == null) {
            return;
        }
        if (head.getData().equals(data)) {
            head = head.getNextNode();
            return;
        }
        Node current = head;
        Node prev = null;
        while (current != null && !current.getData().equals(data)) {
            prev = current;
            current = current.getNextNode();
        }
        if (current == null) {
            // Node with the given data not found
            return;
        }
        if (prev != null) {
            prev.setNextNode(current.getNextNode());
        } else {
            // This case should not occur given the earlier checks, but including for completeness
            head = current.getNextNode();
        }
    }

    private Node findNode(String data) {
        Node current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = current.getNextNode();
        }
        return null;
    }

    public String printList() {
        Node current = head;
        String fullList = "";
        while (current != null) {
            fullList = fullList + (current.getData() + " ");
            current = current.getNextNode();
        }
        return fullList;
    }
}

