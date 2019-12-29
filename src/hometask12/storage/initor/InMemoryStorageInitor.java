package hometask12.storage.initor;

import hometask12.application.serviceholder.ServiceHolder;
import hometask12.cargo.domain.ClothesCargo;
import hometask12.cargo.domain.FoodCargo;
import hometask12.cargo.service.CargoService;
import hometask12.carrier.domain.Carrier;
import hometask12.carrier.service.CarrierService;
import hometask12.transportation.domain.Transportation;
import hometask12.transportation.service.TransportationService;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class InMemoryStorageInitor implements StorageInitor {

    private static final int TOTAL_ENTITIES_IN_GROUP = 6;

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    public InMemoryStorageInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    @Override
    public void initStorage() {
        initCargos();
        initCarriers();
        initTransportations();
    }

    private void initCargos() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.add(createClothesCargo(i));
        }
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP / 2; i++) {
            cargoService.add(createFoodCargo(i));
        }
    }

    private ClothesCargo createClothesCargo(int index) {
        ClothesCargo cargo = new ClothesCargo();
        cargo.setSize("Clothes_Size_" + index);
        cargo.setName("Jeans");
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));

        return cargo;
    }

    private FoodCargo createFoodCargo(int index) {
        FoodCargo cargo = new FoodCargo();
        cargo.setExpirationDate(new Date());
        cargo.setStoreTemperature(index);
        cargo.setWeight(ThreadLocalRandom.current().nextInt(1, 100 + 1));
        cargo.setName("Milk");

        return cargo;
    }

    private void initCarriers() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Carrier carrier = createCarrier(i);
            carrierService.add(carrier);
        }
    }

    private Carrier createCarrier(int index) {
        Carrier carrier = new Carrier();
        carrier.setName("Carrier_Name");
        carrier.setAddress("Address_" + index);
        return carrier;
    }

    private void initTransportations() {
        for (int i = 0; i < TOTAL_ENTITIES_IN_GROUP; i++) {
            Transportation transportation = createTransportation(i + 1, i + 1 + TOTAL_ENTITIES_IN_GROUP);
            transportationService.add(transportation);
        }
    }

    private Transportation createTransportation(long cargoId, long carrierId) {
        Transportation transportation = new Transportation();
        transportation.setCargo(cargoService.getById(cargoId));
        transportation.setCarrier(carrierService.getById(carrierId));
        transportation.setDescription("Transportation");

        return transportation;
    }

}
