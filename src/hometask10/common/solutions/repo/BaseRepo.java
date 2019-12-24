package hometask10.common.solutions.repo;

import hometask10.cargo.domain.Cargo;

import java.util.List;

public interface BaseRepo<T, N extends Number> {
    void add(T something);

    boolean update(T something);

    T getById(N id);

    boolean deleteById(N id);

    List<T> getAll();
}
