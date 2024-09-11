package com.tim_ohagan.interview.datastructure;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CustomHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    public CustomHeap() {
        heap = new ArrayList<>();
    }

    public void insert(T element) {
        heap.add(element);
        siftUp(heap.size() - 1);
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }

        return min;
    }

    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    private void siftUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private void siftDown(int index) {
        int minIndex = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(minIndex)) < 0) {
            minIndex = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(minIndex)) < 0) {
            minIndex = rightChild;
        }

        if (minIndex != index) {
            swap(index, minIndex);
            siftDown(minIndex);
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void heapify(ArrayList<T> list) {
        heap = new ArrayList<>(list);
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public ArrayList<T> heapSort() {
        ArrayList<T> sortedList = new ArrayList<>(heap);
        int size = sortedList.size();

        for (int i = size - 1; i > 0; i--) {
            swap(sortedList, 0, i);
            siftDownForHeapSort(sortedList, 0, i);
        }

        return sortedList;
    }

    private void siftDownForHeapSort(ArrayList<T> list, int index, int heapSize) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heapSize && list.get(leftChild).compareTo(list.get(largest)) > 0) {
            largest = leftChild;
        }

        if (rightChild < heapSize && list.get(rightChild).compareTo(list.get(largest)) > 0) {
            largest = rightChild;
        }

        if (largest != index) {
            swap(list, index, largest);
            siftDownForHeapSort(list, largest, heapSize);
        }
    }

    private void swap(ArrayList<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}