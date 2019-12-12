package hometask5;

import hometask5.cargo.*;
import hometask5.carrier.*;
import hometask5.transportation.*;

import java.util.Date;

public class Demo {
    //While we can't catch exception it will throw it to JVM
    public static void main(String[] args) throws CloneNotSupportedException {
        

        Cargo apple = new Cargo();
        apple.setName("Apple");
        apple.setCargoType(CargoType.FOOD);
        Storage.addCargo(apple);

        Cargo orange = new Cargo();
        orange.setName("Orange");
        orange.setCargoType(CargoType.FOOD);
        Storage.addCargo(orange);

        Cargo macBook = new VipCargo();
        macBook.setName("MacBook");
        macBook.setCargoType(CargoType.COMPUTERS);
        Storage.addCargo(macBook);

        Carrier pdp = new Carrier();
        pdp.setName("PDP");
        pdp.setCarrierType(CarrierType.PLANE);
        Storage.addCarrier(pdp);

        Carrier defex = new Carrier();
        defex.setName("DefEx");
        defex.setCarrierType(CarrierType.CAR);
        Storage.addCarrier(defex);

        Carrier minipigExpress = new Carrier();
        minipigExpress.setName("MinipigExpress");
        minipigExpress.setCarrierType(CarrierType.SHIP);
        Storage.addCarrier(minipigExpress);

        Transportation transportation1 = new Transportation();
        transportation1.setBillTo("Ivan ivanich");
        transportation1.setCargo(orange);
        transportation1.setCarrier(pdp);
        Storage.addTransportation(transportation1);

        Transportation transportation2 = new Transportation();
        transportation2.setBillTo("Tax payers");
        transportation2.setCargo(macBook);
        transportation2.setCarrier(minipigExpress);
        transportation2.setDescription("Little gift for new president");
        transportation2.setDate(new Date(108, 4, 7));
        Storage.addTransportation(transportation2);

        Transportation transportation3 = new Transportation();
        transportation3.setBillTo("Ivan ivanich");
        transportation3.setCargo(apple);
        transportation3.setCarrier(defex);
        transportation3.setDate(new Date());
        Storage.addTransportation(transportation3);

        Transportation transportation4 = (Transportation) transportation2.clone();
        Storage.addTransportation(transportation4);
        Transportation transportation5 = (Transportation) transportation2.clone();
        Storage.addTransportation(transportation5);
        Transportation transportation6 = (Transportation) transportation2.clone();
        Storage.addTransportation(transportation6);
        Storage.addTransportation(null);

        Storage.printAllCargos();
        Storage.printAllCarriers();
        Storage.printAllTransportations();

        System.out.println("Test getAllTransportations");
        Transportation[] testGetAllTransportations = Storage.getAllTransportations();
        for (Transportation transportation : testGetAllTransportations) {
            System.out.println(transportation);
        }

        System.out.println("Test getCarrierById, pdp must be shown");
        Long testId = pdp.getId();
        System.out.println(Storage.getCarrierById(testId));

        System.out.println("Test getCargoByName, first entry of macbook must be shown");
        String testName = macBook.getName();
        System.out.println(Storage.getCargoByName(testName));
    }
}
