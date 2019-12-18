package hometask8.cargo.repo;

import hometask8.cargo.domain.Cargo;

public interface CargoRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    boolean deleteById(Long id);

    Cargo[] getByName(String name);
}
