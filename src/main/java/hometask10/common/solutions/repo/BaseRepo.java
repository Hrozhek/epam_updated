package hometask10.common.solutions.repo;

import hometask10.cargo.domain.Cargo;

import java.util.List;

public interface BaseRepo<T, ID> {
    void add(T something);

    boolean update(T something);

    T getById(ID id);

    boolean deleteById(ID id);

    List<T> getAll();
}
