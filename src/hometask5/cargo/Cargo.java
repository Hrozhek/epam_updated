package hometask5.cargo;


import hometask5.IdGenerator;
import hometask5.transportation.*;

import java.util.Arrays;

public class Cargo implements Cloneable {
    private Long id;
    private String name;
    private int weight;
    private CargoType cargoType;
    private Transportation[] transportations;

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", transportations=" + Arrays.toString(transportations) +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId() {
        if (this.id == null) {
            this.id = IdGenerator.generateId();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Cargo newCargo = new Cargo();
        newCargo.setId();
        newCargo.setName(this.name);
        newCargo.setCargoType(this.cargoType);
        newCargo.setWeight(this.weight);
        newCargo.setTransportations(this.getTransportations());
        return  newCargo;
    }
}
