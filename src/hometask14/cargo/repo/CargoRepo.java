package hometask14.cargo.repo;

import hometask13.cargo.domain.Cargo;
import hometask13.common.solutions.repo.BaseRepo;

public interface CargoRepo extends BaseRepo<Cargo, Long> {
    //void add(Cargo cargo);

    //boolean update(Cargo cargo);

    //Cargo getById(Long id);

    //boolean deleteById(Long id);

    Cargo[] getByName(String name);

    //List<Cargo> getAll();
}
