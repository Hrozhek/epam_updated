package hometask23.cargo.repo;

import hometask23.cargo.domain.Cargo;
import hometask23.common.solutions.utils.ArrayUtils;
import hometask23.storage.IdGenerator;
import hometask23.storage.Storage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class CargoArrayRepoImpl implements CargoRepo {

    @Override
    public void add(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        Storage.cargos[Storage.cargoIndex] = cargo;
        Storage.cargoIndex = (Storage.cargoIndex + 1);

        if (Storage.cargoIndex == Storage.cargos.length) {
            Cargo[] newCargos = new Cargo[Storage.cargos.length * 2];
            ArrayUtils.copyArray(Storage.cargos, newCargos);
            Storage.cargos = newCargos;
        }
    }

    @Override
    public boolean update(Cargo cargo) {
        return false;
    }

    @Override
    public Optional<Cargo> getById(Long id) {
        for (Cargo cargo : Storage.cargos) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return Optional.of(cargo);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        Optional<Cargo> foundCargo = this.getById(id);
        int cargoPosition;
        if (foundCargo.isPresent()) {
            cargoPosition = this.getPositionByCargo(foundCargo.get());
            Cargo[] newCargos = new Cargo[Storage.cargos.length];
            ArrayUtils.deleteObjectFromArray(Storage.cargos, cargoPosition, newCargos);
            Storage.cargos = newCargos;
            Storage.cargoIndex = (Storage.cargoIndex - 1);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public List<Cargo> getByName(String name) {
        Cargo[] result = new Cargo[Storage.cargos.length];
        int cargosHaveSameName = 0;
        int curIndex = 0;
        for (Cargo cargo : Storage.cargos) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                result[curIndex++] = cargo;
                cargosHaveSameName++;
            }
        }
        Cargo[] trimmedResult = new Cargo[cargosHaveSameName];
        ArrayUtils.trimArray(result, trimmedResult);
        return Arrays.asList(trimmedResult);
    }

    public List<Cargo> getAll(){
        return Arrays.asList(Storage.cargos);
    }

    private Integer getPositionByCargo(Cargo cargo) {
        Integer position = null;
        for (int i = 0; (position == null) && (i < Storage.cargos.length); i++) {
            if (Storage.cargos[i] == cargo) {
                position = i;
            }
        }
        return position;
    }
}
