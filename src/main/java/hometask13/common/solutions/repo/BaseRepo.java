package hometask13.common.solutions.repo;

import java.util.List;

public interface BaseRepo<T, ID> {
    void add(T something);

    boolean update(T something);

    T getById(ID id);

    boolean deleteById(ID id);

    List<T> getAll();
}
