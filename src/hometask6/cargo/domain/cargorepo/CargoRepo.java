package hometask6.cargo.domain.cargorepo;

import hometask6.cargo.domain.Cargo;

public interface CargoRepo {
    void addCargo(Cargo cargo);

    Cargo getCargoById(Long id);

    void deleteCargoById(Long id);

    Cargo[] getCargosByName(String name);
}
