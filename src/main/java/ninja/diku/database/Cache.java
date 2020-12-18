package ninja.diku.database;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Cache<K, T> extends HashMap<K, T> {

    /**
     * Insert the item into a hashmap for "expires" amount of seconds
     * @param key
     * @param item
     * @param expires
     */
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