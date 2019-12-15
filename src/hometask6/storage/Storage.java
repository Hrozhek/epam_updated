package hometask6.storage;

import hometask6.cargo.domain.Cargo;
import hometask6.carrier.domain.Carrier;
import hometask6.common.utils.ArrayUtils;
import hometask6.transportation.domain.Transportation;

import java.util.Objects;

public class Storage {

    private static final int START_ARRAY_CAPACITY = 10;

    private static Cargo[] cargos = new Cargo[START_ARRAY_CAPACITY];
    private static int cargoIndex = 0;

    private static Carrier[] carriers = new Carrier[START_ARRAY_CAPACITY];
    private static int carrierIndex = 0;

    private static Transportation[] transportations = new Transportation[START_ARRAY_CAPACITY];
    private static int transportationIndex = 0;

    public static Cargo[] getCargos() {
        return cargos;
    }

    public static void setCargos(Cargo[] cargos) {
        Storage.cargos = cargos;
    }

    public static int getCargoIndex() {
        return cargoIndex;
    }

    public static void setCargoIndex(int index) {
        cargoIndex = index;
    }

    public static Carrier[] getCarriers() {
        return carriers;
    }

    public static void setCarriers(Carrier[] carriers) {
        Storage.carriers = carriers;
    }

    public static int getCarrierIndex() {
        return carrierIndex;
    }

    public static void setCarrierIndex(int carrierIndex) {
        Storage.carrierIndex = carrierIndex;
    }

    public static Transportation[] getTransportations() {
        return transportations;
    }

    public static void setTransportations(Transportation[] transportations) {
        Storage.transportations = transportations;
    }

    public static int getTransportationIndex() {
        return transportationIndex;
    }

    public static void setTransportationIndex(int transportationIndex) {
        Storage.transportationIndex = transportationIndex;
    }

    public static void printAllCargos() {
        ArrayUtils.printArray(cargos);
    }

    public static void printAllCarriers() {
        ArrayUtils.printArray(carriers);
    }

    public static void addTransportation(Transportation transportation) {
        transportation.setId(IdGenerator.generateId());
        transportations[transportationIndex] = transportation;
        transportationIndex++;

        if (transportationIndex == transportations.length) {
            Transportation[] newTransportations = new Transportation[transportations.length * 2];
            ArrayUtils.copyArray(transportations, newTransportations);
            transportations = newTransportations;
        }
    }

    public static Transportation getTransportationById(long id) {
        for (Transportation transportation : transportations) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    public static void printAllTransportations() {
        ArrayUtils.printArray(transportations);
    }

}
