package com.tim_ohagan.interview.algorithm;

public class SearchAlgorithms {

    // Linear Search
    public static <T extends Comparable<T>> int linearSearch(T[] arr, T key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(key) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search (Iterative)
    public static <T extends Comparable<T>> int binarySearch(T[] arr, T key) {
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Array must be sorted for binary search");
        }
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = arr[mid].compareTo(key);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Binary Search (Recursive)
    public static <T extends Comparable<T>> int binarySearchRecursive(T[] arr, T key) {
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Array must be sorted for binary search");
        }
        return binarySearchRecursiveHelper(arr, key, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> int binarySearchRecursiveHelper(T[] arr, T key, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        int cmp = arr[mid].compareTo(key);
        if (cmp == 0) {
            return mid;
        } else if (cmp < 0) {
            return binarySearchRecursiveHelper(arr, key, mid + 1, high);
        } else {
            return binarySearchRecursiveHelper(arr, key, low, mid - 1);
        }
    }

    // Jump Search
    public static <T extends Comparable<T>> int jumpSearch(T[] arr, T key) {
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Array must be sorted for jump search");
        }
        int n = arr.length;
        int step = (int) Math.sqrt(n);
        int prev = 0;
        while (arr[Math.min(step, n) - 1].compareTo(key) < 0) {
            prev = step;
            step += (int) Math.sqrt(n);
            if (prev >= n) {
                return -1;
            }
        }
        while (arr[prev].compareTo(key) < 0) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }
        if (arr[prev].compareTo(key) == 0) {
            return prev;
        }
        return -1;
    }

    // Interpolation Search
    public static int interpolationSearch(Integer[] arr, int key) {
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Array must be sorted for interpolation search");
        }
        int low = 0, high = arr.length - 1;
        while (low <= high && key >= arr[low] && key <= arr[high]) {
            if (low == high) {
                if (arr[low] == key) return low;
                return -1;
            }
            int pos = low + (((high - low) / (arr[high] - arr[low])) * (key - arr[low]));
            if (arr[pos] == key) {
                return pos;
            }
            if (arr[pos] < key) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1;
    }

    // Exponential Search
    public static <T extends Comparable<T>> int exponentialSearch(T[] arr, T key) {
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Array must be sorted for exponential search");
        }
        if (arr[0].compareTo(key) == 0) {
            return 0;
        }
        int i = 1;
        while (i < arr.length && arr[i].compareTo(key) <= 0) {
            i = i * 2;
        }
        return binarySearchRecursiveHelper(arr, key, i / 2, Math.min(i, arr.length - 1));
    }

    // Utility method to check if array is sorted
    private static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    // Utility method to print array
    public static <T> void printArray(T[] arr) {
        for (T t : arr) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
}