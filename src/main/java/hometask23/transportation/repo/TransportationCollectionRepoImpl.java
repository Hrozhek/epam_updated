package hometask23.transportation.repo;

import hometask23.storage.IdGenerator;
import hometask23.storage.Storage;
import hometask23.transportation.domain.Transportation;

import java.util.List;
import java.util.Optional;

public class TransportationCollectionRepoImpl implements TransportationRepo {
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        Storage.transportationList.add(transportation);
    }

    @Override
    public Optional<Transportation> getById(Long id) {
        for (Transportation transportation: Storage.transportationList) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return Optional.of(transportation);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Transportation foundTransportation = this.getById(id).get();
        return Storage.transportationList.remove(foundTransportation);
    }
    
    public boolean update(Transportation transportation) {
        return false;
    }

    @Override
    public List<Transportation> getAll() {
        return Storage.transportationList;
    }

}
