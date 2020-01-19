package hometask11.carrier.service;

public class CarrierInUseDeleteException extends IllegalArgumentException {
    public CarrierInUseDeleteException() {
    }

    public CarrierInUseDeleteException(String s) {
        super(s);
    }
}
