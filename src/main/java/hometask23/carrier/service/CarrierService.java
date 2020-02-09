package hometask23.carrier.service;

import hometask23.carrier.domain.Carrier;
import hometask23.common.solutions.service.BaseService;
import hometask23.transportation.service.TransportationService;

import java.util.List;

public interface CarrierService extends BaseService<Carrier, Long> {
    void setTransportationService(TransportationService t);

    //void add(Carrier carrier);

    //Carrier getById(Long id);

    //void deleteById(Long id);

    List<Carrier> getByName(String name);

    //void printAll();
}