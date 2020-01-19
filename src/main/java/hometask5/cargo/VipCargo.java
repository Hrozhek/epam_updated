package hometask5.cargo;

import hometask5.carrier.CarrierType;
import hometask5.transportation.Transportation;

public class VipCargo extends Cargo {
    @Override
    public String toString() {
        return "Attention! Free next day delivery with plane and courier; " + super.toString();
    }

    @Override
    public void setTransportations(Transportation[] transportations) {
    }
}
