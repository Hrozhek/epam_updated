package hometask14.application;

import hometask14.application.serviceholder.ServiceHolder;
import hometask14.application.serviceholder.StorageType;
import hometask14.cargo.service.CargoService;
import hometask14.carrier.service.CarrierService;
import hometask14.transportation.service.TransportationService;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @org.junit.jupiter.api.Test
    void deserializeCargos() {
        CargoService cargoService = ServiceHolder.getInstance().getCargoService();
        Application.deserializeCargos();

    }

    @org.junit.jupiter.api.Test
    void deserializeCarriers() {
        CarrierService carrierService
    }

    @org.junit.jupiter.api.Test
    void deserializeTrasnportations() {
        TransportationService transportationService = ServiceHolder.getInstance().getTransportationService();

    }
}