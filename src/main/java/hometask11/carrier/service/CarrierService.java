package hometask11.carrier.service;

import hometask11.carrier.domain.Carrier;
import hometask11.common.solutions.service.BaseService;
import hometask11.transportation.service.TransportationService;

import java.util.List;

public interface CarrierService extends BaseService<Carrier, Long> {
    void setTransportationService(TransportationService t);

    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //void deleteById(Long id);

    List<Carrier> getByName(String name);

    //void printAll();
}