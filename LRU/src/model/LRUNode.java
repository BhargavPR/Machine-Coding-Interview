package model;

public class LRUNode<T> {

    private String key;
    private T value;
    private LRUNode<T> nextNode;
    private LRUNode<T> preNode;

    public LRUNode(String key, T value) {
        this.key = key;
        this.value = value;
        this.nextNode = null;
        this.preNode = null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LRUNode<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(LRUNode<T> nextNode) {
        this.nextNode = nextNode;
    }

    public LRUNode<T> getPreNode() {
        return preNode;
    }

    public void setPreNode(LRUNode<T> preNode) {
        this.preNode = preNode;
    }
}
