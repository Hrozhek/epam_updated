package hometask20.carrier.repo;

import hometask20.carrier.domain.Carrier;
import hometask20.common.solutions.repo.BaseRepo;

public interface CarrierRepo extends BaseRepo<Carrier, Long> {
    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //boolean deleteById(Long id);
    
    //boolean update(Carrier carrier);

    Carrier[] getByName(String name);

    //List<Carrier> getAll();
}
