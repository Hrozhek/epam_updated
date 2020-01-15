package hometask14.carrier.repo;

import hometask13.carrier.domain.Carrier;
import hometask13.common.solutions.repo.BaseRepo;

public interface CarrierRepo extends BaseRepo<Carrier, Long> {
    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //boolean deleteById(Long id);
    
    //boolean update(Carrier carrier);

    Carrier[] getByName(String name);

    //List<Carrier> getAll();
}
