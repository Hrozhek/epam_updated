package hometask18.carrier.service;

import hometask18.carrier.domain.Carrier;
import hometask18.common.solutions.service.BaseService;
import hometask18.transportation.service.TransportationService;

import java.util.List;

public interface CarrierService extends BaseService<Carrier, Long> {
    void setTransportationService(TransportationService t);

    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //void deleteById(Long id);

    List<Carrier> getByName(String name);

    //void printAll();
}