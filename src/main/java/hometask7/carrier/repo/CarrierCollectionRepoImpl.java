package hometask7.carrier.repo;

import hometask7.carrier.domain.Carrier;
import hometask7.common.utils.ArrayUtils;
import hometask7.storage.IdGenerator;
import hometask7.storage.Storage;

import java.util.Objects;

public class CarrierCollectionRepoImpl implements CarrierRepo {
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        Storage.carrierList.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        for (Carrier carrier: Storage.carrierList) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return carrier;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Carrier foundCarrier = this.getById(id);
        return Storage.carrierList.remove(foundCarrier);
    }

    @Override
    public Carrier[] getByName(String name) {
        Carrier[] nonTrimmedArray = new Carrier[Storage.carrierList.size()];
        int quantity = 0;
        for (Carrier carrier: Storage.carrierList) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                nonTrimmedArray[quantity++] = carrier;
            }
        }
        Carrier [] trimmedArray = new Carrier[quantity];
        ArrayUtils.trimArray(nonTrimmedArray, trimmedArray);
        return trimmedArray;
    }

}
