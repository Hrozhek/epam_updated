package hometask6.transportation.domain.transportationservice;

import hometask6.transportation.domain.Transportation;

public interface TransportationService {
    void addTransportation(Transportation transportation);

    Transportation getTransportationById(Long id);

    void deleteTransportationById(Long id);

}
