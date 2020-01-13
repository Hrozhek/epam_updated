package hometask12.cargo.service;

import hometask13.cargo.domain.Cargo;
import hometask13.cargo.service.CargoSortCondition;
import hometask13.common.solutions.service.BaseService;
import hometask13.transportation.service.TransportationService;

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
