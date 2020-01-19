package hometask11.cargo.service;

import hometask11.cargo.domain.Cargo;
import hometask11.common.solutions.service.BaseService;
import hometask11.transportation.service.TransportationService;

import java.util.List;

public interface CargoService extends BaseService<Cargo, Long> {
    void setTransportationService(TransportationService t);

    //void add(Cargo cargo);

    //Cargo getById(Long id);

    //void deleteById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> getSorted(CargoSortCondition condition);

    //void printAll();
}
