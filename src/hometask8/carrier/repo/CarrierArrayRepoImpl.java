package hometask8.carrier.repo;

import hometask8.carrier.domain.Carrier;
import hometask8.common.utils.ArrayUtils;
import hometask8.storage.IdGenerator;
import hometask8.storage.Storage;

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
