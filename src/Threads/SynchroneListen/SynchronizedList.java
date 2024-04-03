package Threads.SynchroneListen;

import java.util.LinkedList;
import java.util.List;

public class SynchronizedList<T> {
    private List<T> innerList = new LinkedList<>();
    private RW lock= new RW();

    public void add(int index, T e) throws InterruptedException {
        lock.startWrite();
        innerList.add(index, e);
        lock.endWrite();
    }

    public synchronized T remove(int index) throws InterruptedException {
        lock.startWrite();
        T result = innerList.remove(index);
        lock.endWrite();
        return result;
    }

    public synchronized T get(int index) throws InterruptedException {
        lock.startRead();
        T got = innerList.get(index);
        lock.endRead();
        return got;
    }

    public synchronized boolean contains(T e) throws InterruptedException {
        lock.startRead();
        boolean is = innerList.contains(e);
        lock.endRead();
        return is;
    }
}