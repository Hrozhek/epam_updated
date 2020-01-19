package hometask11.cargo.file;

import hometask11.cargo.domain.Cargo;
import hometask11.cargo.domain.CargoType;
import hometask11.cargo.domain.ClothesCargo;
import hometask11.cargo.domain.FoodCargo;
import hometask11.common.solutions.utils.ParseBySeparator;

import java.util.Date;
import java.util.List;

public class CargoParser {

    private final static Character SEPARATOR = '|';
    private final static short INDEX_OF_TYPE = 0;
    private final static short INDEX_OF_ID = 1;
    private final static short INDEX_OF_NAME = 2;
    private final static short INDEX_OF_WEIGHT = 3;
    private final static short INDEX_OF_SIZE_OR_EXPIRATION = 4;
    private final static short INDEX_OF_MATERIAL_OR_STORETEMP = 5;

    private static String cargoType;
    private static Long previousID;
    private static String name;
    private static Integer weight;

    private String fileLine;

    private  CargoParser() {

    }

    public static CargoFromFile parse(String fileLine) {
        Cargo cargo = null;
        List<String> fields = ParseBySeparator.getSeparated(fileLine, SEPARATOR);
        cargoType = fields.get(INDEX_OF_TYPE);
        if (cargoType.equals("FOOD")) {
            FoodCargo temp = new FoodCargo();
            commonParse(temp, fields);
            temp.setExpirationDate(new Date(Integer.parseInt(fields.get(INDEX_OF_SIZE_OR_EXPIRATION))));
            temp.setStoreTemperature(Integer.parseInt(fields.get(INDEX_OF_MATERIAL_OR_STORETEMP)));
            cargo = temp;
        }
        else if (cargoType.equals("CLOTHES")) {
            ClothesCargo temp = new ClothesCargo();
            commonParse(temp, fields);
            temp.setSize(fields.get(INDEX_OF_SIZE_OR_EXPIRATION));
            temp.setMaterial(fields.get(INDEX_OF_MATERIAL_OR_STORETEMP));
            cargo = temp;
        }
        return new CargoFromFile(cargo, Long.parseLong(fields.get(INDEX_OF_ID)));
    }

    private static void commonParse(Cargo cargo, List<String> cargoFields) {
        cargo.setName(cargoFields.get(INDEX_OF_NAME));
        cargo.setWeight(Integer.parseInt(cargoFields.get(INDEX_OF_WEIGHT)));
    }
}
