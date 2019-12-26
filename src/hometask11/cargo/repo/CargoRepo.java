package hometask11.cargo.repo;

import hometask11.cargo.domain.Cargo;
import hometask11.common.solutions.repo.BaseRepo;

public interface CargoRepo extends BaseRepo<Cargo, Long> {
    //void add(Cargo cargo);

    //boolean update(Cargo cargo);

    //Cargo getById(Long id);

    //boolean deleteById(Long id);

    Cargo[] getByName(String name);

    //List<Cargo> getAll();
}
