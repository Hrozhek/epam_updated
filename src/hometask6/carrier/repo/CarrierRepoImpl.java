package hometask6.carrier.repo;

import hometask6.carrier.domain.Carrier;
import hometask6.common.utils.ArrayUtils;
import hometask6.storage.IdGenerator;
import hometask6.storage.Storage;

import java.util.Objects;

public class CarrierRepoImpl implements CarrierRepo {
    @Override
    public void addCarrier(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        Storage.getCarriers()[Storage.getCarrierIndex()] = carrier;
        Storage.setCarrierIndex(Storage.getCarrierIndex() + 1);

        if (Storage.getCarrierIndex() == Storage.getCarriers().length) {
            Carrier[] newCarriers = new Carrier[Storage.getCarriers().length * 2];
            ArrayUtils.copyArray(Storage.getCarriers(), newCarriers);
            Storage.setCarriers(newCarriers);
        }
    }

    @Override
    public Carrier getCarrierById(Long id) {
        for (Carrier carrier : Storage.getCarriers()) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }
        return null;
    }

    @Override
    public void deleteCarrierById(Long id) {
        Carrier findedCarrier = this.getCarrierById(id);
        int carrierPosition;
        if (findedCarrier != null) {
            carrierPosition = this.getPositionByCarrier(findedCarrier);
            Carrier[] newCarriers = new Carrier[Storage.getCarriers().length];
            ArrayUtils.deleteObjectFromArray(Storage.getCarriers(), carrierPosition, newCarriers);
            Storage.setCarriers(newCarriers);
            Storage.setCarrierIndex(Storage.getCarrierIndex() - 1);
        }
    }

    @Override
    public Carrier[] getCarriersByName(String name) {
        Carrier[] result = new Carrier[Storage.getCarriers().length];
        int carriersHaveSameName = 0;
        int curIndex = 0;
        for (Carrier carrier : Storage.getCarriers()) {
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
        for (int i = 0; (position == null) && (i < Storage.getCarriers().length); i++) {
            if (Storage.getCarriers()[i] == carrier) {
                position = i;
            }
        }
        return position;
    }
}
