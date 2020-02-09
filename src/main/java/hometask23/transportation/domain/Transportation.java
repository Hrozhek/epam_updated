package hometask23.transportation.domain;

import hometask23.cargo.domain.Cargo;
import hometask23.carrier.domain.Carrier;
import hometask23.common.business.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transportation extends BaseEntity {

    private Cargo cargo;
    private Carrier carrier;
    private String description;
    private String billTo;
    private LocalDateTime transportationBeginDate;

    public LocalDateTime getTransportationBeginDate() {
        return transportationBeginDate;
    }

    public void setTransportationBeginDate(LocalDateTime transportationBeginDate) {
        this.transportationBeginDate = transportationBeginDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "Transportation{" +
                "id=" + id +
                ", cargo=" + cargo +
                ", carrier=" + carrier +
                ", description='" + description + '\'' +
                ", billTo='" + billTo + '\'' +
                ", transportationBeginDate=" + transportationBeginDate +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transportation that = (Transportation) o;
        return Objects.equals(cargo, that.cargo) &&
                Objects.equals(carrier, that.carrier) &&
                Objects.equals(description, that.description) &&
                Objects.equals(billTo, that.billTo) &&
                Objects.equals(transportationBeginDate, that.transportationBeginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cargo, carrier, description, billTo, transportationBeginDate);
    }
}
