package hometask7.transportation.repo;

import hometask7.transportation.domain.Transportation;

public interface TransportationRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    boolean deleteById(Long id);

}
