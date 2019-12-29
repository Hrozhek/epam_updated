package hometask12.carrier.service;

import hometask12.carrier.domain.Carrier;
import hometask12.common.solutions.service.BaseService;
import hometask12.transportation.service.TransportationService;

import java.util.List;

public interface CarrierService extends BaseService<Carrier, Long> {
    void setTransportationService(TransportationService t);

    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //void deleteById(Long id);

    List<Carrier> getByName(String name);

    //void printAll();
}