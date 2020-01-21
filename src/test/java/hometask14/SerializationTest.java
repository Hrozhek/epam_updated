package hometask14;


import hometask14.cargo.domain.ClothesCargo;
import hometask14.cargo.domain.FoodCargo;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static hometask14.common.solutions.comparator.SimpleComparator.LONG_COMPARATOR;
import static hometask14.common.solutions.comparator.SimpleComparator.STRING_COMPARATOR;

public class SerializationTest {
    private File tempFile = null;
    private Random randomizer = new Random();

    @Before
    public void createTempFile() throws IOException {
        tempFile = File.createTempFile("hometask14", ".txt");
    }

    @After
    public void deleteTempFile() {
        if (tempFile != null) {
            tempFile.delete();
        }
    }

    @Test
    public void testSerializationFoodCargo() throws Exception {
        FoodCargo cargo = prepareFood();
        String pathToFile = tempFile.toString();

        serializeToFile(cargo, pathToFile);
        FoodCargo deserialized = readSerializedObjectFromFile(pathToFile);
        Assertions.assertTrue(areFoodEntitiesEquals(cargo, deserialized));
    }

    @Test
    public void testSerializationFoodCargos() throws Exception {
        List<FoodCargo> foods = Arrays.asList(prepareFood(), prepareFood());
        String pathToFile = tempFile.toString();
        serializeToFile(foods, pathToFile);
        List<FoodCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areFoodEntitiesEquals(foods, deserialized));
    }

    @Test
    public void testSerializationFoodNullCargo() throws Exception {
        String pathToFile = tempFile.toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    @Test
    public void testSerializationClothesCargo() throws Exception {
        ClothesCargo clothes = prepareClothes();
        String pathToFile = tempFile.toString();
        serializeToFile(clothes, pathToFile);
        ClothesCargo deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClothesEntitiesEquals(clothes, deserialized));
    }

    @Test
    public void testSerializationClothesCargos() throws Exception {
        List<ClothesCargo> clothes = Arrays.asList(prepareClothes(), prepareClothes());
        String pathToFile = tempFile.toString();

        serializeToFile(clothes, pathToFile);
        List<ClothesCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClothesEntitiesEquals(clothes, deserialized));
    }

    @Test
    public void testSerializationClothesNullCargos() throws Exception {
        String pathToFile = tempFile.toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    private <T> void serializeToFile(T entity, String file) throws Exception {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(entity);
        }
    }

    private <T> T readSerializedObjectFromFile(String file) throws Exception {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (T) objectInputStream.readObject();
        }
    }

    private FoodCargo prepareFood() {
        FoodCargo food = new FoodCargo();
        food.setId(randLong());
        food.setName(randString());
        food.setWeight(randInt());
        food.setStoreTemperature(randInt());
        food.setExpirationDate(new Date());

        return food;
    }

    private ClothesCargo prepareClothes() {
        ClothesCargo clothes = new ClothesCargo();
        clothes.setName(randString());
        clothes.setId(randLong());
        clothes.setSize(randString());
        clothes.setWeight(randInt());
        clothes.setMaterial(randString());

        return clothes;
    }

    private String randString() {
        byte[] arrayForString = new byte[6];
        randomizer.nextBytes(arrayForString);
        return new String(arrayForString, StandardCharsets.UTF_8);
    }

    private int randInt() {
        return randomizer.nextInt();
    }

    private long randLong() {
        return randomizer.nextLong();
    }

    private boolean areFoodEntitiesEquals(FoodCargo food1, FoodCargo food2) {
        if (food1 == null && food2 == null) {
            return true;
        } else if (food1 != null && food2 == null) {
            return false;
        } else if (food1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(food1.getName(), food2.getName()) == 0
                    && LONG_COMPARATOR.compare(food1.getId(), food2.getId()) == 0
                    && food1.getWeight() == food2.getWeight()
                    && food1.getStoreTemperature() == food2.getStoreTemperature();
            //continue in this way
        }
    }

    private boolean areFoodEntitiesEquals(List<FoodCargo> foods1, List<FoodCargo> foods2) {
        if (foods1 == null && foods2 == null) {
            return true;
        } else if (foods1 != null && foods2 == null) {
            return false;
        } else if (foods1 == null) {
            return false;
        } else if (foods1.size() != foods2.size()) {
            return false;
        } else {
            for (int i = 0; i < foods1.size(); i++) {
                if (!areFoodEntitiesEquals(foods1.get(i), foods2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean areClothesEntitiesEquals(ClothesCargo clothes1, ClothesCargo clothes2) {
        if (clothes1 == null && clothes2 == null) {
            return true;
        } else if (clothes1 != null && clothes2 == null) {
            return false;
        } else if (clothes1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(clothes1.getName(), clothes2.getName()) == 0
                    && LONG_COMPARATOR.compare(clothes1.getId(), clothes2.getId()) == 0
                    && STRING_COMPARATOR.compare(clothes1.getMaterial(), clothes2.getMaterial()) == 0;
            //continue in this way
        }
    }

    private boolean areClothesEntitiesEquals(List<ClothesCargo> clothes1,
                                             List<ClothesCargo> clothes2) {
        if (clothes1 == null && clothes2 == null) {
            return true;
        } else if (clothes1 != null && clothes2 == null) {
            return false;
        } else if (clothes1 == null) {
            return false;
        } else if (clothes1.size() != clothes2.size()) {
            return false;
        } else {
            for (int i = 0; i < clothes1.size(); i++) {
                if (!areClothesEntitiesEquals(clothes1.get(i), clothes2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }
}
