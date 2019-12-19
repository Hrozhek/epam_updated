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
}
