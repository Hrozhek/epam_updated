package hometask6.carrier.domain.carrierrepo;

import hometask6.carrier.domain.Carrier;

public interface CarrierRepo {
    void addCarrier(Carrier carrier);

    Carrier getCarrierById(Long id);

    void deleteCarrierById(Long id);

     Carrier[] getCarriersByName(String name);
}
