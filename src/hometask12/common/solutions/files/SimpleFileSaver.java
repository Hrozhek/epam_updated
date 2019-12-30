package hometask12.common.solutions.files;

import hometask12.cargo.domain.Cargo;
import hometask12.cargo.service.CargoService;
import hometask12.carrier.domain.Carrier;
import hometask12.carrier.service.CarrierService;
import hometask12.transportation.domain.Transportation;
import hometask12.transportation.service.TransportationService;

import java.io.File;
import java.util.List;

public class SimpleFileSaver {
    CargoService cargoService;
    CarrierService carrierService;
    TransportationService transportationService;

    File outputFile = new File("resources/hometask12/outfile.txt");

    public void setCargoService(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    public void setCarrierService(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    public void setTransportationService(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public void save() {
        if (cargoService != null && carrierService != null && transportationService != null) {
            saveCargoes();
            saveCarriers();
            saveTrasnportations();
        } else {
            System.out.println("Please set all services before call save() method");
        }
    }

    public void saveCargoes() {
        //write CargosBegin
        List<Cargo> cargoes = cargoService.getAll();
        // for each make a line and then write
        //write cargo end
    }
    public void saveCarriers() {
        List<Carrier> carriers = carrierService.getAll();
    }
    public void saveTrasnportations() {
        List<Transportation> transportations = transportationService.getAll();
    }
}
