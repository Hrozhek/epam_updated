package hometask23.storage.initor.fileinitor;

import hometask23.application.serviceholder.ServiceHolder;
import hometask23.cargo.domain.Cargo;
import hometask23.carrier.domain.Carrier;
import hometask23.common.business.files.TemporaryTransportation;
import hometask23.storage.initor.StorageInitor;
import hometask23.transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BaseFileInitor implements StorageInitor {

    protected void setReferencesBetweenEntities(Map<String, Cargo> cargoMap,
                                                Map<String, Carrier> carrierMap, List<TemporaryTransportation> parsedTransportations) {

        for (TemporaryTransportation parsedTransportation : parsedTransportations) {
            Cargo cargo = cargoMap.get(parsedTransportation.getCargoId());
            Carrier carrier = carrierMap.get(parsedTransportation.getCarrierId());
            Transportation transportation = parsedTransportation.getTransportation();

            if (cargo != null) {
                List<Transportation> transportations =
                        cargo.getTransportations() == null ? new ArrayList<>() : cargo.getTransportations();
                transportations.add(transportation);
                transportation.setCargo(cargo);
                cargo.setTransportations(transportations);
            }

            if (carrier != null) {
                List<Transportation> transportations =
                        carrier.getTransportations() == null ? new ArrayList<>() : carrier.getTransportations();
                transportations.add(transportation);
                transportation.setCarrier(carrier);
                carrier.setTransportations(transportations);
            }
        }
    }

    protected void persistCargos(Collection<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            ServiceHolder.getInstance().getCargoService().add(cargo);
        }
    }

    protected void persistCarriers(Collection<Carrier> carriers) {
        for (Carrier carrier : carriers) {
            ServiceHolder.getInstance().getCarrierService().add(carrier);
        }
    }

    protected List<Transportation> getTransportationsFromParsedObject(
            List<TemporaryTransportation> transportations) {
        List<Transportation> result = new ArrayList<>();
        for (TemporaryTransportation transportation : transportations) {
            result.add(transportation.getTransportation());
        }

        return result;
    }

    protected void persistTransportations(List<Transportation> transportations) {
        for (Transportation transportation : transportations) {
            ServiceHolder.getInstance().getTransportationService().add(transportation);
        }
    }

    protected static class ParsedTransportation {
        private String cargoRef;
        private String carrierRef;
        private Transportation transportation;

        public String getCargoRef() {
            return cargoRef;
        }

        public void setCargoRef(String cargoRef) {
            this.cargoRef = cargoRef;
        }

        public String getCarrierRef() {
            return carrierRef;
        }

        public void setCarrierRef(String carrierRef) {
            this.carrierRef = carrierRef;
        }

        public Transportation getTransportation() {
            return transportation;
        }

        public void setTransportation(
                Transportation transportation) {
            this.transportation = transportation;
        }
    }

}