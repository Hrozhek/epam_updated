package hometask23.carrier.repo;

import hometask23.carrier.domain.Carrier;
import hometask23.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CarrierRepo extends BaseRepo<Carrier, Long> {
    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //boolean deleteById(Long id);
    
    //boolean update(Carrier carrier);

    List<Carrier> getByName(String name);

    //List<Carrier> getAll();
}
