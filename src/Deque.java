public class Deque<E> {
    private E[] data;
    private int front;
    private int tail;

    public Deque(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
    }
    public Deque() {
        this(10);
    }

    public boolean isEmpty() {
        return tail + 1 == front;
    }

    public int getSize() {
        return front < tail ? tail - front : tail - front + data.length;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    public E getFront() {
        return data[front];
    }

    public E getTail() {
        return data[tail];
    }

    public void addFront(E e) {
        if (getSize() == getCapacity()) {
            resize(getCapacity() * 2);
        }


    }
    public void addLast(E e) {
        if (getSize() == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    public E removeFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot remove front from a empty queue.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot remove last from a empty queue.");
        }
        E ret = data[tail - 1];
        data[tail - 1] = null;
        tail = (tail - 1) % data.length;
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < getSize(); i++) {
            newData[i] = data[(i + front) % data.length];
        }
        tail = getSize();
        front = 0;
        data = newData;
    }



}
