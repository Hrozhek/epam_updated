package hometask8.cargo.domain;

import hometask8.common.domain.BaseEntity;
import hometask8.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Objects;

public abstract class Cargo extends BaseEntity {

    protected String name;
    protected double weight;
    protected Transportation[] transportations;
    protected CargoType cargoType;

    public Cargo() {
        cargoType = this.getCargoType();
    }

    public abstract CargoType getCargoType();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        return weight == cargo.weight &&
                Objects.equals(name, cargo.name) &&
                cargoType == cargo.cargoType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight, cargoType);
    }


}