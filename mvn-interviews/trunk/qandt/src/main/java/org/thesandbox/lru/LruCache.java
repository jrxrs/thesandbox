package org.thesandbox.lru;

import java.util.LinkedHashMap;

public class LruCache<K, V> extends LinkedHashMap<K, V> {

    public LruCache(int size) {
        super(size, 0.75f, true);
    }

}
