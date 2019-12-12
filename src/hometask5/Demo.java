package hometask5;

import hometask5.cargo.*;

public class Demo {
    public static void main(String[] args) {
        Cargo cargo = new Cargo();
        cargo.setName("A1");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A2");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A3");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A4");
        Storage.addCargo(cargo);
        cargo = new Cargo();
        cargo.setName("A5");
        Storage.addCargo(cargo);
        Storage.printAllCargos();

    }
}
