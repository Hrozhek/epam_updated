package hometask9.carrier.repo;

import hometask9.carrier.domain.Carrier;

import java.util.List;

public interface CarrierRepo {
    void add(Carrier carrier);

    Carrier getById(Long id);

    boolean deleteById(Long id);
    
    boolean update(Carrier carrier);

    Carrier[] getByName(String name);

    List<Carrier> getAll();
}
