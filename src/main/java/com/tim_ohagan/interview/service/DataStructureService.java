package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import com.tim_ohagan.interview.datastructure.*;
import java.util.List;

@Service
public class DataStructureService {

    private CustomArray<Integer> customArray;
    private CustomLinkedList<Integer> customLinkedList;
    private CustomHashMap<String, Integer> customHashMap;
    private CustomStack<Integer> customStack;
    private CustomQueue<Integer> customQueue;
    private CustomTree<Integer> customTree;
    private CustomGraph<Integer> customGraph;
    private CustomHeap<Integer> customHeap;

    // Array operations
    public void createArray(int capacity) {
        this.customArray = new CustomArray<>(capacity);
    }

    public void addToArray(Integer element) {
        customArray.add(element);
    }

    public Integer getFromArray(int index) {
        return customArray.get(index);
    }

    // Linked List operations
    public void createLinkedList() {
        this.customLinkedList = new CustomLinkedList<>();
    }

    public void addToLinkedList(Integer element) {
        customLinkedList.addLast(element);
    }

    public Integer getFromLinkedList(int index) {
        if (index == 0) {
            return customLinkedList.getFirst();
        } else if (index == customLinkedList.size() - 1) {
            return customLinkedList.getLast();
        } else {
            throw new IndexOutOfBoundsException("CustomLinkedList only supports getting first or last element");
        }
    }
    
    
    // HashMap operations
    public void createHashMap() {
        this.customHashMap = new CustomHashMap<>();
    }

    public void putInHashMap(String key, Integer value) {
        customHashMap.put(key, value);
    }

    public Integer getFromHashMap(String key) {
        return customHashMap.get(key);
    }

    // Stack operations
    public void createStack() {
        this.customStack = new CustomStack<>();
    }

    public void pushToStack(Integer element) {
        customStack.push(element);
    }

    public Integer popFromStack() {
        return customStack.pop();
    }

    // Queue operations
    public void createQueue() {
        this.customQueue = new CustomQueue<>();
    }

    public void enqueueToQueue(Integer element) {
        customQueue.enqueue(element);
    }

    public Integer dequeueFromQueue() {
        return customQueue.dequeue();
    }

    // Tree operations
    public void createTree() {
        this.customTree = new CustomTree<>();
    }

    public void insertIntoTree(Integer element) {
        customTree.insert(element);
    }

    public boolean searchInTree(Integer element) {
        return customTree.search(element);
    }

    // Graph operations
    public void createGraph() {
        this.customGraph = new CustomGraph<Integer>(false);
    }

    public void addEdgeToGraph(Integer source, Integer destination) {
        customGraph.addEdge(source, destination);
    }

    public List<Integer> getNeighbors(Integer vertex) {
        return customGraph.getNeighbors(vertex);
    }

    // Heap operations
    public void createHeap() {
        this.customHeap = new CustomHeap<>();
    }

    public void insertIntoHeap(Integer element) {
        customHeap.insert(element);
    }

    public Integer extractMinFromHeap() {
        return customHeap.deleteMin();
    }
}