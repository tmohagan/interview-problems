package com.tim_ohagan.interview.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AlgorithmService {

    // Sorting Algorithms
    public int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int midpoint = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, midpoint);
        int[] right = Arrays.copyOfRange(array, midpoint, array.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftPointer = 0, rightPointer = 0, resultPointer = 0;
        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer] < right[rightPointer]) {
                result[resultPointer++] = left[leftPointer++];
            } else {
                result[resultPointer++] = right[rightPointer++];
            }
        }
        while (leftPointer < left.length) {
            result[resultPointer++] = left[leftPointer++];
        }
        while (rightPointer < right.length) {
            result[resultPointer++] = right[rightPointer++];
        }
        return result;
    }

    public int[] quickSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    private void quickSortHelper(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSortHelper(array, low, pivotIndex - 1);
            quickSortHelper(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Searching Algorithms
    public int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Graph Algorithms
    public List<Integer> depthFirstSearch(Map<Integer, List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsHelper(graph, start, visited, result);
        return result;
    }

    private void dfsHelper(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited, List<Integer> result) {
        if (!visited.contains(node)) {
            visited.add(node);
            result.add(node);
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                dfsHelper(graph, neighbor, visited, result);
            }
        }
    }

    public List<Integer> breadthFirstSearch(Map<Integer, List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    // Other Algorithms
    public List<Integer> greedyActivitySelection(int[] start, int[] finish) {
        List<Integer> selected = new ArrayList<>();
        int n = start.length;

        // Sort activities by finish time
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (a, b) -> Integer.compare(finish[a], finish[b]));

        selected.add(indices[0]);
        int lastFinish = finish[indices[0]];

        for (int i = 1; i < n; i++) {
            if (start[indices[i]] >= lastFinish) {
                selected.add(indices[i]);
                lastFinish = finish[indices[i]];
            }
        }

        return selected;
    }

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(numbers[i], i);
        }
        return new int[]{-1, -1}; // No solution found
    }

    public int maxSumSubarray(int[] numbers, int k) {
        if (numbers.length < k) {
            throw new IllegalArgumentException("Array length should be at least k");
        }

        int maxSum = 0;
        int windowSum = 0;

        // Compute sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += numbers[i];
        }
        maxSum = windowSum;

        // Slide the window and update max sum
        for (int i = k; i < numbers.length; i++) {
            windowSum = windowSum - numbers[i - k] + numbers[i];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public int[] prefixSums(int[] numbers) {
        int[] prefixSum = new int[numbers.length];
        prefixSum[0] = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + numbers[i];
        }

        return prefixSum;
    }

    public List<Integer> kWayMerge(List<List<Integer>> sortedArrays) {
        PriorityQueue<QueueNode> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);
        List<Integer> result = new ArrayList<>();

        // Add the first element from each array to the min heap
        for (int i = 0; i < sortedArrays.size(); i++) {
            if (!sortedArrays.get(i).isEmpty()) {
                minHeap.offer(new QueueNode(sortedArrays.get(i).get(0), i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            QueueNode node = minHeap.poll();
            result.add(node.value);

            if (node.index + 1 < sortedArrays.get(node.arrayIndex).size()) {
                minHeap.offer(new QueueNode(
                    sortedArrays.get(node.arrayIndex).get(node.index + 1),
                    node.arrayIndex,
                    node.index + 1
                ));
            }
        }

        return result;
    }

    private static class QueueNode {
        int value;
        int arrayIndex;
        int index;

        QueueNode(int value, int arrayIndex, int index) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.index = index;
        }
    }
}