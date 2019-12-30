package hometask12.application;

import hometask12.application.serviceholder.ServiceHolder;
import hometask12.application.serviceholder.StorageType;
import hometask12.cargo.domain.Cargo;
import hometask12.cargo.service.CargoService;
import hometask12.cargo.service.CargoSortCondition;
import hometask12.cargo.service.CargoSortFields;
import hometask12.carrier.domain.Carrier;
import hometask12.carrier.service.CarrierService;
import hometask12.common.business.files.SimpleFileSaver;
import hometask12.storage.initor.FromFileStorageInitor;
import hometask12.storage.initor.InMemoryStorageInitor;
import hometask12.storage.initor.StorageInitor;
import hometask12.transportation.domain.Transportation;
import hometask12.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static hometask12.cargo.service.CargoSortFields.SORT_BY_NAME;
import static hometask12.cargo.service.CargoSortFields.SORT_BY_WEIGHT;
import static java.util.Collections.singletonList;

public class Application {
    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        transportationService = ServiceHolder.getInstance().getTransportationService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();

        //StorageInitor storageInitor = new InMemoryStorageInitor();
        StorageInitor storageInitor = new FromFileStorageInitor();
        storageInitor.initStorage();

        printStorageData();
        demoSaveToFile();
        //demoSearchOperations();
        //demoSortOperations();
        //demoCarrierDeleter(carrierService, transportationService);
    }

    private static void demoSaveToFile() {
        System.out.println(SEPARATOR);
        System.out.println("Test of writing to file");
        SimpleFileSaver fileSaver = new SimpleFileSaver();
        fileSaver.setCargoService(cargoService);
        fileSaver.setCarrierService(carrierService);
        fileSaver.setTransportationService(transportationService);
        fileSaver.save();
    }

    private static void demoSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 8");
        System.out.println(carrierService.getById(8L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Jeans'");
        for (Cargo cargo : cargoService.getByName("Jeans")) {
            System.out.println(cargo);
        }
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
        for (Carrier carrier : (carrierService.getByName("Carrier_Name"))) {
            System.out.println(carrier);
        }
    }

    private static void printStorageData() {
        System.out.println("ALL CARGOES");
        cargoService.printAll();
        printSeparator();

        System.out.println("ALL CARRIERS");
        carrierService.printAll();
        printSeparator();

        System.out.println("ALL TRANSPOORTATIONS");
        transportationService.printAll();
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private static void demoSortOperations() {
        demoCargoSorting(singletonList(SORT_BY_NAME));
        demoCargoSorting(singletonList(SORT_BY_WEIGHT));
        demoCargoSorting(Arrays.asList(SORT_BY_NAME, SORT_BY_WEIGHT));

    }

    private static String getOrderingConditionsAsString(CargoSortCondition condition) {
        StringBuilder result = new StringBuilder();

        Iterator<CargoSortFields> iter = condition.getSortFields().iterator();
        while (iter.hasNext()) {
            CargoSortFields fld = iter.next();
            result.append(fld);

            if (iter.hasNext()) {
                result.append(",");
            }
        }


        return result.toString();
    }

    private static void demoCargoSorting(Collection<CargoSortFields> sortFields) {
        CargoSortCondition cargoSortCondition = new CargoSortCondition(new LinkedHashSet<>(sortFields));
        System.out.println(
                "---------Sorting '" + getOrderingConditionsAsString(cargoSortCondition) + "'------");
        cargoService.getSorted(cargoSortCondition);
        cargoService.printAll();
        System.out.println();
        System.out.println(
                "---------Sorting in reversed order '" + getOrderingConditionsAsString(cargoSortCondition) + "'------");
        cargoSortCondition.setOrderReversed();
        cargoService.getSorted(cargoSortCondition);
        cargoService.printAll();
        System.out.println();
        cargoSortCondition.setOrderReversed(false);
    }

    public static void demoCarrierDeleter(CarrierService carrierService, TransportationService transportationService) {
        Carrier carrierForDelete = new Carrier();
        carrierForDelete.setName("\"Carrier which can be deleted\"");
        carrierService.add(carrierForDelete);
        System.out.println("Carrier " + carrierForDelete.getName() + " deleting");
        carrierService.deleteById(carrierForDelete.getId());

        Carrier carrierWithoutId = new Carrier();
        carrierWithoutId.setName("\"Carrier without id\"");
        System.out.println("Try to delete carrier " + carrierWithoutId.getName());
        carrierService.deleteById(carrierWithoutId.getId());


        Carrier carrierUnableToDelete = new Carrier();
        carrierUnableToDelete.setName("\"Carrier cannot be deleted\"");
        carrierService.add(carrierUnableToDelete);
        Transportation transportation = new Transportation();
        transportation.setCarrier(carrierUnableToDelete);
        transportationService.add(transportation);
        try {
            carrierService.deleteById(carrierUnableToDelete.getId());
        } catch (RuntimeException e) {
            System.out.println("Carrier " + carrierUnableToDelete.getName() + " cannot be deleted");
        }

        Transportation tryToFallWithNPE = new Transportation();
        System.out.println("Try to delete transportation " + tryToFallWithNPE.getId());
        transportationService.deleteById(tryToFallWithNPE.getId());
    }
}
