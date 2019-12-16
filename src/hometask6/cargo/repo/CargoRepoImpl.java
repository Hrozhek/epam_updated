package hometask6.cargo.repo;

import hometask6.cargo.domain.Cargo;
import hometask6.common.utils.ArrayUtils;
import hometask6.storage.IdGenerator;
import hometask6.storage.Storage;

import java.util.Objects;


public class CargoRepoImpl implements CargoRepo {

    @Override
    public void addCargo(Cargo cargo) {
        cargo.setId(IdGenerator.generateId());
        Storage.getCargos()[Storage.getCargoIndex()] = cargo;
        Storage.setCargoIndex(Storage.getCargoIndex() + 1);

        if (Storage.getCargoIndex() == Storage.getCargos().length) {
            Cargo[] newCargos = new Cargo[Storage.getCargos().length * 2];
            ArrayUtils.copyArray(Storage.getCargos(), newCargos);
            Storage.setCargos(newCargos);
        }
    }

    @Override
    public Cargo getCargoById(Long id) {
        for (Cargo cargo : Storage.getCargos()) {
            if (cargo != null && Long.valueOf(id).equals(cargo.getId())) {
                return cargo;
            }
        }
        return null;
    }

    @Override
    public void deleteCargoById(Long id) {
        Cargo findedCargo = this.getCargoById(id);
        int cargoPosition;
        if (findedCargo != null) {
            cargoPosition = this.getPositionByCargo(findedCargo);
            Cargo[] newCargos = new Cargo[Storage.getCargos().length];
            ArrayUtils.deleteObjectFromArray(Storage.getCargos(), cargoPosition, newCargos);
            Storage.setCargos(newCargos);
            Storage.setCargoIndex(Storage.getCargoIndex() - 1);
        }
    }

    @Override
    public Cargo[] getCargosByName(String name) {
        Cargo[] result = new Cargo[Storage.getCargos().length];
        int cargosHaveSameName = 0;
        int curIndex = 0;
        for (Cargo cargo : Storage.getCargos()) {
            if (cargo != null && Objects.equals(cargo.getName(), name)) {
                result[curIndex++] = cargo;
                cargosHaveSameName++;
            }
        }
        Cargo [] trimmedResult = new Cargo[cargosHaveSameName];
        ArrayUtils.trimArray(result, trimmedResult);
        return trimmedResult;
    }

    private Integer getPositionByCargo(Cargo cargo) {
        Integer position = null;
        for (int i = 0; (position == null) && (i < Storage.getCargos().length); i++) {
            if (Storage.getCargos()[i] == cargo) {
                position = i;
            }
        }
        return position;
    }
}
