package hometask23.storage.initor.fileinitor.multithread;

import hometask23.cargo.domain.Cargo;
import hometask23.common.solutions.utils.FileUtils;
import hometask23.storage.initor.fileinitor.sax.OurCompanySaxHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CargoParser implements Runnable {

    private static final String FILE = "src/main/resources/hometask20/inputxml.xml";

    private volatile boolean hasError = false;

    private Map<String, Cargo> cargoMap;

    @Override
    public void run() {
        File file = null;
        try {
            file = getFileWithInitData();
            Map<String, Cargo> cargoMap = parseCargosFromFile(file);
            setCargoMap(cargoMap);
        } catch (Exception e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private Map<String, Cargo> parseCargosFromFile(File file) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        OurCompanySaxHandler saxHandler = new OurCompanySaxHandler();
        saxParser.parse(file, saxHandler);
        return saxHandler.getCargoMap();
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils
                .createFileFromResource(
                        "init-data", "lesson", FILE);
    }

    public boolean isHasError() {
        return hasError;
    }

    private synchronized void setCargoMap(Map<String, Cargo> cargoMap) {
        this.cargoMap = cargoMap;
    }

    public synchronized Map<String, Cargo> getCargoMap() {
        return this.cargoMap;
    }
}
