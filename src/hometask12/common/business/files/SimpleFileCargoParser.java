package hometask12.common.business.files;

import hometask14.cargo.domain.Cargo;
import hometask14.cargo.domain.ClothesCargo;
import hometask14.cargo.domain.FoodCargo;
import hometask14.common.solutions.utils.ParseBySeparator;

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
        String fourthParam = fields.get(INDEX_OF_SIZE_OR_EXPIRATION);
        String fifthParam = fields.get(INDEX_OF_MATERIAL_OR_STORETEMP);
        if (cargoType.equals("FOOD")) {
            FoodCargo temp = new FoodCargo();
            if (!fourthParam.isEmpty()) {
                temp.setExpirationDate(new Date(Long.parseLong(fourthParam)));
            }
            if (!fifthParam.isEmpty()) {
                temp.setStoreTemperature(Integer.parseInt(fifthParam));
            }
            cargo = temp;
        } else if (cargoType.equals("CLOTHES")) {
            ClothesCargo temp = new ClothesCargo();
            if (!fourthParam.isEmpty()) {
                temp.setSize(fourthParam);
            }
            if (!fifthParam.isEmpty()) {
                temp.setMaterial(fifthParam);
            }
            cargo = temp;
        }
        String name = fields.get(INDEX_OF_NAME);
        if (!name.isEmpty()) {
            cargo.setName(name);
        }
        String weight = fields.get(INDEX_OF_WEIGHT);
        if (!weight.isEmpty()) {
            cargo.setWeight(Integer.parseInt(weight));
        }
        cargoMap.put(fields.get(INDEX_OF_ID), cargo);
    }
}
