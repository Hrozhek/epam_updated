package hometask12.common.business.files;

import hometask12.cargo.domain.Cargo;
import hometask12.cargo.domain.CargoType;
import hometask12.cargo.domain.ClothesCargo;
import hometask12.cargo.domain.FoodCargo;
import hometask12.cargo.service.CargoService;
import hometask12.carrier.domain.Carrier;
import hometask12.carrier.domain.CarrierType;
import hometask12.carrier.service.CarrierService;
import hometask12.transportation.domain.Transportation;
import hometask12.transportation.service.TransportationService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            try (FileWriter writer = new FileWriter(outputFile)) {
                saveCargoes(writer);
                saveCarriers(writer);
                saveTrasnportations(writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Please set all services before call save() method");
        }
    }

    public void saveCargoes(FileWriter writer) throws IOException {
        writer.write("CargosBegin\n");
        List<Cargo> cargoes = cargoService.getAll();
        for (Cargo cargo : cargoes) {
            StringBuilder line = new StringBuilder();
            List<Object> parameters = new ArrayList<>();
            CargoType cargoType = cargo.getCargoType();
            parameters.add(cargoType);
            parameters.add(cargo.getId());
            parameters.add(cargo.getName());
            parameters.add(cargo.getWeight());
            switch (cargoType) {
                case FOOD:
                    FoodCargo foodCargo = (FoodCargo) cargo;
                    parameters.add(foodCargo.getExpirationDate().getTime());
                    parameters.add(foodCargo.getStoreTemperature());
                    break;
                case CLOTHES:
                    ClothesCargo clothesCargo = (ClothesCargo) cargo;
                    parameters.add(clothesCargo.getSize());
                    parameters.add(clothesCargo.getMaterial());
                    break;
            }
            for (Object parameter : parameters) {
                if (parameter != null) {
                    line.append(parameter.toString());
                }
                line.append('|');
            }
            int DeleteLastChar = line.length() - 1;
            String answer = line.substring(0, DeleteLastChar);
            writer.write(answer + "\n");
        }
        writer.write("CargosEnd\n");
    }

    public void saveCarriers(FileWriter writer) throws IOException {
        writer.write("CarriersBegin\n");
        List<Carrier> carrieres = carrierService.getAll();
        for (Carrier carrier : carrieres) {
            StringBuilder line = new StringBuilder();
            List<Object> parameters = new ArrayList<>();
            CarrierType carrierType = carrier.getCarrierType();
            parameters.add(carrierType);
            parameters.add(carrier.getId());
            parameters.add(carrier.getName());
            parameters.add(carrier.getAddress());
            for (Object parameter : parameters) {
                if (parameter != null) {
                    line.append(parameter.toString());
                }
                line.append('|');
            }
            int DeleteLastChar = line.length() - 1;
            String answer = line.substring(0, DeleteLastChar);
            writer.write(answer + "\n");
        }
        writer.write("CarriersEnd\n");
    }

    public void saveTrasnportations(FileWriter writer) throws IOException {
        writer.write("TransportationsBegin\n");
        List<Transportation> transportations = transportationService.getAll();
        for (Transportation transportation : transportations) {
            StringBuilder line = new StringBuilder();
            List<Object> parameters = new ArrayList<>();
            parameters.add(transportation.getCargo().getId());
            parameters.add(transportation.getCarrier().getId());
            parameters.add(transportation.getDescription());
            parameters.add(transportation.getBillTo());
            parameters.add(transportation.getTransportationBeginDate().getTime());

            for (Object parameter : parameters) {
                if (parameter != null) {
                    line.append(parameter.toString());
                }
                line.append('|');
            }
            int DeleteLastChar = line.length() - 1;
            String answer = line.substring(0, DeleteLastChar);
            writer.write(answer + "\n");
        }
        writer.write("TransportationsEnd\n");

    }
}
