package hometask12.cargo.repo;

import hometask14.cargo.domain.Cargo;
import hometask14.cargo.repo.CargoRepo;
import hometask14.common.solutions.utils.ArrayUtils;
import hometask14.storage.IdGenerator;
import hometask14.storage.Storage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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
    public Cargo getById(Long id) {
        for (Cargo cargo : Storage.cargos) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        Cargo foundCargo = this.getById(id);
        int cargoPosition;
        if (foundCargo != null) {
            cargoPosition = this.getPositionByCargo(foundCargo);
            Cargo[] newCargos = new Cargo[Storage.cargos.length];
            ArrayUtils.deleteObjectFromArray(Storage.cargos, cargoPosition, newCargos);
            Storage.cargos = newCargos;
            Storage.cargoIndex = (Storage.cargoIndex - 1);
            isDeleted = true;
        }
        return isDeleted;
    }

    @Override
    public Cargo[] getByName(String name) {
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
        return trimmedResult;
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
