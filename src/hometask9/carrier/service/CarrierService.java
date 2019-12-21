package hometask9.carrier.service;

import hometask9.carrier.domain.Carrier;

import java.util.List;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    void deleteById(Long id);

    List<Carrier> getByName(String name);

    void printAll();
}