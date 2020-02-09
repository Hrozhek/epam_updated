package hometask23.cargo.domain;

public class ClothesCargo extends Cargo {

    private String size;
    private String material;

    @Override
    public CargoType getCargoType() {
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

    @Override
    public String toString() {
        return "ClothesCargo{" +
                "size='" + size + '\'' +
                ", material='" + material + '\'' +
                "} " + super.toString();
    }
}

