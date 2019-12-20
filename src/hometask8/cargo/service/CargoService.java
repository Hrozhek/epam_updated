package hometask8.cargo.service;

import hometask8.cargo.domain.Cargo;

import java.util.Collections;

public interface CargoService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    void deleteById(Long id);

    Cargo[] getByName(String name);

    List<Cargo> getSortedByName();

    List<Cargo> getSortedByWeight();

    List<Cargo> getSortedByNameAndWeight();
}
