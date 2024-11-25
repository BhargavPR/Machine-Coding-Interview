package factory;

import cache.Cache;
import cache.LRUCache;
import model.EvictionPolicy;

public class CacheFactory {

    public static <T> Cache<T> getCache(int capacity, EvictionPolicy evictionPolicy) {
        if (EvictionPolicy.LRU.equals(evictionPolicy)) {
            return new LRUCache<>(capacity);
        }
        return null;
    }
}
