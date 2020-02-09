package hometask23.cargo.repo;

import hometask23.cargo.domain.Cargo;
import hometask23.storage.IdGenerator;
import hometask23.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Optional<Cargo> getById(Long id) {
        for (Cargo cargo: Storage.cargoList) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return Optional.of(cargo);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Cargo> foundCargo = this.getById(id);
        if (foundCargo.isPresent()) {
            return Storage.cargoList.remove(foundCargo.get());
        }
        return false;
    }

    @Override
    public List<Cargo> getByName(String name) {
        List<Cargo> cargoesWithGivenName = new ArrayList<>();


        for (Cargo cargo: Storage.cargoList) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                cargoesWithGivenName.add(cargo);
            }
        }
        return cargoesWithGivenName;
    }

    @Override
    public List<Cargo> getAll() {
        return Storage.cargoList;
    }
}
