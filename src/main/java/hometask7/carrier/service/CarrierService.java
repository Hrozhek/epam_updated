package hometask7.carrier.service;

import hometask7.carrier.domain.Carrier;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    void deleteById(Long id);

    Carrier[] getByName(String name);
}