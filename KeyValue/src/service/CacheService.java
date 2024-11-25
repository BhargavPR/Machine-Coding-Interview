package service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public interface CacheService<T> {

    void put(String key, String attributeKey, T value);

    ConcurrentHashMap<String, T> get(String key);

    List<String> getKeys();

    Integer delete(String key);

    Optional<T> search(String attributeKey);

}
