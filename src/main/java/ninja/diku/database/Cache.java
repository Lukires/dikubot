package ninja.diku.database;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Cache<K, T> extends HashMap<K, T> {

    public void put(final K key, T item, long expires) {
        super.put(key, item);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, expires*1000);
    }
}