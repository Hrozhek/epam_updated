package hometask8.transportation.service;

import hometask8.transportation.domain.Transportation;

public interface TransportationService {
    void add(Transportation transportation);

    Transportation getById(Long id);

    void deleteById(Long id);

}
