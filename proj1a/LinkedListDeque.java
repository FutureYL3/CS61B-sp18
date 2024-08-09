public class LinkedListDeque<T> {
    private final Node<T> head;
    private final Node<T> tail;
    private int size;

    public LinkedListDeque() {
        this.head = new Node<>(null);
        this.head.prev = null;
        this.tail = new Node<>(null);
        this.tail.next = null;
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    private static class Node<K> {
        private final K item;
        private Node<K> next;
        private Node<K> prev;

        Node(K data) {
            this.item = data;
        }

        public K getRecursiveHelper(int index, Node<K> current) {
            if (index == 0) {
                return current.item;
            } else {
                current = current.next;
                return getRecursiveHelper(index - 1, current);
            }
        }
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);
        if (this.size == 0) {
            this.tail.prev = newNode;
        }
        newNode.next = this.head.next;
        newNode.prev = this.head;
        this.head.next.prev = newNode;
        this.head.next = newNode;
        this.size++;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (this.size == 0) {
            this.head.next = newNode;
        }
        newNode.next = this.tail;
        newNode.prev = this.tail.prev;
        this.tail.prev.next = newNode;
        this.tail.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void printDeque() {
        Node<T> cur = this.head.next;
        while (cur != this.tail) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
    }

    public int size() {
        return this.size;
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        Node<T> toRemoved = this.head.next;
        T item = toRemoved.item;
        this.head.next = toRemoved.next;
        toRemoved.next.prev = this.head;
        toRemoved.next = null;
        toRemoved.prev = null;
        this.size--;
        return item;
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        Node<T> toRemoved = this.tail.prev;
        T item = toRemoved.item;
        this.tail.prev = toRemoved.prev;
        toRemoved.prev.next = this.tail;
        toRemoved.prev = null;
        toRemoved.next = null;
        this.size--;
        return item;
    }

    public T get(int index) {
        if (this.size - 1 < index || index < 0) {
            return null;
        }
        int counter = 0;
        Node<T> cur = this.head.next;
        while (counter != index) {
            cur = cur.next;
            counter++;
        }
        return cur.item;
    }

    public T getRecursive(int index) {
        if (this.size - 1 < index || index < 0) {
            return null;
        }
        Node<T> cur = this.head.next;
        return cur.getRecursiveHelper(index, cur);
    }
}
