package hometask6.cargo.service;

import hometask6.cargo.domain.Cargo;

public interface CargoService {
    void addCargo(Cargo cargo);

    Cargo getCargoById(Long id);

    void deleteCargoById(Long id);

    Cargo[] getCargosByName(String name);
}
