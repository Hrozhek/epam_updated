package hometask5;


import hometask5.cargo.*;
import hometask5.carrier.*;
import hometask5.transportation.*;


public final class Storage {
    private Storage(){
    }
    private static final int ARRAY_START_CAPACITY = 10;
    private static Cargo[] cargos = new Cargo[ARRAY_START_CAPACITY];
    private static int cargoIndex = 0;
    private static Transportation[] transportations = new Transportation[ARRAY_START_CAPACITY];
    private static int transportationIndex = 0;
    private static Carrier[] carriers = new Carrier[ARRAY_START_CAPACITY];
    private static int carrierIndex = 0;

    public static void addCargo(Cargo cargo) {
        if (cargo != null) {
            cargo.setId();
            cargos[cargoIndex] = cargo;
            if (cargoIndex == (cargos.length - 1)) {
                Cargo[] newCargos = new Cargo[cargos.length * 2];
                copyArray(cargos, newCargos);
                cargos = newCargos;
            }
            cargoIndex++;
        }
    }

    public static void printAllCargos() {
        for (Cargo cargo : cargos) {
            if (cargo != null) {
                System.out.println(cargo);
            }
        }
    }

    public static void addTransportation(Transportation transportation) {
        if (transportation != null) {
            transportation.setId();
            transportations[transportationIndex] = transportation;
            if (transportationIndex == (transportations.length - 1)) {
                Transportation[] newTransportations = new Transportation[transportations.length * 2];
                copyArray(transportations, newTransportations);
                transportations = newTransportations;
            }
            transportationIndex++;
        }
    }

    public static void printAllTransportations() {
        for (Transportation transportation : transportations) {
            if (transportation != null) {
                System.out.println(transportation);
            }
        }
    }

    public static void addCarrier(Carrier carrier) {
        if (carrier != null) {
            carrier.setId();
            carriers[carrierIndex] = carrier;
            if (carrierIndex == (carriers.length - 1)) {
                Carrier[] newCarriers = new Carrier[carriers.length * 2];
                copyArray(carriers, newCarriers);
                carriers = newCarriers;
            }
            carrierIndex++;
        }
    }

    public static void printAllCarriers() {
        for (Carrier carrier : carriers) {
            if (carrier != null) {
                System.out.println(carrier);
            }
        }
    }

    public static Cargo getCargoById(Long id) {
        for (Cargo cargo : cargos) {
            if (cargo != null) {
                if (cargo.getId().equals(id)) {
                    return cargo;
                }
            }
        }
        return null;
    }

    public static Cargo getCargoByName(String name) {
        for (Cargo cargo : cargos) {
            if (cargo != null && cargo.getName() != null) {
                if (cargo.getName().equals(name)) {
                    return cargo;
                }
            }
        }
        return null;
    }

    public static Transportation getTransportationById(Long id) {
        for (Transportation transportation : transportations) {
            if (transportation != null) {
                if (transportation.getId().equals(id)) {
                    return transportation;
                }
            }
        }
        return null;
    }

    public static Carrier getCarrierById(Long id) {
        for (Carrier carrier : carriers) {
            if (carrier != null) {
                if (carrier.getId().equals(id)) {
                    return carrier;
                }
            }
        }
        return null;
    }

    public static Carrier getCarrierByName(String name) {
        for (Carrier carrier : carriers) {
            if (carrier != null && carrier.getName() != null) {
                if (carrier.getName().equals(name)) {
                    return carrier;
                }
            }
        }
        return null;
    }

    public static Cargo[] getAllCargos() {
        Cargo[] newCargos = new Cargo[positionWhereIsFirstNull(cargos)];
        copyArray(cargos, newCargos);
        return newCargos;
    }

    public static Transportation[] getAllTransportations() {
        Transportation[] newTransportations = new Transportation[positionWhereIsFirstNull(transportations)];
        copyArray(transportations, newTransportations);
        return newTransportations;
    }

    public static Carrier[] getAllCarriers() {
        Carrier[] newCarriers = new Carrier[positionWhereIsFirstNull(carriers)];
        copyArray(carriers, newCarriers);
        return newCarriers;
    }

    private static void copyArray(Object[] src, Object[] dest) {
        for (int i = 0; i < src.length; i++) {
            if (src[i] != null) {
                dest[i] = src[i];
            }
        }
    }

    private static int positionWhereIsFirstNull(Object[] src) {
        int currentPosition = 0;
        while (src[currentPosition] != null && currentPosition < src.length) {
            currentPosition++;
        }
        return currentPosition;
    }
}
