package hometask8.cargo.repo;

import hometask8.cargo.domain.Cargo;
import hometask8.storage.IdGenerator;
import hometask8.storage.Storage;

import java.util.*;

public class CargoCollectionRepoImpl implements CargoRepo {
    @Override
    public void add(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        Storage.cargoList.add(cargo);
    }

    @Override
    public boolean update(Cargo cargo) {
        return false;
    }

    @Override
    public Cargo getById(Long id) {
        for (Cargo cargo: Storage.cargoList) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Cargo foundCargo = this.getById(id);
        return Storage.cargoList.remove(foundCargo);
    }

    @Override
    public Cargo[] getByName(String name) {
        List<Cargo> cargoesWithGivenName = new ArrayList<>();


        for (Cargo cargo: Storage.cargoList) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                cargoesWithGivenName.add(cargo);
            }
        }
        return (Cargo[]) cargoesWithGivenName.toArray();
    }

    @Override
    public List<Cargo> getAll() {
        return Storage.cargoList;
    }
}
