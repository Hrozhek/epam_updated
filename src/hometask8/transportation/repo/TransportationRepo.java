package hometask8.transportation.repo;

import hometask8.transportation.domain.Transportation;

public interface TransportationRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    boolean deleteById(Long id);

}
