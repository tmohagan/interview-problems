package com.tim_ohagan.interview.dto.algorithm;

import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.List;

public class GraphSearchRequestDto {

    @NotNull(message = "Graph cannot be null")
    private Map<Integer, List<Integer>> graph;

    @NotNull(message = "Start vertex cannot be null")
    private Integer start;

    // Constructors
    public GraphSearchRequestDto() {}

    public GraphSearchRequestDto(Map<Integer, List<Integer>> graph, Integer start) {
        this.graph = graph;
        this.start = start;
    }

    // Getters and setters
    public Map<Integer, List<Integer>> getGraph() {
        return graph;
    }

    public void setGraph(Map<Integer, List<Integer>> graph) {
        this.graph = graph;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}