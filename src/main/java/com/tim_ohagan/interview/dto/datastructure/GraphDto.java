package com.tim_ohagan.interview.dto.datastructure;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import jakarta.validation.constraints.NotNull;

public class GraphDto {

    @NotNull(message = "Graph adjacency list cannot be null")
    private Map<Integer, List<Integer>> adjacencyList;

    public GraphDto() {
        this.adjacencyList = new HashMap<>();
    }

    public GraphDto(Map<Integer, List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public Map<Integer, List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public void setAdjacencyList(Map<Integer, List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public void addVertex(Integer vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(Integer source, Integer destination) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.get(source).add(destination);
        // If it's an undirected graph, uncomment the following lines
        // adjacencyList.putIfAbsent(destination, new ArrayList<>());
        // adjacencyList.get(destination).add(source);
    }

    public List<Integer> getNeighbors(Integer vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public boolean hasVertex(Integer vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public boolean hasEdge(Integer source, Integer destination) {
        return adjacencyList.containsKey(source) && adjacencyList.get(source).contains(destination);
    }

    public void removeVertex(Integer vertex) {
        adjacencyList.values().forEach(e -> e.remove(vertex));
        adjacencyList.remove(vertex);
    }

    public void removeEdge(Integer source, Integer destination) {
        List<Integer> sourceNeighbors = adjacencyList.get(source);
        if (sourceNeighbors != null) {
            sourceNeighbors.remove(destination);
        }
        // If it's an undirected graph, uncomment the following lines
        // List<Integer> destNeighbors = adjacencyList.get(destination);
        // if (destNeighbors != null) {
        //     destNeighbors.remove(source);
        // }
    }

    public int getVertexCount() {
        return adjacencyList.size();
    }

    public int getEdgeCount() {
        return adjacencyList.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}