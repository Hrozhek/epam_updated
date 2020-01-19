package hometask14.common.solutions.service;

import java.util.List;

public interface BaseService<T, ID> {
    void add(T t);

    T getById(ID id);

    void deleteById(ID id);

    List<T> getAll();

    void printAll();
}
