package com.aduan.study.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少使用淘汰策略的缓存类
 *
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int MAX_CACHE_SIZE;

    public LRUCache(int cacheSize) {

        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        this.MAX_CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 超过阈值时返回true，进行LRU淘汰
        return super.size() > MAX_CACHE_SIZE;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<>(5);
        lruCache.put("1", 1);
        lruCache.put("2", 2);
        lruCache.put("3", 3);
        lruCache.put("4", 4);
        lruCache.put("5", 5);
        lruCache.get("1");
        lruCache.put("6", 6);

        System.out.println(lruCache.keySet());
    }
}
