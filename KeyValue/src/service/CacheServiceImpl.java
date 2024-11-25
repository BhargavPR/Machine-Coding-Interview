package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CacheServiceImpl<T> implements CacheService<T> {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, T>> dataMap;
    private final ConcurrentHashMap<String, String> indexMap;

    public CacheServiceImpl() {
        this.dataMap = new ConcurrentHashMap<>();
        this.indexMap = new ConcurrentHashMap<>();
    }

    @Override
    public void put(String key, String attributeKey, T value) {
        if (!dataMap.containsKey(key)) {
            dataMap.put(key, new ConcurrentHashMap<>());
        }
        ConcurrentHashMap<String, T> attributeMap = dataMap.get(key);
        attributeMap.put(attributeKey, value);
        indexMap.put(attributeKey, key);
    }

    @Override
    public ConcurrentHashMap<String, T> get(String key) {
        return dataMap.get(key);
    }

    @Override
    public List<String> getKeys() {
        return new ArrayList<>(dataMap.keySet());
    }

    @Override
    public Integer delete(String key) {
        if (!dataMap.containsKey(key)) {
            return 0;
        }

        ConcurrentHashMap<String, T> attributeMap = dataMap.get(key);
        Set<String> attributeKeys = attributeMap.keySet();
        for (String attributeKey : new ArrayList<>(attributeKeys)) {
            indexMap.remove(attributeKey);
        }
        dataMap.remove(key);
        return 1;
    }

    @Override
    public Optional<T> search(String attributeKey) {
        if (!indexMap.containsKey(attributeKey)) {
            return Optional.empty();
        }

        String key = indexMap.get(attributeKey);
        if (!dataMap.containsKey(key)) {
            return Optional.empty();
        }

        ConcurrentHashMap<String, T> attributeMap = dataMap.get(key);
        if (!attributeMap.containsKey(attributeKey)) {
            return Optional.empty();
        }

        return Optional.ofNullable(attributeMap.get(attributeKey));
    }
}
