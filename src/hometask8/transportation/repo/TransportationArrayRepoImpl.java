package hometask8.transportation.repo;

import hometask8.common.utils.ArrayUtils;
import hometask8.storage.IdGenerator;
import hometask8.storage.Storage;
import hometask8.transportation.domain.Transportation;

public class TransportationArrayRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        Storage.transportations[Storage.transportationIndex] = transportation;
        Storage.transportationIndex = Storage.transportationIndex + 1;

        if (Storage.transportationIndex == Storage.transportations.length) {
            Transportation[] newTransportations = new Transportation[Storage.transportations.length * 2];
            ArrayUtils.copyArray(Storage.transportations, newTransportations);
            Storage.transportations = newTransportations;
        }

    }

    @Override
    public Transportation getById(Long id) {
        for (Transportation transportation : Storage.transportations) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Transportation foundTransportation = this.getById(id);
        int transportationPosition;
        boolean isDeleted = false;
        if (foundTransportation != null) {
            transportationPosition = this.getPositionByTransportation(foundTransportation);
            Transportation[] newTransportations = new Transportation[Storage.transportations.length];
            ArrayUtils.deleteObjectFromArray(Storage.transportations, transportationPosition, newTransportations);
            Storage.transportations = newTransportations;
            Storage.transportationIndex= Storage.transportationIndex - 1;
            isDeleted = true;
        }
        return isDeleted;
    }
    
    public boolean update(Transportation transportation) {
        return false;
    }
    
    private Integer getPositionByTransportation(Transportation transportation) {
        Integer position = null;
        for (int i = 0; (position == null) && (i < Storage.transportations.length); i++) {
            if (Storage.transportations[i] == transportation) {
                position = i;
            }
        }
        return position;
    }

}
