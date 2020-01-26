package hometask18.storage.initor.fileinitor.multithread;

import hometask18.common.business.files.TemporaryTransportation;
import hometask18.common.solutions.utils.FileUtils;
import hometask18.storage.initor.fileinitor.sax.OurCompanySaxHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransportationParser implements Runnable {

    private static final String FILE = "src/main/resources/hometask13/inputxml.xml";

    private volatile boolean hasError = false;

    private List<TemporaryTransportation> transportations;

    @Override
    public void run() {
        File file = null;
        try {
            file = getFileWithInitData();
            List<TemporaryTransportation> transportations = parseCargosFromFile(file);
            setTransportations(transportations);
        } catch (Exception e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private List<TemporaryTransportation> parseCargosFromFile(File file) throws Exception {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        OurCompanySaxHandler saxHandler = new OurCompanySaxHandler();
        saxParser.parse(file, saxHandler);
        return saxHandler.getTemporaryTransportation();
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils
                .createFileFromResource("init-data", "lesson", FILE);
    }

    public boolean isHasError() {
        return hasError;
    }

    private synchronized void setTransportations(List<TemporaryTransportation> transportations) {
        this.transportations = transportations;
    }

    public synchronized List<TemporaryTransportation> getTransportations() {
        return this.transportations;
    }
}
