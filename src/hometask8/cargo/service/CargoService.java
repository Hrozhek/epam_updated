package hometask8.cargo.service;

import hometask8.cargo.domain.Cargo;

public interface CargoService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    void deleteById(Long id);

    Cargo[] getByName(String name);
}
