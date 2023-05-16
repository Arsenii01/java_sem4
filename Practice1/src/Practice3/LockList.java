package Practice3;

import javax.swing.plaf.basic.BasicTreeUI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockList<E> {
    private static final Lock lock = new ReentrantLock();
    private List<E> array = new ArrayList<>();

    public void add(E e) {
        lock.lock();
        array.add(e);
        lock.unlock();
    }

    public void clear() {
        lock.lock();
        array.clear();
        lock.unlock();
    }
    public boolean contains(Object o) {
        lock.lock();
        boolean ifContains = array.contains(o);
        lock.unlock();
        return ifContains;
    }

    public E get(int index) {
        lock.lock();
        E element = array.get(index);
        lock.unlock();
        return element;
    }

    public E remove(int index) {
        lock.lock();
        E element = array.remove(index);
        lock.unlock();
        return element;
    }

    public boolean remove(Object o) {
        lock.lock();
        boolean element = array.remove(o);
        lock.unlock();
        return element;
    }

    @Override
    public String toString() {
        return "LockList{" +
                "array=" + array +
                '}';
    }
}
