package hometask9.cargo.repo;

import hometask9.cargo.domain.Cargo;

import java.util.List;

public interface CargoRepo {
    void add(Cargo cargo);

    boolean update(Cargo cargo);

    Cargo getById(Long id);

    boolean deleteById(Long id);

    Cargo[] getByName(String name);

    List<Cargo> getAll();
}
