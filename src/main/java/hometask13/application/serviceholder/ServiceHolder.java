package hometask13.application.serviceholder;

import hometask14.application.serviceholder.StorageType;
import hometask14.cargo.repo.CargoArrayRepoImpl;
import hometask14.cargo.repo.CargoCollectionRepoImpl;
import hometask14.cargo.service.CargoService;
import hometask14.cargo.service.CargoServiceImpl;
import hometask14.carrier.repo.CarrierArrayRepoImpl;
import hometask14.carrier.repo.CarrierCollectionRepoImpl;
import hometask14.carrier.service.CarrierService;
import hometask14.carrier.service.CarrierServiceImpl;
import hometask14.transportation.repo.TransportationArrayRepoImpl;
import hometask14.transportation.repo.TransportationCollectionRepoImpl;
import hometask14.transportation.service.TransportationService;
import hometask14.transportation.service.TransportationServiceImpl;

public final class ServiceHolder {
    private static ServiceHolder instance = null;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    private ServiceHolder(hometask14.application.serviceholder.StorageType storageType) {
        SimpleServiceHolder initedServiceHolder = getInitedServiceHolder(storageType);
        transportationService = initedServiceHolder.transportationService;
        cargoService = initedServiceHolder.cargoService;
        carrierService = initedServiceHolder.carrierService;
    }

    public static void initServiceHolder(hometask14.application.serviceholder.StorageType storageType) {
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
