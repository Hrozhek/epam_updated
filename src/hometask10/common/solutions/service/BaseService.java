package hometask10.common.solutions.service;

import hometask10.carrier.domain.Carrier;

import java.util.List;

public interface BaseService<T, ID> {
    void add(T t);

    T getById(ID id);

    void deleteById(ID id);

    List<T> getAll();

    void printAll();
}
