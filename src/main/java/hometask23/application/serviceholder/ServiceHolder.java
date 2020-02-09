package hometask23.application.serviceholder;

import hometask23.cargo.repo.CargoArrayRepoImpl;
import hometask23.cargo.repo.CargoCollectionRepoImpl;
import hometask23.cargo.repo.jdbc.CargoDatabaseRepoImpl;
import hometask23.cargo.service.CargoService;
import hometask23.cargo.service.CargoServiceImpl;
import hometask23.carrier.repo.CarrierArrayRepoImpl;
import hometask23.carrier.repo.CarrierCollectionRepoImpl;
import hometask23.carrier.repo.jdbc.CarrierDatabaseRepoImpl;
import hometask23.carrier.service.CarrierService;
import hometask23.carrier.service.CarrierServiceImpl;
import hometask23.transportation.repo.TransportationArrayRepoImpl;
import hometask23.transportation.repo.TransportationCollectionRepoImpl;
import hometask23.transportation.repo.jdbc.TransportationDatabaseRepoImpl;
import hometask23.transportation.service.TransportationService;
import hometask23.transportation.service.TransportationServiceImpl;

public final class ServiceHolder {
    private static ServiceHolder instance = null;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    private ServiceHolder(StorageType storageType) {
        SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
        transportationService = initedServiceHolder.transportationService;
        cargoService = initedServiceHolder.cargoService;
        carrierService = initedServiceHolder.carrierService;
    }

    public static void initServiceHolder(StorageType storageType) {
        ServiceHolder.instance = new ServiceHolder(storageType);
    }

    public static ServiceHolder getInstance() {
        return instance;
    }

    private static class SimpleServiceHolder {

        private final TransportationService transportationService;
        private final CarrierService carrierService;
        private final CargoService cargoService;


        public SimpleServiceHolder(
                TransportationService transportationService,
                CarrierService carrierService,
                CargoService cargoService) {
            this.transportationService = transportationService;
            this.carrierService = carrierService;
            this.carrierService.setTransportationService(transportationService);
            this.cargoService = cargoService;
            this.cargoService.setTransportationService(transportationService);

        }
    }

    private SimpleServiceHolder getInitedServiceHolder(StorageType storageType) {
        switch (storageType) {

            case ARRAY: {
                return new SimpleServiceHolder(
                        new TransportationServiceImpl(new TransportationArrayRepoImpl()),
                        new CarrierServiceImpl(new CarrierArrayRepoImpl()),
                        new CargoServiceImpl(new CargoArrayRepoImpl()));
            }
            case DATABASE: {
                return new SimpleServiceHolder(
                        new TransportationServiceImpl(new TransportationDatabaseRepoImpl()),
                        new CarrierServiceImpl(new CarrierDatabaseRepoImpl()),
                        new CargoServiceImpl(new CargoDatabaseRepoImpl()));
            }
            default: {
                return new SimpleServiceHolder(
                        new TransportationServiceImpl(new TransportationCollectionRepoImpl()),
                        new CarrierServiceImpl(new CarrierCollectionRepoImpl()),
                        new CargoServiceImpl(new CargoCollectionRepoImpl()));
            }
        }
    }

    public CarrierService getCarrierService() {
        return carrierService;
    }

    public CargoService getCargoService() {
        return cargoService;
    }

    public TransportationService getTransportationService() {
        return transportationService;
    }
}
