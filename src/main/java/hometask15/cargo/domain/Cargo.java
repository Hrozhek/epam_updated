package hometask15.cargo.domain;

import hometask15.common.business.BaseEntity;
import hometask15.transportation.domain.Transportation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Cargo extends BaseEntity {

    protected String name;
    protected double weight;
    protected List <Transportation> transportations;
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

    public List<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cargoType=" + cargoType +
                ", transportations=" + transportations +
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