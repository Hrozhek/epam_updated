package hometask14.storage.initor.fileinitor.sax;

import hometask14.cargo.domain.Cargo;
import hometask14.carrier.domain.Carrier;
import hometask14.common.business.exception.checked.InitStorageException;
import hometask14.common.business.files.TemporaryTransportation;
import hometask14.common.solutions.utils.FileUtils;
import hometask14.storage.initor.fileinitor.BaseFileInitor;
import hometask14.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class XmlSaxFileDataInitor extends BaseFileInitor {

    private static final String FILE = "src/main/resources/hometask13/inputxml.xml";


    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            OurCompanySaxHandler handler = new OurCompanySaxHandler();
            saxParser.parse(file, handler);
            Map<String, Cargo> cargoMap = handler.getCargoMap();
            Map<String, Carrier> carrierMap = handler.getCarrierMap();
            List<TemporaryTransportation> transportations = handler.getTemporaryTransportation();
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);
            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("initdata", "hometask14", FILE);
    }

}
