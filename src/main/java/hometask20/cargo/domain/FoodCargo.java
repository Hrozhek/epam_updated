package hometask20.cargo.domain;

import java.time.LocalDateTime;


public class FoodCargo extends Cargo {

    private LocalDateTime expirationDate;
    private int storeTemperature;

    @Override
    public CargoType getCargoType() {
        return CargoType.FOOD;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStoreTemperature() {
        return storeTemperature;
    }

    public void setStoreTemperature(int storeTemperature) {
        this.storeTemperature = storeTemperature;
    }

    @Override
    public String toString() {
        return "FoodCargo{" +
                "expirationDate=" + expirationDate +
                ", storeTemperature=" + storeTemperature +
                "} " + super.toString();
    }
}
