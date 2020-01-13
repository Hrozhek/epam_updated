package hometask13.carrier.repo;

import hometask13.carrier.domain.Carrier;
import hometask13.storage.IdGenerator;
import hometask13.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarrierCollectionRepoImpl implements CarrierRepo {
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        Storage.carrierList.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        for (Carrier carrier : Storage.carrierList) {
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

    public boolean update(Carrier carrier) {
        return false;
    }

    @Override
    public Carrier[] getByName(String name) {
        List<Carrier> carrieresWithGivenName = new ArrayList<>();

        for (Carrier carrier : Storage.carrierList) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                carrieresWithGivenName.add(carrier);
            }
        }
        return carrieresWithGivenName.toArray(new Carrier[carrieresWithGivenName.size()]);
    }

    public List<Carrier> getAll() {
        return Storage.carrierList;
    }

}
