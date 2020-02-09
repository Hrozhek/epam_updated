package hometask23.common.solutions.repo;

import java.util.List;
import java.util.Optional;

public interface BaseRepo<T, ID> {
    void add(T something);

    boolean update(T something);

    Optional<T> getById(ID id);

    boolean deleteById(ID id);

    List<T> getAll();
}
