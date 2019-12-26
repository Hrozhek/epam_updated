package hometask11.carrier.repo;

import hometask11.carrier.domain.Carrier;
import hometask11.common.solutions.utils.ArrayUtils;
import hometask11.storage.IdGenerator;
import hometask11.storage.Storage;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CarrierArrayRepoImpl implements CarrierRepo {
    @Override
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        Storage.carriers[Storage.carrierIndex] = carrier;
        Storage.carrierIndex = Storage.carrierIndex + 1;

        if (Storage.carrierIndex == Storage.carriers.length) {
            Carrier[] newCarriers = new Carrier[Storage.carriers.length * 2];
            ArrayUtils.copyArray(Storage.carriers, newCarriers);
            Storage.carriers = newCarriers;
        }
    }

    @Override
    public Carrier getById(Long id) {
        for (Carrier carrier : Storage.carriers) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDeleted = false;
        Carrier foundCarrier = this.getById(id);
        int carrierPosition;
        if (foundCarrier != null) {
            carrierPosition = this.getPositionByCarrier(foundCarrier);
            Carrier[] newCarriers = new Carrier[Storage.carriers.length];
            ArrayUtils.deleteObjectFromArray(Storage.carriers, carrierPosition, newCarriers);
            Storage.carriers = newCarriers;
            Storage.carrierIndex = Storage.carrierIndex - 1;
            isDeleted = true;
        }
        return isDeleted;
    }

    public boolean update(Carrier carrier) {
        return false;
    }

    @Override
    public Carrier[] getByName(String name) {
        Carrier[] result = new Carrier[Storage.carriers.length];
        int carriersHaveSameName = 0;
        int curIndex = 0;
        for (Carrier carrier : Storage.carriers) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                result[curIndex++] = carrier;
                carriersHaveSameName++;
            }
        }
        Carrier[] trimmedResult = new Carrier[carriersHaveSameName];
        ArrayUtils.trimArray(result, trimmedResult);
        return trimmedResult;
    }

    public List<Carrier> getAll() {
        return Arrays.asList(Storage.carriers);
    }

    private Integer getPositionByCarrier(Carrier carrier) {
        Integer position = null;
        for (int i = 0; (position == null) && (i < Storage.carriers.length); i++) {
            if (Storage.carriers[i] == carrier) {
                position = i;
            }
        }
        return position;
    }
}
