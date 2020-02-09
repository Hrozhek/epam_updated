package hometask23.carrier.repo;

import hometask23.carrier.domain.Carrier;
import hometask23.storage.IdGenerator;
import hometask23.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarrierCollectionRepoImpl implements CarrierRepo {
    public void add(Carrier carrier) {
        carrier.setId(IdGenerator.generateId());
        Storage.carrierList.add(carrier);
    }

    @Override
    public Optional<Carrier> getById(Long id) {
        for (Carrier carrier : Storage.carrierList) {
            if (carrier != null && Long.valueOf(id).equals(carrier.getId())) {
                return Optional.of(carrier);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Carrier> foundCarrier = this.getById(id);
        if (foundCarrier.isPresent()) {
            return Storage.carrierList.remove(foundCarrier.get());
        } return false;
    }

    public boolean update(Carrier carrier) {
        return false;
    }

    @Override
    public List<Carrier> getByName(String name) {
        List<Carrier> carrieresWithGivenName = new ArrayList<>();

        for (Carrier carrier : Storage.carrierList) {
            if (carrier != null && Objects.equals(carrier.getName(), name)) {
                carrieresWithGivenName.add(carrier);
            }
        }
        return carrieresWithGivenName;
    }

    public List<Carrier> getAll() {
        return Storage.carrierList;
    }

}
