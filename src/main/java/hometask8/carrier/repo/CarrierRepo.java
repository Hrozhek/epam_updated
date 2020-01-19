package hometask8.carrier.repo;

import hometask8.carrier.domain.Carrier;

public interface CarrierRepo {
    void add(Carrier carrier);

    Carrier getById(Long id);

    boolean deleteById(Long id);
    
    boolean update(Carrier carrier);

    Carrier[] getByName(String name);
}
