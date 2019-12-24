package hometask10.cargo.repo;

import hometask10.cargo.domain.Cargo;
import hometask10.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CargoRepo extends BaseRepo<Cargo, Long> {
    //void add(Cargo cargo);

    //boolean update(Cargo cargo);

    //Cargo getById(Long id);

    //boolean deleteById(Long id);

    Cargo[] getByName(String name);

    //List<Cargo> getAll();
}
