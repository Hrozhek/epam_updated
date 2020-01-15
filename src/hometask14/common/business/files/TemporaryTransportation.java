package hometask14.common.business.files;

import hometask14.transportation.domain.Transportation;

public class TemporaryTransportation {
    private Transportation transportation;
    private String cargoId;

    public String getCargoId() {
        return cargoId;
    }

    public void setCargoId(String cargoId) {
        this.cargoId = cargoId;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public void setCarrierId(String carrierId) {
        this.carrierId = carrierId;
    }

    private String carrierId;

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }
}
