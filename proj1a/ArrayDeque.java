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

//        if (this.first == -1) {
//            int index = this.last - this.size + 1 >= 0
//                    ? this.last - this.size + 1 : this.last + 1 + this.data.length - this.size;
//            this.first = index == 0
//                    ? this.data.length - 1 : index - 1;
//            this.data[this.first] = item;
//            this.size++;
//            return;
//        }

        if (size == this.data.length) {
            resizeTo(this.size * FACTOR);
//            T[] newData = (T[]) new Object[this.data.length * FACTOR];
//            if (this.first < this.last) {
//                if (this.first != -1) {
//                    System.arraycopy(this.data, 0, newData, 0, this.data.length);
//                } else {
//                    System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                    System.arraycopy(this.data, this.last + 1,
//                            newData, newData.length - (this.size - this.last - 1),
//                            this.size - this.last - 1);
//                    this.first = newData.length - (this.size - this.last - 1) - 1;
//                    this.data = newData;
//                    this.data[this.first] = item;
//                    this.size++;
//                    return;
//                }
//            } else if (this.last != -1) {
//                int rightLength = this.size - this.last - 1;
//                System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                System.arraycopy(this.data, this.first,
//                        newData, newData.length - rightLength, rightLength);
//                this.first = newData.length - rightLength;
//            } else {
//                int rightLength = this.size - this.first;
//                System.arraycopy(this.data, 0,
//                        newData, 0, this.size - rightLength);
//                System.arraycopy(this.data, this.first,
//                        newData, newData.length - rightLength, rightLength);
//                this.first = newData.length - rightLength;
//            }
//            this.data = newData;
//            if (this.first == 0) {
//                this.first = this.data.length - 1;
//            } else {
//                this.first--;
//            }
//            this.data[this.first] = item;
//            this.size++;
//            return;
        }

//        if (this.first == 0) {
//            this.first = this.data.length - 1;
//        } else {
//            this.first--;
//        }
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

//        if (this.last == -1) {
//            int index = this.first + this.size - 1 <= this.data.length - 1
//                    ? this.first + this.size - 1 : this.size - this.data.length + this.first - 1;
//            this.last = index == this.data.length - 1 ? 0 : index + 1;
//            this.data[this.last] = item;
//            this.size++;
//            return;
//        }

        if (size == this.data.length) {
            resizeTo(this.size * FACTOR);
//            T[] newData = (T[]) new Object[this.data.length * FACTOR];
//            if (this.first < this.last && this.first != -1) {
//                System.arraycopy(this.data, 0, newData, 0, this.data.length);
//            } else if (this.first > this.last) {
//                if (this.last == -1) {
//                    System.arraycopy(this.data, 0, newData, 0, this.first);
//                    System.arraycopy(this.data, this.first, newData,
//                            newData.length - (this.size - this.first), this.size - this.first);
//                    this.last = this.first;
//                    this.first = newData.length - (this.size - this.first);
//                    this.data = newData;
//                    this.data[this.last] = item;
//                    this.size++;
//                    return;
//                } else {
//                    int rightLength = this.size - this.last - 1;
//                    System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                    System.arraycopy(this.data, this.first,
//                            newData, newData.length - (rightLength), rightLength);
//                }
//            } else {
//                int rightLength = this.size - this.last - 1;
//                System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                System.arraycopy(this.data, this.last + 1,
//                        newData, newData.length - rightLength, rightLength);
//            }
//            this.data = newData;
//            if (this.last == this.data.length - 1) {
//                this.last = 0;
//            } else {
//                this.last++;
//            }
//            this.data[this.last] = item;
//            this.size++;
//            return;
        }

//        if (this.last == this.data.length - 1) {
//            this.last = 0;
//        } else {
//            this.last++;
//        }
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
//        if (this.size == 0) {
//            return;
//        }
//        if (this.first == -1) {
//            int index = this.last - this.size + 1 >= 0
//                    ? this.last - this.size + 1 : this.last + 1 + this.data.length - this.size;
//            while (index <= this.last) {
//                System.out.print(this.data[index] + " ");
//                if (index == this.data.length - 1) {
//                    index = 0;
//                } else {
//                    index++;
//                }
//            }
//        }
//        if (this.last == -1) {
//            int index = this.first;
//            for (int i = 0; i < this.size; i++) {
//                System.out.print(this.data[index] + " ");
//                if (index == this.data.length - 1) {
//                    index = 0;
//                } else {
//                    index++;
//                }
//            }
//        }
//        int curIndex = this.first;
//        while (true) {
//            System.out.print(this.data[curIndex] + " ");
//            if (curIndex == this.last) {
//                break;
//            }
//
//            if (curIndex == this.data.length - 1) {
//                curIndex = 0;
//            } else {
//                curIndex++;
//            }
//        }
    }

    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        T item = this.data[this.first];
        this.data[this.first] = null;
        this.first = (this.first + 1) % this.data.length;
        this.size--;
//        if (this.first != -1) {
//            item = this.data[this.first];
//            this.data[this.first] = null;
//            this.first = (this.first + 1) % this.data.length;
//        } else {
//            int index = this.last - this.size + 1 >= 0
//                    ? this.last - this.size + 1 : this.last + 1 + this.data.length - this.size;
//            item = this.data[index];
//            this.data[index] = null;
//        }
//        this.size--;
        if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
            resizeTo(this.data.length / FACTOR);
        }
        return item;
//        if (this.first == -1) {
//            int index = this.last - this.size + 1 >= 0
//                    ? this.last - this.size + 1 : this.last + 1 + this.data.length - this.size;
//            this.size--;
//
//            if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
//                T[] newData = (T[]) new Object[this.data.length / FACTOR];
//                if (this.last - this.size + 1 >= 0) {
//                    System.arraycopy(this.data, index + 1, newData, 0, this.size);
//                } else {
//                    System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                    System.arraycopy(this.data, index + 1, newData,
//                            newData.length - (this.size - this.last), this.size - this.last);
//                }
//                this.data = newData;
//            }
//            return this.data[index];
//        }
//        int index = this.first;
//        if (this.first == this.data.length - 1) {
//            this.first = 0;
//        } else {
//            this.first++;
//        }
//        this.size--;
//
//        if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
//            T[] newData = (T[]) new Object[this.data.length / FACTOR];
//            if (this.last == -1) {
//                int idx = this.first + this.size - 1 <= this.data.length - 1
//                        ? this.first + this.size - 1
//                        : this.size - this.data.length + this.first - 1;
//                if (this.first + this.size - 1 <= this.data.length - 1) {
//                    System.arraycopy(this.data, this.first, newData, 0, this.size);
//                } else {
//                    System.arraycopy(this.data, 0, newData, 0, idx + 1);
//                    System.arraycopy(this.data, this.first, newData,
//                            newData.length - (this.size - idx - 1), this.size - idx - 1);
//                }
//            } else {
//                if (this.first < this.last) {
//                    System.arraycopy(this.data, this.first, newData, 0, this.size);
//                } else {
//                    System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                    System.arraycopy(this.data, this.first,
//                            newData, newData.length - (this.size - this.last - 1),
//                            this.size - this.last - 1);
//                }
//            }
//            this.data = newData;
//        }
//        return this.data[index];
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        T item = this.data[this.last];
        this.data[this.last] = null;
        this.last = (this.last - 1 + this.data.length) % this.data.length;
        this.size--;
//        if (this.last != -1) {
//            item = this.data[this.last];
//            this.data[this.last] = null;
//            this.last = (this.last - 1 + this.data.length) % this.data.length;
//        } else {
//            int index = this.first + this.size - 1 <= this.data.length - 1
//                    ? this.first + this.size - 1 : this.size - this.data.length + this.first - 1;
//            item = this.data[index];
//            this.data[index] = null;
//        }
//        this.size--;
        if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
            resizeTo(this.data.length / FACTOR);
        }
        return item;
//        if (this.last == -1) {
//            int index = this.first + this.size - 1 <= this.data.length - 1
//                    ? this.first + this.size - 1 : this.size - this.data.length + this.first - 1;
//            this.size--;
//
//            if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
//                T[] newData = (T[]) new Object[this.data.length / FACTOR];
//                if (this.first + this.size - 1 <= this.data.length - 1) {
//                    System.arraycopy(this.data, index + 1, newData, 0, this.size);
//                } else {
//                    System.arraycopy(this.data, 0, newData, 0, index);
//                    System.arraycopy(this.data, this.first, newData,
//                            newData.length - (this.size - index), this.data.length - index);
//                }
//                this.data = newData;
//            }
//            return this.data[index];
//        }
//        int index = this.last;
//        if (this.last == 0) {
//            this.last = this.data.length - 1;
//        } else {
//            this.last--;
//        }
//        this.size--;
//
//        if (this.data.length >= 16 && 1.0 * this.size / this.data.length < USERATE) {
//            T[] newData = (T[]) new Object[this.data.length / FACTOR];
//            if (this.first == -1) {
//                int idx = this.last - this.size + 1 >= 0
//                        ? this.last - this.size + 1
//                        : this.last + 1 + this.data.length - this.size;
//                if (this.last - this.size + 1 >= 0) {
//                    System.arraycopy(this.data, idx, newData, 0, this.size);
//                } else {
//                    System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                    System.arraycopy(this.data, idx,
//                            newData, newData.length - (this.size - this.last - 1),
//                            this.size - this.last - 1);
//                }
//            } else {
//                if (this.first < this.last) {
//                    System.arraycopy(this.data, this.first, newData, 0, this.size);
//                } else {
//                    System.arraycopy(this.data, 0, newData, 0, this.last + 1);
//                    System.arraycopy(this.data, this.first,
//                            newData, newData.length - (this.size - this.last - 1),
//                            this.size - this.last - 1);
//                }
//            }
//            this.data = newData;
//        }
//
//        return this.data[index];
    }

    public T get(int index) {
        if (index > this.size || index < 0) {
            return null;
        }
//        if (this.first == -1) {
//            int start = this.last - this.size + 1 >= 0
//                    ? this.last - this.size + 1 : this.last + 1 + this.data.length - this.size;
////            if (start + index <= this.data.length - 1) {
////                return this.data[start + index];
////            } else {
////                return this.data[index - (this.data.length - 1 - start) - 1];
////            }
//            return this.data[(start + index) % this.data.length];
//        }

        return this.data[(this.first + index) % this.data.length];

//        if (this.first + index <= this.data.length - 1) {
//            return this.data[this.first + index];
//        } else {
//            return this.data[index - (this.data.length - 1 - this.first) - 1];
//        }
    }
}
