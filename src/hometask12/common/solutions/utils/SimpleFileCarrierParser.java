package hometask12.common.solutions.utils;

import hometask12.carrier.domain.Carrier;
import hometask12.carrier.domain.CarrierType;

import java.util.List;
import java.util.Map;

public class SimpleFileCarrierParser {
    private final static Character SEPARATOR = '|';
    private final static short INDEX_OF_TYPE = 0;
    private final static short INDEX_OF_ID = 1;
    private final static short INDEX_OF_NAME = 2;
    private final static short INDEX_OF_ADDRESS = 3;

    private static String carrierType;

    public static void parse(String fileLine, Map<String, Carrier> carrierMap) {
        Carrier carrier = new Carrier();
        List<String> fields = ParseBySeparator.getSeparated(fileLine, SEPARATOR);
        carrierType = fields.get(INDEX_OF_TYPE);
        if (carrierType.equals("SHIP")) {
            carrier.setCarrierType(CarrierType.SHIP);
        } else if (carrierType.equals("PLANE")) {
            carrier.setCarrierType(CarrierType.PLANE);
        } else if (carrierType.equals("CAR")) {
            carrier.setCarrierType(CarrierType.CAR);
        } else if (carrierType.equals("TRAIN")) {
            carrier.setCarrierType(CarrierType.TRAIN);
        }
        String name = fields.get(INDEX_OF_NAME);
        String address = fields.get(INDEX_OF_ADDRESS);
        if (!name.isEmpty()) {
            carrier.setName(name);
        }
        if (!address.isEmpty()) {
            carrier.setAddress(address);
        }
        carrierMap.put(fields.get(INDEX_OF_ID), carrier);
    }
}

