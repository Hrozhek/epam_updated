package hometask14.application;

import hometask14.application.serviceholder.ServiceHolder;
import hometask14.application.serviceholder.StorageType;
import hometask14.cargo.domain.Cargo;
import hometask14.cargo.service.CargoService;
import hometask14.cargo.service.CargoSortCondition;
import hometask14.cargo.service.CargoSortFields;
import hometask14.carrier.domain.Carrier;
import hometask14.carrier.service.CarrierService;
import hometask14.common.business.BaseEntity;
import hometask14.common.business.exception.checked.InitStorageException;
import hometask14.common.business.files.SimpleFileSaver;
import hometask14.common.solutions.service.BaseService;
import hometask14.storage.Storage;
import hometask14.storage.initor.InitStorageType;
import hometask14.storage.initor.StorageInitor;
import hometask14.transportation.domain.Transportation;
import hometask14.transportation.service.TransportationService;

import java.io.*;
import java.util.*;

import static hometask14.cargo.service.CargoSortFields.SORT_BY_NAME;
import static hometask14.cargo.service.CargoSortFields.SORT_BY_WEIGHT;
import static hometask14.storage.initor.StorageInitorFactory.getStorageInitor;
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

        StorageInitor storageInitor = getStorageInitor(InitStorageType.XML_SAX_FILE);
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.XML_DOM_FILE);
        //StorageInitor storageInitor = new InMemoryStorageInitor();
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.TEXT_FILE);
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.MEMORY);
        try {
            storageInitor.initStorage();
        } catch (InitStorageException e) {
            e.printStackTrace();
        }

        printStorageData();
        demoSerialize();
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
    public static void serializeObjects(BaseService service, ObjectOutput output) throws IOException {
        List<BaseEntity> entities = service.getAll();
        output.writeObject(entities);
    }

    public static List<Cargo> deserializeCargos(ObjectInputStream reader) throws Exception{
        System.out.println("Previous cargoes:");
        cargoService.printAll();
        System.out.println("After serialization:");
        List<Cargo> cargos = (List<Cargo>) reader.readObject();
        for (Cargo cargo : cargos) {
            System.out.println(cargo);
        }
        return cargos;
    }

    public static List<Carrier> deserializeCarriers(ObjectInputStream reader) throws Exception{
        System.out.println("Previous carrieres:");
        carrierService.printAll();
        System.out.println("After serialization:");
        List<Carrier> carriers = (List<Carrier>) reader.readObject();
        for (Carrier carrier : carriers) {
            System.out.println(carrier);
        }
        return carriers;
    }

    public static List<Transportation> deserializeTrasnportations(ObjectInputStream reader) throws Exception{
        System.out.println("Previous transportationes:");
        transportationService.printAll();
        System.out.println("After serialization:");
        List<Transportation> transportations = (List<Transportation>) reader.readObject();
        for (Transportation transportation : transportations) {
            System.out.println(transportation);
        }
        return transportations;
    }

    private static void demoSerialize() {
        File file = null;
        try {
            file = File.createTempFile("hometask14", ".txt");
            try (ObjectOutput writer = new ObjectOutputStream(new FileOutputStream(file));
                 ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file))) {
                serializeObjects(cargoService, writer);
                deserializeCargos(reader);
                serializeObjects(carrierService, writer);
                deserializeCarriers(reader);
                serializeObjects(transportationService, writer);
                deserializeTrasnportations(reader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }
}
