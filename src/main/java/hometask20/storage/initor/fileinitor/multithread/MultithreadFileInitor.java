package hometask20.storage.initor.fileinitor.multithread;

import hometask15.application.serviceholder.StorageType;
import hometask20.cargo.domain.Cargo;
import hometask20.carrier.domain.Carrier;
import hometask20.common.business.exception.checked.InitStorageException;
import hometask20.common.business.files.TemporaryTransportation;
import hometask20.storage.initor.fileinitor.BaseFileInitor;
import hometask20.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MultithreadFileInitor extends BaseFileInitor {
    @Override
    public void initStorage() throws InitStorageException {
        try {
            CargoParser cargoParser = new CargoParser();
            CarrierParser carrierParser = new CarrierParser();
            TransportationParser transportationParser = new TransportationParser();

            List<Thread> parsers = Arrays.asList(
                    new Thread(cargoParser),
                    new Thread(carrierParser),
                    new Thread(transportationParser)
            );

            startParseWithThreads(parsers);
            waitAllParserHaveFinished(parsers);

            boolean hasErrorWhileParseFile =
                    cargoParser.isHasError() && carrierParser.isHasError() && transportationParser
                            .isHasError();

            if (hasErrorWhileParseFile) {
                throw new Exception("Error while parse data!");
            }

            Map<String, Cargo> cargoMap = cargoParser.getCargoMap();
            Map<String, Carrier> carrierMap = carrierParser.getCarrierMap();
            List<TemporaryTransportation> transportations = transportationParser.getTransportations();
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);


        } catch (Exception e) {
            throw new InitStorageException(e.getMessage());
        }
    }

    private void startParseWithThreads(List<Thread> threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private void waitAllParserHaveFinished(List<Thread> threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
