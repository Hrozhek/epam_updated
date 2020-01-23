package hometask15.storage.initor.fileinitor.multithread;

import hometask15.carrier.domain.Carrier;
import hometask15.common.solutions.utils.FileUtils;
import hometask15.storage.initor.fileinitor.sax.OurCompanySaxHandler;
import hometask15.storage.initor.fileinitor.sax.XmlSaxFileDataInitor;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CarrierParser implements Runnable{

    private static final String FILE = "src/main/resources/hometask13/inputxml.xml";

    private volatile boolean hasError = false;

    private Map<String, Carrier> carrierMap;

    @Override
    public void run() {
        File file = null;
        try {
            file = getFileWithInitData();
            Map<String, Carrier> carrierMap = parseCarriersFromFile(file);
            setCarrierMap(carrierMap);
        } catch (Exception e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private Map<String, Carrier> parseCarriersFromFile(File file) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        OurCompanySaxHandler saxHandler = new OurCompanySaxHandler();
        saxParser.parse(file, saxHandler);
        return saxHandler.getCarrierMap();
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils
                .createFileFromResource(
                         "init-data", "lesson", FILE);
    }

    public boolean isHasError() {
        return hasError;
    }

    private synchronized void setCarrierMap(Map<String, Carrier> carrierMap) {
        this.carrierMap = carrierMap;
    }

    public synchronized Map<String, Carrier> getCarrierMap() {
        return this.carrierMap;
    }
}
