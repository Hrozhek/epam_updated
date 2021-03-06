package hometask7.cargo.repo;

import hometask7.cargo.domain.Cargo;
import hometask7.common.utils.ArrayUtils;
import hometask7.storage.IdGenerator;
import hometask7.storage.Storage;

import java.util.Objects;

public class CargoCollectionRepoImpl implements CargoRepo {
    @Override
    public void add(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        Storage.cargoList.add(cargo);
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
        Cargo[] nonTrimmedArray = new Cargo[Storage.cargoList.size()];
        int quantity = 0;
        for (Cargo cargo: Storage.cargoList) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                nonTrimmedArray[quantity++] = cargo;
            }
        }
        Cargo [] trimmedArray = new Cargo[quantity];
        ArrayUtils.trimArray(nonTrimmedArray, trimmedArray);
        return trimmedArray;
    }
}
