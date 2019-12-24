package hometask10.carrier.repo;

import hometask10.carrier.domain.Carrier;
import hometask10.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CarrierRepo extends BaseRepo<Carrier, Long> {
    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //boolean deleteById(Long id);
    
    //boolean update(Carrier carrier);

    Carrier[] getByName(String name);

    //List<Carrier> getAll();
}
