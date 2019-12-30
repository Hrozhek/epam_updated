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
            try(FileWriter writer = new FileWriter(outputFile)) {
                saveCargoes(writer);
                saveCarriers(writer);
                saveTrasnportations(writer);
            }
        } else {
            System.out.println("Please set all services before call save() method");
        }
    }

    public void saveCargoes(FileWriter writer) {
        writer.write("CargosBegin\n");
        List<Cargo> cargoes = cargoService.getAll();
        for (Cargo cargo: cargoes) {
            StringBuilder line = new StringBuilder();
            List<Object> parametres = new ArrayList<>();
            CargoType cargotype = cargo.getCargoType();
            parameters.add(cargoType);
            parameters.add(cargo.getId());
            parameters.add(cargo.getName());
            parameters.add(cargo.getWeight());
            switch(cargoType):
            case(FOOD) {
                parameters.add((FoodCargo)cargo.getExpirationDate());
                parameters.add((FoodCargo)cargo.getStoreTemperature());
                break;
            }
            case(CLOTHES) {
                parameters.add((ClothesCargo)cargo.getSize());
                parameters.add((ClothesCargo)cargo.getMaterial())
                break;
            }
            for (Object parameter: parameters) {
                if (parameter != null) {
                    line.append(parameter.toString());
                }
                line.append('|');
            }
            writer.write(line.toString() + "\n");
        }
        writer.write("CargosEnd\n");
    }
    public void saveCarriers(FileWriter writer) {
        List<Carrier> carriers = carrierService.getAll();
    }
    public void saveTrasnportations(FileWriter writer) {
        List<Transportation> transportations = transportationService.getAll();
    }
}
