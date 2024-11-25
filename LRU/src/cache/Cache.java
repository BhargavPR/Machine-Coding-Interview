package cache;

import java.util.Optional;

public interface Cache<T> {

    void put(String key, T value);

    Optional<T> get(String key);

    Integer delete(String key);

    void print();

}
