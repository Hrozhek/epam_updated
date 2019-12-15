package hometask6.carrier.domain.carrierservice;

import hometask6.carrier.domain.Carrier;

public interface CarrierService {
    void addCarrier(Carrier carrier);

    Carrier getCarrierById(Long id);

    void deleteCarrierById(Long id);

    Carrier[] getCarriersByName(String name);
}