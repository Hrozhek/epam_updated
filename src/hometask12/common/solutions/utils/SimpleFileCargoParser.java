package hometask12.common.solutions.utils;

import hometask12.cargo.domain.Cargo;
import hometask12.cargo.domain.ClothesCargo;
import hometask12.cargo.domain.FoodCargo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class SimpleFileCargoParser {

    private final static Character SEPARATOR = '|';
    private final static short INDEX_OF_TYPE = 0;
    private final static short INDEX_OF_ID = 1;
    private final static short INDEX_OF_NAME = 2;
    private final static short INDEX_OF_WEIGHT = 3;
    private final static short INDEX_OF_SIZE_OR_EXPIRATION = 4;
    private final static short INDEX_OF_MATERIAL_OR_STORETEMP = 5;

    private static String cargoType;

    private SimpleFileCargoParser() {

    }

    public static void parse(String fileLine, Map<String, Cargo> cargoMap) {
        Cargo cargo = null;
        List<String> fields = ParseBySeparator.getSeparated(fileLine, SEPARATOR);
        cargoType = fields.get(INDEX_OF_TYPE);
        if (cargoType.equals("FOOD")) {
            FoodCargo temp = new FoodCargo();
            temp.setExpirationDate(new Date(Integer.parseInt(fields.get(INDEX_OF_SIZE_OR_EXPIRATION))));
            temp.setStoreTemperature(Integer.parseInt(fields.get(INDEX_OF_MATERIAL_OR_STORETEMP)));
            cargo = temp;
        } else if (cargoType.equals("CLOTHES")) {
            ClothesCargo temp = new ClothesCargo();
            temp.setSize(fields.get(INDEX_OF_SIZE_OR_EXPIRATION));
            temp.setMaterial(fields.get(INDEX_OF_MATERIAL_OR_STORETEMP));
            cargo = temp;
        }
        cargo.setName(fields.get(INDEX_OF_NAME));
        cargo.setWeight(Integer.parseInt(fields.get(INDEX_OF_WEIGHT)));
        cargoMap.put(fields.get(INDEX_OF_ID), cargo);
    }
}
