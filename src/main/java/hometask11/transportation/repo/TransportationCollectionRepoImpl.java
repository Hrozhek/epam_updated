package hometask11.transportation.repo;

import hometask11.storage.IdGenerator;
import hometask11.storage.Storage;
import hometask11.transportation.domain.Transportation;

import java.util.List;

public class TransportationCollectionRepoImpl implements TransportationRepo {
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        Storage.transportationList.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        for (Transportation transportation: Storage.transportationList) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Transportation foundTransportation = this.getById(id);
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
