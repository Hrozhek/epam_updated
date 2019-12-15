package hometask6.transportation.domain.transportationrepo;

import hometask6.common.utils.ArrayUtils;
import hometask6.storage.IdGenerator;
import hometask6.storage.Storage;
import hometask6.transportation.domain.Transportation;

public class TransportationRepoImpl implements TransportationRepo {
    @Override
    public void addTransportation(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        Storage.getTransportations()[Storage.getTransportationIndex()] = transportation;
        Storage.setTransportationIndex(Storage.getTransportationIndex() + 1);

        if (Storage.getTransportationIndex() == Storage.getTransportations().length) {
            Transportation[] newTransportations = new Transportation[Storage.getTransportations().length * 2];
            ArrayUtils.copyArray(Storage.getTransportations(), newTransportations);
            Storage.setTransportations(newTransportations);
        }

    }

    @Override
    public Transportation getTransportationById(Long id) {
        for (Transportation transportation : Storage.getTransportations()) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }
        return null;
    }

    @Override
    public void deleteTransportationById(Long id) {
        Transportation findedTransportation = this.getTransportationById(id);
        int transportationPosition;
        if (findedTransportation != null) {
            transportationPosition = this.getPositionByTransportation(findedTransportation);
            Transportation[] newTransportations = new Transportation[Storage.getTransportations().length];
            ArrayUtils.deleteObjectFromArray(Storage.getTransportations(), transportationPosition, newTransportations);
            Storage.setTransportations(newTransportations);
            Storage.setTransportationIndex(Storage.getTransportationIndex() - 1);
        }
    }

    private Integer getPositionByTransportation(Transportation transportation) {
        Integer position = null;
        for (int i = 0; (position == null) && (i < Storage.getTransportations().length); i++) {
            if (Storage.getTransportations()[i] == transportation) {
                position = i;
            }
        }
        return position;
    }

}
