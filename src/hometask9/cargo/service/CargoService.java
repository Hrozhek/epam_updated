package hometask9.cargo.service;

import hometask9.cargo.domain.Cargo;

import java.util.Comparator;
import java.util.List;

public interface CargoService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    void deleteById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getSortedCargos(CargoSortCondition condition);

    void printAll();
}
