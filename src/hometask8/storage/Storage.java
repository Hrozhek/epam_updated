package hometask8.storage;

import hometask8.cargo.domain.Cargo;
import hometask8.carrier.domain.Carrier;
import hometask8.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final int START_ARRAY_CAPACITY = 10;

    public static Cargo[] cargos = new Cargo[START_ARRAY_CAPACITY];
    public static int cargoIndex = 0;

    public static Carrier[] carriers = new Carrier[START_ARRAY_CAPACITY];
    public static int carrierIndex = 0;

    public static Transportation[] transportations = new Transportation[START_ARRAY_CAPACITY];
    public static int transportationIndex = 0;

    public static List<Cargo> cargoList = new ArrayList<>();
    public static List<Carrier> carrierList = new ArrayList<>();
    public static List<Transportation> transportationList = new ArrayList<>();
/*
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
    */
}
