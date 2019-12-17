package hometask7.transportation.repo;

import hometask7.storage.IdGenerator;
import hometask7.storage.Storage;
import hometask7.transportation.domain.Transportation;

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

}
