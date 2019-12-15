package hometask6.transportation.domain.transportationrepo;

import hometask6.transportation.domain.Transportation;

public interface TransportationRepo {
    void addTransportation(Transportation transportation);

    Transportation getTransportationById(Long id);

    void deleteTransportationById(Long id);

}
