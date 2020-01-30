package hometask20.storage.initor.fileinitor;

import hometask20.cargo.domain.Cargo;
import hometask20.cargo.domain.CargoType;
import hometask20.cargo.domain.ClothesCargo;
import hometask20.cargo.domain.FoodCargo;
import hometask20.carrier.domain.Carrier;
import hometask20.carrier.domain.CarrierType;
import hometask20.common.business.exception.checked.InitStorageException;
import hometask20.common.business.files.TemporaryTransportation;
import hometask20.common.solutions.utils.FileUtils;
import hometask20.common.solutions.utils.xml.dom.XmlDomUtils;
import hometask20.transportation.domain.Transportation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

import static hometask20.common.solutions.utils.DateTimeUtils.getDateFormatter;
import static hometask20.common.solutions.utils.xml.dom.XmlDomUtils.getOnlyElement;
import static hometask20.common.solutions.utils.xml.dom.XmlDomUtils.getOnlyElementTextContent;

public class XmlDomFileDataInitor extends BaseFileInitor {

    private static final String FILE = "src/main/resources/hometask20/inputxml.xml";


    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();
            Document document = XmlDomUtils.getDocument(file);
            Map<String, Cargo> cargoMap = parseCargos(document);
            Map<String, Carrier> carrierMap = parseCarriers(document);
            List<TemporaryTransportation> transportations = parseTransportationsData(document);
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
        return FileUtils.createFileFromResource("initdata", "hometask20", FILE);
    }

    private Map<String, Cargo> parseCargos(Document doc) throws ParseException {
        Map<String, Cargo> cargos = new LinkedHashMap<>();

        Element root = getOnlyElement(doc, "Cargos");
        NodeList xmlCargos = root.getElementsByTagName("Cargo");

        for (int i = 0; i < xmlCargos.getLength(); i++) {
            Map.Entry<String, Cargo> parsedData = parseCargo(xmlCargos.item(i));
            cargos.put(parsedData.getKey(), parsedData.getValue());
        }

        return cargos;
    }

    private Map.Entry<String, Cargo> parseCargo(Node cargoXml) throws ParseException {
        Element cargoElem = ((Element) cargoXml);

        String id = getOnlyElementTextContent(cargoElem,"id");
        CargoType cargoType = CargoType.valueOf(cargoElem.getAttribute("type"));

        Cargo cargo;
        if (CargoType.FOOD.equals(cargoType)) {
            FoodCargo foodCargo = new FoodCargo();
            LocalDateTime expirationDate = LocalDateTime.parse(getOnlyElementTextContent(cargoElem, "expirationDate"),
                    getDateFormatter());
            foodCargo.setExpirationDate(expirationDate);
            foodCargo.setStoreTemperature(
                    Integer.parseInt(getOnlyElementTextContent(cargoElem, "storeTemperature")));
            cargo = foodCargo;
        } else {
            ClothesCargo clothesCargo = new ClothesCargo();
            clothesCargo.setMaterial(getOnlyElementTextContent(cargoElem, "material"));
            clothesCargo.setSize(getOnlyElementTextContent(cargoElem, "size"));
            cargo = clothesCargo;
        }

        cargo.setName(getOnlyElementTextContent(cargoElem, "name"));
        cargo.setWeight(Integer.parseInt(getOnlyElementTextContent(cargoElem, "weight")));

        return new AbstractMap.SimpleEntry<>(id, cargo);
    }

    private Map<String, Carrier> parseCarriers(Document doc) throws ParseException {
        Map<String, Carrier> carriers = new LinkedHashMap<>();

        Element root = getOnlyElement(doc, "Carriers");
        NodeList xmlCarriers = root.getElementsByTagName("Carrier");

        for (int i = 0; i < xmlCarriers.getLength(); i++) {
            Map.Entry<String, Carrier> parsedData = parseCarrier(xmlCarriers.item(i));
            carriers.put(parsedData.getKey(), parsedData.getValue());
        }

        return carriers;
    }

    private Map.Entry<String, Carrier> parseCarrier(Node cargoXml) {
        Element carrierElement = ((Element) cargoXml);

        String id = getOnlyElementTextContent(carrierElement, "id");
        Carrier carrier = new Carrier();

        carrier.setName(getOnlyElementTextContent(carrierElement, "name"));
        carrier.setAddress(getOnlyElementTextContent(carrierElement, "address"));
        String carrierTypeStr = carrierElement.getAttribute("type");
        carrier.setCarrierType(CarrierType.valueOf(carrierTypeStr));

        return new AbstractMap.SimpleEntry<>(id, carrier);
    }

    private List<TemporaryTransportation> parseTransportationsData(Document doc) throws ParseException {
        List<TemporaryTransportation> result = new ArrayList<>();

        Element root = getOnlyElement(doc, "Transportations");
        NodeList xmlTransportations = root.getElementsByTagName("Transportation");

        for (int i = 0; i < xmlTransportations.getLength(); i++) {
            TemporaryTransportation parsedData = parseTransportationData(xmlTransportations.item(i));
            result.add(parsedData);
        }

        return result;
    }

    private TemporaryTransportation parseTransportationData(Node transportationXml)
            throws ParseException {
        Element transportationElement = ((Element) transportationXml);

        TemporaryTransportation result = new TemporaryTransportation();
        String cargoRef = getOnlyElementTextContent(transportationElement,"cargoId");
        result.setCargoId(cargoRef);
        String carrierRef = getOnlyElementTextContent(transportationElement,"carrierId");
        result.setCarrierId(carrierRef);

        Transportation transportation = new Transportation();
        transportation.setBillTo(getOnlyElementTextContent(transportationElement, "billTo"));
        transportation.setDescription(getOnlyElementTextContent(transportationElement, "description"));
        String beginDataStr = getOnlyElementTextContent(transportationElement, "beginDate");
        transportation.setTransportationBeginDate(LocalDateTime.parse(beginDataStr, getDateFormatter()));
        result.setTransportation(transportation);
        return result;
    }

}