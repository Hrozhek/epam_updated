package hometask8.carrier.service;

import hometask8.carrier.domain.Carrier;

public interface CarrierService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    void deleteById(Long id);

    Carrier[] getByName(String name);
}