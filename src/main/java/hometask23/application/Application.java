package hometask23.application;

import hometask23.application.serviceholder.ServiceHolder;
import hometask23.application.serviceholder.StorageType;
import hometask23.cargo.domain.Cargo;
import hometask23.cargo.service.CargoService;
import hometask23.cargo.service.CargoSortCondition;
import hometask23.cargo.service.CargoSortFields;
import hometask23.carrier.domain.Carrier;
import hometask23.carrier.service.CarrierService;
import hometask23.common.business.exception.checked.InitStorageException;
import hometask23.common.business.files.SimpleFileSaver;
import hometask23.storage.initor.InitStorageType;
import hometask23.storage.initor.StorageInitor;
import hometask23.transportation.domain.Transportation;
import hometask23.transportation.service.TransportationService;
import hometask23.cargo.domain.ClothesCargo;
import hometask23.cargo.domain.FoodCargo;
import hometask23.carrier.domain.CarrierType;

import java.time.LocalDateTime;
import java.util.*;

import static hometask23.storage.IdGenerator.generateId;
import static hometask23.cargo.service.CargoSortFields.SORT_BY_NAME;
import static hometask23.cargo.service.CargoSortFields.SORT_BY_WEIGHT;
import static hometask23.storage.initor.StorageInitorFactory.getStorageInitor;
import static java.util.Collections.singletonList;

public class Application {
    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        //ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        ServiceHolder.initServiceHolder(StorageType.DATABASE);
        transportationService = ServiceHolder.getInstance().getTransportationService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();

        //StorageInitor storageInitor = getStorageInitor(InitStorageType.MULTI_THREAD);
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.XML_SAX_FILE);
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.XML_DOM_FILE);
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.TEXT_FILE);
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.MEMORY);
        StorageInitor storageInitor = getStorageInitor(InitStorageType.DATA_BASE);
        try {
            storageInitor.initStorage();
        } catch (InitStorageException e) {
            e.printStackTrace();
        }

        printStorageData();
        demoSaveToDataBase();
        printStorageData();
        //demoSaveToFile();
        //demoSearchOperations();
        //demoSortOperations();
        //demoCarrierDeleter(carrierService, transportationService);
    }


    private static void demoSaveToFile() {
        printSeparator();
        System.out.println("Test of writing to file");
        SimpleFileSaver fileSaver = new SimpleFileSaver();
        fileSaver.setCargoService(cargoService);
        fileSaver.setCarrierService(carrierService);
        fileSaver.setTransportationService(transportationService);
        fileSaver.save();
    }

    private static void demoSearchOperations() {
        System.out.println("SEARCH CARGO BY ID = 1");
        Cargo toPrint = cargoService.getById(1L).get();
        System.out.println(toPrint);
        printSeparator();

        System.out.println("SEARCH CARGO BY ID = 123213");
        Cargo absent = cargoService.getById(123213L).get();
        System.out.println(absent);
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

        System.out.println("ALL TRANSPORTATIONS");
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

    private static void demoSaveToDataBase() {
        FoodCargo orange = new FoodCargo();
        orange.setStoreTemperature(34);
        orange.setExpirationDate(LocalDateTime.now());
        orange.setName("Orange");
        orange.setWeight(145);
        for (int i = 0; i < 15; i++) {
            generateId();
        }
        orange.setId(generateId());

        ClothesCargo parka = new ClothesCargo();
        parka.setSize("XS");
        parka.setMaterial("Cotton 1%, Poliester 99%");
        parka.setName("Alpha Industries N13B");
        parka.setWeight(5);
        parka.setId(generateId());
        cargoService.add(orange);
        cargoService.add(parka);

        Carrier pdp = new Carrier();
        pdp.setId(generateId());
        pdp.setName("PDP");
        pdp.setCarrierType(CarrierType.PLANE);

        Carrier defex = new Carrier();
        defex.setId(generateId());
        defex.setName("DefEx");
        defex.setCarrierType(CarrierType.SHIP);

        Carrier minipigExpress = new Carrier();
        minipigExpress.setId(generateId());
        minipigExpress.setName("MinipigExpress");
        minipigExpress.setCarrierType(CarrierType.SHIP);

        carrierService.add(pdp);
        carrierService.add(defex);
        carrierService.add(minipigExpress);

        Transportation transportation1 = new Transportation();
        transportation1.setId(generateId());
        transportation1.setBillTo("Ivan ivanich");
        transportation1.setCargo(orange);
        transportation1.setCarrier(pdp);
        transportation1.setDescription("Just a garbage");
        transportation1.setTransportationBeginDate(LocalDateTime.now());


        Transportation transportation2 = new Transportation();
        transportation2.setId(generateId());
        transportation2.setBillTo("Tax payers");
        transportation2.setCargo(parka);
        transportation2.setCarrier(minipigExpress);
        transportation2.setDescription("Little gift for new president");
        transportation2.setTransportationBeginDate(LocalDateTime.now());

        Transportation transportation3 = new Transportation();
        transportation3.setId(generateId());
        transportation3.setBillTo("Ivan ivanich");
        transportation3.setCargo(orange);
        transportation3.setCarrier(defex);
        transportation3.setTransportationBeginDate(LocalDateTime.now());

        transportationService.add(transportation3);
        transportationService.add(transportation1);
        transportationService.add(transportation2);
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
