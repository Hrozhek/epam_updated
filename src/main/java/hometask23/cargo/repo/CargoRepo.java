package hometask23.cargo.repo;

import hometask23.cargo.domain.Cargo;
import hometask23.common.solutions.repo.BaseRepo;

import java.util.List;

public interface CargoRepo extends BaseRepo<Cargo, Long> {
    //void add(Cargo cargo);

    //boolean update(Cargo cargo);

    //Cargo getById(Long id);

    //boolean deleteById(Long id);

    List<Cargo> getByName(String name);

    //List<Cargo> getAll();
}
