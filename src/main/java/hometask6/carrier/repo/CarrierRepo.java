package hometask6.carrier.repo;

import hometask6.carrier.domain.Carrier;

public interface CarrierRepo {
    void addCarrier(Carrier carrier);

    Carrier getCarrierById(Long id);

    void deleteCarrierById(Long id);

     Carrier[] getCarriersByName(String name);
}
