package hometask15.cargo.service;

public class CargoInUseDeleteException extends IllegalArgumentException {
    public CargoInUseDeleteException() {
    }

    public CargoInUseDeleteException(String s) {
        super(s);
    }
}
