package com.tim_ohagan.interview.datastructure;

import java.util.*;

public class CustomGraph<T> {
    private Map<T, List<T>> adjacencyList;
    private boolean isDirected;

    public CustomGraph(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.get(source).add(destination);
        if (!isDirected) {
            adjacencyList.putIfAbsent(destination, new ArrayList<>());
            adjacencyList.get(destination).add(source);
        }
    }

    public void removeVertex(T vertex) {
        adjacencyList.values().forEach(e -> e.remove(vertex));
        adjacencyList.remove(vertex);
    }

    public void removeEdge(T source, T destination) {
        List<T> srcEdges = adjacencyList.get(source);
        List<T> destEdges = adjacencyList.get(destination);
        if (srcEdges != null) {
            srcEdges.remove(destination);
        }
        if (!isDirected && destEdges != null) {
            destEdges.remove(source);
        }
    }

    public List<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public boolean hasVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean hasEdge(T source, T destination) {
        return adjacencyList.containsKey(source) && adjacencyList.get(source).contains(destination);
    }

    public int getVertexCount() {
        return adjacencyList.size();
    }

    public int getEdgeCount() {
        int count = adjacencyList.values().stream().mapToInt(List::size).sum();
        return isDirected ? count : count / 2;
    }

    public List<T> depthFirstSearch(T start) {
        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        dfsHelper(start, visited, result);
        return result;
    }

    private void dfsHelper(T vertex, Set<T> visited, List<T> result) {
        visited.add(vertex);
        result.add(vertex);
        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }

    public List<T> breadthFirstSearch(T start) {
        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();

        visited.add(start);
        queue.offer(start);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            result.add(vertex);

            for (T neighbor : getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    public boolean hasCycle() {
        Set<T> visited = new HashSet<>();
        Set<T> recursionStack = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (hasCycleHelper(vertex, visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleHelper(T vertex, Set<T> visited, Set<T> recursionStack) {
        if (recursionStack.contains(vertex)) {
            return true;
        }

        if (visited.contains(vertex)) {
            return false;
        }

        visited.add(vertex);
        recursionStack.add(vertex);

        for (T neighbor : getNeighbors(vertex)) {
            if (hasCycleHelper(neighbor, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack.remove(vertex);
        return false;
    }

    public List<T> topologicalSort() {
        if (!isDirected) {
            throw new IllegalStateException("Topological sort is only applicable to directed graphs");
        }

        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortHelper(vertex, visited, result);
            }
        }

        Collections.reverse(result);
        return result;
    }

    private void topologicalSortHelper(T vertex, Set<T> visited, List<T> result) {
        visited.add(vertex);

        for (T neighbor : getNeighbors(vertex)) {
            if (!visited.contains(neighbor)) {
                topologicalSortHelper(neighbor, visited, result);
            }
        }

        result.add(vertex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, List<T>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey().toString()).append(": ");
            sb.append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();
    }
}