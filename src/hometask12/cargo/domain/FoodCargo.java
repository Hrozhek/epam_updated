package hometask12.cargo.domain;

import hometask13.cargo.domain.Cargo;
import hometask13.cargo.domain.CargoType;

import java.util.Date;

public class FoodCargo extends Cargo {

    private Date expirationDate;
    private int storeTemperature;

    @Override
    public hometask13.cargo.domain.CargoType getCargoType() {
        return CargoType.FOOD;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getStoreTemperature() {
        return storeTemperature;
    }

    public void setStoreTemperature(int storeTemperature) {
        this.storeTemperature = storeTemperature;
    }
}
