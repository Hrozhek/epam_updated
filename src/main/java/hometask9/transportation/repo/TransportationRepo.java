package hometask9.transportation.repo;

import hometask9.transportation.domain.Transportation;

import java.util.List;

public interface TransportationRepo {
    void add(Transportation transportation);

    Transportation getById(Long id);

    boolean deleteById(Long id);
    
    boolean update(Transportation transportation);

    List<Transportation> getAll();
    
}
