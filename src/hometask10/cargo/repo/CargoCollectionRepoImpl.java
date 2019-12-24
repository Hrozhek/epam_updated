package hometask10.cargo.repo;

import hometask10.cargo.domain.Cargo;
import hometask10.storage.IdGenerator;
import hometask10.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        return cargoesWithGivenName.toArray(new Cargo[cargoesWithGivenName.size()]);
    }

    @Override
    public List<Cargo> getAll() {
        return Storage.cargoList;
    }
}
