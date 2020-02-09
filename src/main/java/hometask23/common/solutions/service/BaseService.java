package hometask23.common.solutions.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    void add(T t);

    Optional<T> getById(ID id);

    void deleteById(ID id);

    List<T> getAll();

    void printAll();
}
