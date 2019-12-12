package hometask5;


import hometask5.cargo.*;
import hometask5.carrier.*;
import hometask5.transportation.*;



public class Storage {
    private static final int ARRAY_START_CAPACITY = 10;

    private static Cargo[] cargos = new Cargo[ARRAY_START_CAPACITY];
    private static int cargoIndex = 0;
    private static Transportation[] transportations = new Transportation[ARRAY_START_CAPACITY];
    private static int transportationIndex = 0;
    private static Carrier[] carriers = new Carrier[ARRAY_START_CAPACITY];
    private static int carrierIndex = 0;

    public static void addCargo(Cargo cargo) {
        cargo.setId();
        cargos[cargoIndex] = cargo;

        if (cargoIndex == (cargos.length - 1)) {
            Cargo[] newCargos = new Cargo[cargos.length * 2];
            copyArray(cargos, newCargos);
            cargos = newCargos;
        }
        cargoIndex++;
    }

    public static void printAllCargos() {
        for (Cargo cargo : cargos) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }

    public static void addTransportation(Transportation transportation) {
        transportation.setId();
        transportations[transportationIndex] = transportation;

        if (transportationIndex == (transportations.length - 1)) {
            Transportation[] newTransportations = new Transportation[transportations.length * 2];
            copyArray(transportations, newTransportations);
            transportations = newTransportations;
        }
        transportationIndex++;
    }

    public static void printAllTransportations() {
        for (Transportation transportation : transportations) {
            if (transportation != null) {
                System.out.println(transportation);
            }
        }
    }

    public static void addCarrier(Carrier carrier) {
        carrier.setId();
        carriers[carrierIndex] = carrier;

        if (carrierIndex == (carriers.length - 1)) {
            Carrier[] newCarriers = new Carrier[carriers.length * 2];
            copyArray(carriers, newCarriers);
            carriers = newCarriers;
        }
        carrierIndex++;
    }

    public static void printAllCarriers() {
        for (Carrier carrier : carriers) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }

    private static void copyArray(Object[] src, Object[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }


}
