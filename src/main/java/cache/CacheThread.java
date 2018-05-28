package cache;

import java.util.concurrent.atomic.AtomicInteger;

public class CacheThread extends Thread {

    private CacheManager map;

    private String threadName;
    public static volatile AtomicInteger index=new AtomicInteger(0);

    public AtomicInteger getIndex() {
        return index;
    }

    public void setIndex(AtomicInteger index) {
        if (index != this.index) {
            this.index = index;
        }
    }

    public CacheThread(CacheManager map, String threadName) {
        this.map = map;
        this.threadName = threadName;
        index.addAndGet(1);
    }

    public CacheManager getMap() {
        return map;
    }

    public void setMap(CacheManager map) {
        this.map = map;
    }

    public void run() {
        while (index.get() < map.getMap().size()) {
            System.out.println("Поток: " + threadName + " индекс: " + index +
                    ". Страна: " + map.getKeyByIndex(index.get()) + " имеет столицу: " + map.getValueByIndex(index.get()).toString());
         //   synchronized (this) {
                index.addAndGet(1);
                if (index.get() > 20) {
                }

        }
    }
}
