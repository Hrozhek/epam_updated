package hometask7.cargo.repo;

import hometask7.cargo.domain.Cargo;

public interface CargoRepo {
    void add(Cargo cargo);

    Cargo getById(Long id);

    boolean deleteById(Long id);

    Cargo[] getByName(String name);
}
