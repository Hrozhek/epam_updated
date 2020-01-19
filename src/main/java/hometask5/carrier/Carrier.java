package hometask5.carrier;

import hometask5.IdGenerator;
import hometask5.transportation.Transportation;

import java.util.Arrays;

public class Carrier {
    private boolean fragileAvailable;
    private Long id;
    private String name;
    private String address;
    private CarrierType carrierType;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", carrierType=" + carrierType +
                '}';
    }

    public boolean isFragileAvailable() {
        return fragileAvailable;
    }

    public void setFragileAvailable(boolean fragileAvailable) {
        this.fragileAvailable = fragileAvailable;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CarrierType getCarrierType() {
        return carrierType;
    }

    public void setCarrierType(CarrierType carrierType) {
        this.carrierType = carrierType;
    }

}
