package com.tim_ohagan.interview.algorithm;

import java.util.*;

public class GraphAlgorithms {

    // Depth-First Search (DFS)
    public static <T> Set<T> depthFirstSearch(Map<T, List<T>> graph, T start) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (T neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return visited;
    }

    // Breadth-First Search (BFS)
    public static <T> Set<T> breadthFirstSearch(Map<T, List<T>> graph, T start) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.offer(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (T neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return visited;
    }

    // Dijkstra's Shortest Path Algorithm
    public static <T> Map<T, Integer> dijkstra(Map<T, Map<T, Integer>> graph, T start) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<Node<T>> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Set<T> visited = new HashSet<>();

        for (T vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);
        pq.offer(new Node<>(start, 0));

        while (!pq.isEmpty()) {
            Node<T> current = pq.poll();
            T currentVertex = current.vertex;

            if (visited.contains(currentVertex)) {
                continue;
            }
            visited.add(currentVertex);

            for (Map.Entry<T, Integer> neighbor : graph.get(currentVertex).entrySet()) {
                T nextVertex = neighbor.getKey();
                int newDist = distances.get(currentVertex) + neighbor.getValue();
                if (newDist < distances.get(nextVertex)) {
                    distances.put(nextVertex, newDist);
                    pq.offer(new Node<>(nextVertex, newDist));
                }
            }
        }
        return distances;
    }

    // Bellman-Ford Algorithm
    public static <T> Map<T, Integer> bellmanFord(Map<T, Map<T, Integer>> graph, T start) {
        Map<T, Integer> distances = new HashMap<>();
        for (T vertex : graph.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        for (int i = 0; i < graph.size() - 1; i++) {
            for (T u : graph.keySet()) {
                for (Map.Entry<T, Integer> v : graph.get(u).entrySet()) {
                    T destination = v.getKey();
                    int weight = v.getValue();
                    if (distances.get(u) != Integer.MAX_VALUE &&
                            distances.get(u) + weight < distances.get(destination)) {
                        distances.put(destination, distances.get(u) + weight);
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (T u : graph.keySet()) {
            for (Map.Entry<T, Integer> v : graph.get(u).entrySet()) {
                T destination = v.getKey();
                int weight = v.getValue();
                if (distances.get(u) != Integer.MAX_VALUE &&
                        distances.get(u) + weight < distances.get(destination)) {
                    throw new IllegalArgumentException("Graph contains a negative-weight cycle");
                }
            }
        }

        return distances;
    }

    // Floyd-Warshall Algorithm
    public static <T> Map<T, Map<T, Integer>> floydWarshall(Map<T, Map<T, Integer>> graph) {
        Map<T, Map<T, Integer>> distances = new HashMap<>();

        // Initialize distances
        for (T i : graph.keySet()) {
            distances.put(i, new HashMap<>());
            for (T j : graph.keySet()) {
                if (i.equals(j)) {
                    distances.get(i).put(j, 0);
                } else {
                    distances.get(i).put(j, graph.get(i).getOrDefault(j, Integer.MAX_VALUE));
                }
            }
        }

        // Calculate shortest paths
        for (T k : graph.keySet()) {
            for (T i : graph.keySet()) {
                for (T j : graph.keySet()) {
                    if (distances.get(i).get(k) != Integer.MAX_VALUE
                            && distances.get(k).get(j) != Integer.MAX_VALUE
                            && distances.get(i).get(k) + distances.get(k).get(j) < distances.get(i).get(j)) {
                        distances.get(i).put(j, distances.get(i).get(k) + distances.get(k).get(j));
                    }
                }
            }
        }

        return distances;
    }

    // Kruskal's Minimum Spanning Tree Algorithm
    public static <T extends Comparable<T>> List<Edge<T>> kruskalMST(Map<T, Map<T, Integer>> graph) {
        List<Edge<T>> allEdges = new ArrayList<>();
        for (T source : graph.keySet()) {
            for (Map.Entry<T, Integer> entry : graph.get(source).entrySet()) {
                allEdges.add(new Edge<>(source, entry.getKey(), entry.getValue()));
            }
        }
        allEdges.sort(Comparator.comparingInt(e -> e.weight));

        DisjointSet<T> disjointSet = new DisjointSet<>(graph.keySet());
        List<Edge<T>> mst = new ArrayList<>();

        for (Edge<T> edge : allEdges) {
            if (disjointSet.find(edge.source) != disjointSet.find(edge.destination)) {
                mst.add(edge);
                disjointSet.union(edge.source, edge.destination);
            }
        }

        return mst;
    }

    // Topological Sort
    public static <T> List<T> topologicalSort(Map<T, List<T>> graph) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();

        for (T vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                topologicalSortUtil(vertex, visited, stack, graph);
            }
        }

        List<T> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static <T> void topologicalSortUtil(T vertex, Set<T> visited, Stack<T> stack, Map<T, List<T>> graph) {
        visited.add(vertex);
        for (T neighbor : graph.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                topologicalSortUtil(neighbor, visited, stack, graph);
            }
        }
        stack.push(vertex);
    }

    // Utility classes
    private static class Node<T> {
        T vertex;
        int distance;

        Node(T vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    private static class Edge<T> {
        T source;
        T destination;
        int weight;

        Edge(T source, T destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static class DisjointSet<T> {
        private Map<T, T> parent;
        private Map<T, Integer> rank;

        public DisjointSet(Set<T> vertices) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            for (T vertex : vertices) {
                parent.put(vertex, vertex);
                rank.put(vertex, 0);
            }
        }

        public T find(T item) {
            if (parent.get(item).equals(item))
                return item;
            T result = find(parent.get(item));
            parent.put(item, result);
            return result;
        }

        public void union(T x, T y) {
            T xRoot = find(x);
            T yRoot = find(y);

            if (rank.get(xRoot) < rank.get(yRoot))
                parent.put(xRoot, yRoot);
            else if (rank.get(xRoot) > rank.get(yRoot))
                parent.put(yRoot, xRoot);
            else {
                parent.put(yRoot, xRoot);
                rank.put(xRoot, rank.get(xRoot) + 1);
            }
        }
    }
}