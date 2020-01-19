package hometask11.cargo.file;

import hometask11.cargo.domain.Cargo;

public class CargoFromFile {
    private Cargo cargo;
    private Long previousID;

    public Cargo getCargo() {
        return cargo;
    }

    public Long getPreviousID() {
        return previousID;
    }

    public CargoFromFile(Cargo cargo, Long previousID) {
        this.cargo = cargo;
        this.previousID = previousID;
    }
}
