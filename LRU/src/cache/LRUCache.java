package cache;

import model.LRUNode;

import java.util.HashMap;
import java.util.Optional;

public class LRUCache<T> implements Cache<T> {

    private final int capacity;
    private final HashMap<String, LRUNode<T>> indexMap = new HashMap<>();
    private LRUNode<T> head;
    private LRUNode<T> tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void put(String key, T value) {
        if (indexMap.containsKey(key)) {
            LRUNode<T> node = indexMap.get(key);
            deleteNode(node);
            addNode(key, value);
        } else {
            if (indexMap.size() >= capacity) {
                deleteNode(tail);
            }
            addNode(key, value);
        }
    }

    @Override
    public Optional<T> get(String key) {
        if (!indexMap.containsKey(key)) {
            return Optional.empty();
        }
        LRUNode<T> node = indexMap.get(key);
        deleteNode(node);
        addNode(node.getKey(), node.getValue());
        return Optional.of(node.getValue());
    }

    @Override
    public Integer delete(String key) {
        if (!indexMap.containsKey(key)) {
            return -1;
        }
        LRUNode<T> node = indexMap.get(key);
        deleteNode(node);
        return 1;
    }

    @Override
    public void print() {
        LRUNode<T> node = head;
        while (node != null) {
            System.out.println(node.getKey() + "-" + node.getValue());
            node = node.getNextNode();
        }
        System.out.println();
    }

    private void addNode(String key, T value) {
        LRUNode<T> node = new LRUNode<>(key, value);
        indexMap.put(key, node);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.setPreNode(node);
            node.setNextNode(head);
            head = node;
        }
    }

    private void deleteNode(LRUNode<T> node) {
        if (node == null) {
            return;
        }

        indexMap.remove(node.getKey());
        if (head != null && node.getKey().equals(head.getKey())) {
            head = head.getNextNode();
            head.setPreNode(null);
            return;
        }

        if (tail != null && node.getKey().equals(tail.getKey())) {
            tail = tail.getPreNode();
            tail.setNextNode(null);
            return;
        }

        LRUNode<T> nextNode = node.getNextNode();
        LRUNode<T> preNode = node.getPreNode();

        if (nextNode != null) {
            nextNode.setPreNode(preNode);
        }
        if (preNode != null) {
            preNode.setNextNode(nextNode);
        }
    }
}
