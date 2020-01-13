package hometask13.storage.initor.fileinitor;

import hometask13.application.serviceholder.ServiceHolder;
import hometask13.cargo.domain.Cargo;
import hometask13.cargo.service.CargoService;
import hometask13.carrier.domain.Carrier;
import hometask13.carrier.service.CarrierService;
import hometask13.common.business.files.TemporaryTransportation;
import hometask13.common.business.files.SimpleFileCargoParser;
import hometask13.common.business.files.SimpleFileCarrierParser;
import hometask13.common.business.files.SimpleFileTransportationParser;
import hometask13.storage.initor.StorageInitor;
import hometask13.transportation.domain.Transportation;
import hometask13.transportation.service.TransportationService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextFileDataInitor implements StorageInitor {

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    private static final String whereCargosBegin = "CargosBegin";
    private static final String whereCargosEnd = "CargosEnd";
    private static final String whereCarriersBegin = "CarriersBegin";
    private static final String whereCarriersEnd = "CarriersEnd";
    private static final String whereTransportationsBegin = "TransportationsBegin";
    private static final String whereTransportationsEnd = "TransportationsEnd";

    public File inputFile = new File("resources/hometask12/inputfile.txt");

    public TextFileDataInitor() {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public void initStorage() {
        Map<String, Cargo> cargosMap = readCargos();
        Map<String, Carrier> carriersMap = readCarriers();
        List<TemporaryTransportation> transportationList = readTransportations();
        for (Map.Entry<String, Cargo> entry : cargosMap.entrySet()) {
            cargoService.add(entry.getValue());
        }
        for (Map.Entry<String, Carrier> entry : carriersMap.entrySet()) {
            carrierService.add(entry.getValue());
        }
        for (TemporaryTransportation temporaryTransportation : transportationList) {
            String cargoId = temporaryTransportation.getCargoId();
            String carrierId = temporaryTransportation.getCarrierId();
            Transportation transportation = temporaryTransportation.getTransportation();
            transportation.setCargo(cargosMap.get(cargoId));
            transportation.setCarrier(carriersMap.get(carrierId));
            transportationService.add(transportation);
        }
    }

    public Map<String, Cargo> readCargos() {
        Map<String, Cargo> cargoMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null && !(line.equals(whereCargosBegin))) {
                line = reader.readLine();
            }
            while (line != null && !(line.equals(whereCargosEnd))) {
                line = reader.readLine();
                if (!(line.equals(whereCargosEnd))) {
                    SimpleFileCargoParser.parse(line, cargoMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargoMap;
    }

    public Map<String, Carrier> readCarriers() {
        Map<String, Carrier> carrierMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null && !(line.equals(whereCarriersBegin))) {
                line = reader.readLine();
            }
            while (line != null && !(line.equals(whereCarriersEnd))) {
                line = reader.readLine();
                if (!(line.equals(whereCarriersEnd))) {
                    SimpleFileCarrierParser.parse(line, carrierMap);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carrierMap;
    }

    public List<TemporaryTransportation> readTransportations() {
        List<TemporaryTransportation> transportationsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null && !(line.equals(whereTransportationsBegin))) {
                line = reader.readLine();
            }
            while (line != null && !(line.equals(whereTransportationsEnd))) {
                line = reader.readLine();
                if (!(line.equals(whereTransportationsEnd))) {
                    SimpleFileTransportationParser.parse(line, transportationsList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transportationsList;
    }

}

