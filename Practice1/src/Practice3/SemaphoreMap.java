package Practice3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class SemaphoreMap<K, V> {
    private Semaphore sem;

    private Map<K, V> map = new HashMap<>();

    public SemaphoreMap(Map<K, V> map) {
        this.sem = new Semaphore(1);
        this.map = map;
    }

    public void clear() throws InterruptedException {
        sem.acquire();
        map.clear();
        sem.release();
    }

    public V get(Object k) throws InterruptedException {
        sem.acquire();
        V result = map.get(k);
        sem.release();
        return result;
    }

    public V put(K k, V v) throws InterruptedException {
        sem.acquire();
        V previousValue = map.get(k);
        map.put(k, v);
        sem.release();
        return previousValue;
    }

    public V remove(Object k) throws InterruptedException {
        sem.acquire();
        V deletedValue = map.remove(k);
        sem.release();
        return deletedValue;
    }

}
