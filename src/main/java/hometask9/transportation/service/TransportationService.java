package hometask9.transportation.service;

import hometask9.cargo.domain.Cargo;
import hometask9.transportation.domain.Transportation;

import java.util.List;

public interface TransportationService {
    void add(Transportation transportation);

    Transportation getById(Long id);

    void deleteById(Long id);

    List<Transportation> getAll();

    void printAll();
}
