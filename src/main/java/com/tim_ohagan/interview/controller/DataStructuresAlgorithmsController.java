package com.tim_ohagan.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tim_ohagan.interview.service.AlgorithmService;
import com.tim_ohagan.interview.service.DataStructureService;
import com.tim_ohagan.interview.dto.algorithm.ActivitySelectionRequestDto;
import com.tim_ohagan.interview.dto.algorithm.GraphSearchRequestDto;
import com.tim_ohagan.interview.dto.algorithm.KWayMergeRequestDto;
import com.tim_ohagan.interview.dto.algorithm.PrefixSumsRequestDto;
import com.tim_ohagan.interview.dto.algorithm.SearchRequestDto;
import com.tim_ohagan.interview.dto.algorithm.SlidingWindowRequestDto;
import com.tim_ohagan.interview.dto.algorithm.SortRequestDto;
import com.tim_ohagan.interview.dto.algorithm.TwoSumRequestDto;
import com.tim_ohagan.interview.dto.datastructure.AddEdgeRequestDto;
import com.tim_ohagan.interview.dto.datastructure.AddToArrayRequestDto;
import com.tim_ohagan.interview.dto.datastructure.AddToListRequestDto;
import com.tim_ohagan.interview.dto.datastructure.CreateArrayRequestDto;
import com.tim_ohagan.interview.dto.datastructure.EnqueueRequestDto;
import com.tim_ohagan.interview.dto.datastructure.InsertToHeapRequestDto;
import com.tim_ohagan.interview.dto.datastructure.InsertToTreeRequestDto;
import com.tim_ohagan.interview.dto.datastructure.PushToStackRequestDto;
import com.tim_ohagan.interview.dto.datastructure.PutInMapRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api/dsa")
public class DataStructuresAlgorithmsController {

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private DataStructureService dataStructureService;

    // Sorting Algorithms
    @PostMapping("/insertionsort")
    public int[] insertionSort(@RequestBody SortRequestDto request) {
        return algorithmService.insertionSort(request.getArray());
    }

    @PostMapping("/mergesort")
    public int[] mergeSort(@RequestBody SortRequestDto request) {
        return algorithmService.mergeSort(request.getArray());
    }

    @PostMapping("/quicksort")
    public int[] quickSort(@RequestBody SortRequestDto request) {
        return algorithmService.quickSort(request.getArray());
    }

    // Searching Algorithms
    @GetMapping("/linearsearch")
    public int linearSearch(@RequestBody SearchRequestDto request) {
        return algorithmService.linearSearch(request.getArray(), request.getTarget());
    }

    @GetMapping("/binarysearch")
    public int binarySearch(@RequestBody SearchRequestDto request) {
        return algorithmService.binarySearch(request.getArray(), request.getTarget());
    }

    // Graph Algorithms
    @PostMapping("/dfs")
    public List<Integer> depthFirstSearch(@RequestBody GraphSearchRequestDto request) {
        return algorithmService.depthFirstSearch(request.getGraph(), request.getStart());
    }

    @PostMapping("/bfs")
    public List<Integer> breadthFirstSearch(@RequestBody GraphSearchRequestDto request) {
        return algorithmService.breadthFirstSearch(request.getGraph(), request.getStart());
    }

    // Other Algorithms
    @PostMapping("/greedy")
    public List<Integer> greedyActivitySelection(@RequestBody ActivitySelectionRequestDto request) {
        return algorithmService.greedyActivitySelection(request.getStart(), request.getFinish());
    }

    @PostMapping("/twopointers")
    public int[] twoSum(@RequestBody TwoSumRequestDto request) {
        return algorithmService.twoSum(request.getNumbers(), request.getTarget());
    }

    @PostMapping("/slidingwindow")
    public int maxSumSubarray(@RequestBody SlidingWindowRequestDto request) {
        return algorithmService.maxSumSubarray(request.getNumbers(), request.getK());
    }

    @PostMapping("/prefixsums")
    public int[] prefixSums(@RequestBody PrefixSumsRequestDto request) {
        return algorithmService.prefixSums(request.getNumbers());
    }

    @PostMapping("/kwaymerge")
    public List<Integer> kWayMerge(@RequestBody KWayMergeRequestDto request) {
        return algorithmService.kWayMerge(request.getSortedArrays());
    }

    // Data Structure Operations
    @PostMapping("/array/create")
    public void createArray(@RequestBody CreateArrayRequestDto request) {
        dataStructureService.createArray(request.getCapacity());
    }

    @PostMapping("/array/add")
    public void addToArray(@RequestBody AddToArrayRequestDto request) {
        dataStructureService.addToArray(request.getElement());
    }

    @GetMapping("/array/get/{index}")
    public Object getFromArray(@PathVariable int index) {
        return dataStructureService.getFromArray(index);
    }

    @PostMapping("/linkedlist/create")
    public void createLinkedList() {
        dataStructureService.createLinkedList();
    }

    @PostMapping("/linkedlist/add")
    public void addToLinkedList(@RequestBody AddToListRequestDto request) {
        dataStructureService.addToLinkedList(request.getElement());
    }

    @GetMapping("/linkedlist/get/{index}")
    public Object getFromLinkedList(@PathVariable int index) {
        return dataStructureService.getFromLinkedList(index);
    }

    @PostMapping("/hashmap/create")
    public void createHashMap() {
        dataStructureService.createHashMap();
    }

    @PostMapping("/hashmap/put")
    public void putInHashMap(@RequestBody PutInMapRequestDto request) {
        dataStructureService.putInHashMap(request.getKey(), request.getValue());
    }

    @GetMapping("/hashmap/get/{key}")
    public Object getFromHashMap(@PathVariable String key) {
        return dataStructureService.getFromHashMap(key);
    }

    @PostMapping("/stack/create")
    public void createStack() {
        dataStructureService.createStack();
    }

    @PostMapping("/stack/push")
    public void pushToStack(@RequestBody PushToStackRequestDto request) {
        dataStructureService.pushToStack(request.getElement());
    }

    @GetMapping("/stack/pop")
    public Object popFromStack() {
        return dataStructureService.popFromStack();
    }

    @PostMapping("/queue/create")
    public void createQueue() {
        dataStructureService.createQueue();
    }

    @PostMapping("/queue/enqueue")
    public void enqueueToQueue(@RequestBody EnqueueRequestDto request) {
        dataStructureService.enqueueToQueue(request.getElement());
    }

    @GetMapping("/queue/dequeue")
    public Object dequeueFromQueue() {
        return dataStructureService.dequeueFromQueue();
    }

    @PostMapping("/tree/create")
    public void createTree() {
        dataStructureService.createTree();
    }

    @PostMapping("/tree/insert")
    public void insertIntoTree(@RequestBody InsertToTreeRequestDto request) {
        dataStructureService.insertIntoTree(request.getElement());
    }

    @GetMapping("/tree/search/{element}")
    public boolean searchInTree(@PathVariable int element) {
        return dataStructureService.searchInTree(element);
    }

    @PostMapping("/graph/create")
    public void createGraph() {
        dataStructureService.createGraph();
    }

    @PostMapping("/graph/addedge")
    public void addEdgeToGraph(@RequestBody AddEdgeRequestDto request) {
        dataStructureService.addEdgeToGraph(request.getSource(), request.getDestination());
    }

    @GetMapping("/graph/neighbors/{vertex}")
    public List<Integer> getNeighbors(@PathVariable int vertex) {
        return dataStructureService.getNeighbors(vertex);
    }

    @PostMapping("/heap/create")
    public void createHeap() {
        dataStructureService.createHeap();
    }

    @PostMapping("/heap/insert")
    public void insertIntoHeap(@RequestBody InsertToHeapRequestDto request) {
        dataStructureService.insertIntoHeap(request.getElement());
    }

    @GetMapping("/heap/extractmin")
    public Object extractMinFromHeap() {
        return dataStructureService.extractMinFromHeap();
    }
}