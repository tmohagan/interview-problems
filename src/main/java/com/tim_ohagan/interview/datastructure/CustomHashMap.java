package com.tim_ohagan.interview.datastructure;

import java.util.ArrayList;
import java.util.List;

public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size;
    private int capacity;
    private float loadFactor;
    private List<Entry<K, V>>[] buckets;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.size = 0;
        this.buckets = new List[capacity];
    }

    @SuppressWarnings("unchecked")
    public CustomHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity must be non-negative");
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be positive");
        }
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.buckets = new List[capacity];
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if ((float) size / capacity >= loadFactor) {
            resize();
        }
        int bucketIndex = getBucketIndex(key);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>();
        }
        for (Entry<K, V> entry : buckets[bucketIndex]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets[bucketIndex].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int bucketIndex = getBucketIndex(key);
        if (buckets[bucketIndex] != null) {
            for (Entry<K, V> entry : buckets[bucketIndex]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int bucketIndex = getBucketIndex(key);
        if (buckets[bucketIndex] != null) {
            for (int i = 0; i < buckets[bucketIndex].size(); i++) {
                Entry<K, V> entry = buckets[bucketIndex].get(i);
                if (entry.key.equals(key)) {
                    buckets[bucketIndex].remove(i);
                    size--;
                    return entry.value;
                }
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        for (int i = 0; i < capacity; i++) {
            buckets[i] = null;
        }
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        List<Entry<K, V>>[] newBuckets = new List[newCapacity];
        for (List<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new ArrayList<>();
                    }
                    newBuckets[newIndex].add(entry);
                }
            }
        }
        buckets = newBuckets;
        capacity = newCapacity;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean first = true;
        for (List<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (!first) {
                        sb.append(", ");
                    }
                    sb.append(entry.key).append("=").append(entry.value);
                    first = false;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }
}