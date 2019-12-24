package hometask10.common.solutions.service;

import hometask10.carrier.domain.Carrier;

import java.util.List;

public interface BaseService<T, N extends Number> {
    void add(T t);

    T getById(N id);

    void deleteById(N id);

    List<T> getAll();

    void printAll();
}
