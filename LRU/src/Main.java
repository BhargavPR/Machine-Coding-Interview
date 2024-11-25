import cache.Cache;
import factory.CacheFactory;
import model.EvictionPolicy;

public class Main {
    public static void main(String[] args) {
        Cache<String> cache = CacheFactory.getCache(3, EvictionPolicy.LRU);
        if (cache != null) {
            cache.put("a", "a");
            cache.put("b", "b");
            cache.put("a", "c");
            cache.put("c", "c1");
            cache.put("d", "d1");
            cache.get("a");
            cache.get("b");
            cache.print();
        }
    }
}