package hometask7.cargo.service;

import hometask7.cargo.domain.Cargo;

public interface CargoService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    void deleteById(Long id);

    Cargo[] getByName(String name);
}
