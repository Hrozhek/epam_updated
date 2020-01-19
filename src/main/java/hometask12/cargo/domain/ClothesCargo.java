package hometask12.cargo.domain;

import hometask14.cargo.domain.Cargo;
import hometask14.cargo.domain.CargoType;

public class ClothesCargo extends Cargo {

    private String size;
    private String material;

    @Override
    public hometask14.cargo.domain.CargoType getCargoType() {
        return CargoType.CLOTHES;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}

