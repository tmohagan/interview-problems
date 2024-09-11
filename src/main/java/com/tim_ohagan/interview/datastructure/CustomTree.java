package com.tim_ohagan.interview.datastructure;

import java.util.ArrayList;
import java.util.List;

public class CustomTree<T extends Comparable<T>> {
    private Node root;

    private class Node {
        T data;
        Node left, right;

        Node(T data) {
            this.data = data;
            left = right = null;
        }
    }

    public CustomTree() {
        root = null;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node insertRec(Node root, T data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data.compareTo(root.data) < 0)
            root.left = insertRec(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = insertRec(root.right, data);

        return root;
    }

    public boolean search(T data) {
        return searchRec(root, data);
    }

    private boolean searchRec(Node root, T data) {
        if (root == null || root.data.equals(data))
            return root != null;

        if (data.compareTo(root.data) < 0)
            return searchRec(root.left, data);

        return searchRec(root.right, data);
    }

    public void delete(T data) {
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node root, T data) {
        if (root == null) return null;

        if (data.compareTo(root.data) < 0)
            root.left = deleteRec(root.left, data);
        else if (data.compareTo(root.data) > 0)
            root.right = deleteRec(root.right, data);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private T minValue(Node root) {
        T minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    public List<T> inorderTraversal() {
        List<T> result = new ArrayList<>();
        inorderTraversalRec(root, result);
        return result;
    }

    private void inorderTraversalRec(Node root, List<T> result) {
        if (root != null) {
            inorderTraversalRec(root.left, result);
            result.add(root.data);
            inorderTraversalRec(root.right, result);
        }
    }

    public List<T> preorderTraversal() {
        List<T> result = new ArrayList<>();
        preorderTraversalRec(root, result);
        return result;
    }

    private void preorderTraversalRec(Node root, List<T> result) {
        if (root != null) {
            result.add(root.data);
            preorderTraversalRec(root.left, result);
            preorderTraversalRec(root.right, result);
        }
    }

    public List<T> postorderTraversal() {
        List<T> result = new ArrayList<>();
        postorderTraversalRec(root, result);
        return result;
    }

    private void postorderTraversalRec(Node root, List<T> result) {
        if (root != null) {
            postorderTraversalRec(root.left, result);
            postorderTraversalRec(root.right, result);
            result.add(root.data);
        }
    }

    public T findMin() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    public T findMax() {
        if (root == null) throw new IllegalStateException("Tree is empty");
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public int getHeight() {
        return getHeightRec(root);
    }

    private int getHeightRec(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        return "InOrder Traversal: " + inorderTraversal().toString();
    }
}