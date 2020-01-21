package hometask15.carrier.repo;

import hometask15.carrier.domain.Carrier;
import hometask15.common.solutions.repo.BaseRepo;

public interface CarrierRepo extends BaseRepo<Carrier, Long> {
    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //boolean deleteById(Long id);
    
    //boolean update(Carrier carrier);

    Carrier[] getByName(String name);

    //List<Carrier> getAll();
}
