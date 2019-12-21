package hometask9.application;

import hometask9.application.serviceholder.ServiceHolder;
import hometask9.application.serviceholder.StorageType;
import hometask9.cargo.domain.Cargo;
import hometask9.cargo.service.CargoService;
import hometask9.cargo.service.CargoServiceImpl;
import hometask9.cargo.service.CargoSortCondition;
import hometask9.carrier.domain.Carrier;
import hometask9.carrier.service.CarrierService;
import hometask9.storage.initor.InMemoryStorageInitor;
import hometask9.storage.initor.StorageInitor;
import hometask9.transportation.service.TransportationService;
import hometask9.cargo.service.CargoSortFields;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static hometask9.cargo.service.CargoSortFields.SORT_BY_NAME;
import static hometask9.cargo.service.CargoSortFields.SORT_BY_WEIGHT;
import static java.util.Collections.singletonList;

public class Application {
    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();
        transportationService = ServiceHolder.getInstance().getTransportationService();

        StorageInitor storageInitor = new InMemoryStorageInitor();
        storageInitor.initStorage();

        printStorageData();
        demoSearchOperations();
        demoSortOperations();
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
        cargoSortCondition.setOrderReversed();
        System.out.println(
                "---------Sorting '" + getOrderingConditionsAsString(cargoSortCondition) + "'------");
        cargoService.getSortedCargos(cargoSortCondition);
        cargoService.printAll();
        System.out.println();
    }
}
