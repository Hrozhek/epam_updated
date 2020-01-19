package hometask7.transportation.service;

import hometask7.transportation.domain.Transportation;

public interface TransportationService {
    void add(Transportation transportation);

    Transportation getById(Long id);

    void deleteById(Long id);

}
