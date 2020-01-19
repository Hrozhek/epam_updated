package hometask13.transportation.repo;

import hometask14.storage.IdGenerator;
import hometask14.storage.Storage;
import hometask14.transportation.domain.Transportation;
import hometask14.transportation.repo.TransportationRepo;

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
