package hometask12.carrier.domain;
import hometask12.common.business.BaseEntity;
import hometask12.transportation.domain.Transportation;

import java.util.Objects;

public class Carrier extends BaseEntity {

    private String name;
    private String address;
    private CarrierType carrierType;
    private Transportation[] transportations;

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

    public Transportation[] getTransportations() {
        return transportations;
    }

    public void setTransportations(Transportation[] transportations) {
        this.transportations = transportations;
    }

    @Override
    public String toString() {
        return "Carrier{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", carrierType=" + carrierType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrier carrier = (Carrier) o;
        return Objects.equals(name, carrier.name) &&
                Objects.equals(address, carrier.address) &&
                carrierType == carrier.carrierType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, carrierType);
    }
}
