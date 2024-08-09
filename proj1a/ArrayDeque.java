public class ArrayDeque<T> {
    private T[] data;
    private int size;
    // first and last are the index of the last inserted item.
    private int first;
    private int last;

    private static final double USERATE = 0.25;
    private static final int FACTOR = 2;
    private static final int INITSIZE = 8;

    public ArrayDeque() {
        this.data = (T[]) new Object[INITSIZE];
        this.size = 0;
    }

    private void resizeTo(int newSize) {
        T[] newData = (T[]) new Object[newSize];
        for (int i = 0; i < this.size; i++) {
            newData[i] = this.get(i);
        }
        this.data = newData;
        this.first = 0;
        this.last = this.size - 1;
    }

    public void addFirst(T item) {
        if (this.size == 0) {
            this.first = 0;
            this.last = 0;
            this.data[this.first] = item;
            this.size++;
            return;
        }

        if (size == this.data.length) {
            resizeTo(this.size * FACTOR);
        }

        this.first = (this.first - 1 + this.data.length) % this.data.length;
        this.data[this.first] = item;
        this.size++;
    }

    public void addLast(T item) {
        if (this.size == 0) {
            this.first = 0;
            this.last = 0;
            this.data[this.last] = item;
            this.size++;
            return;
        }

        if (size == this.data.length) {
            resizeTo(this.size * FACTOR);
        }

        this.last = (this.last + 1 + this.data.length) % this.data.length;
        this.data[this.last] = item;
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        T item = this.data[this.first];
        this.data[this.first] = null;
        this.first = (this.first + 1) % this.data.length;
        this.size--;

        if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
            resizeTo(this.data.length / FACTOR);
        }
        return item;
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        T item = this.data[this.last];
        this.data[this.last] = null;
        this.last = (this.last - 1 + this.data.length) % this.data.length;
        this.size--;

        if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
            resizeTo(this.data.length / FACTOR);
        }
        return item;
    }

    public T get(int index) {
        if (index > this.size || index < 0) {
            return null;
        }

        return this.data[(this.first + index) % this.data.length];
    }
}
