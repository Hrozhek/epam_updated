package hometask20.storage.initor.fileinitor.sax;

import hometask20.cargo.domain.Cargo;
import hometask20.cargo.domain.ClothesCargo;
import hometask20.cargo.domain.FoodCargo;
import hometask20.carrier.domain.Carrier;
import hometask20.carrier.domain.CarrierType;
import hometask20.common.business.files.TemporaryTransportation;
import hometask20.transportation.domain.Transportation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.*;

import static hometask20.common.solutions.utils.DateTimeUtils.getDateFormatter;

public class OurCompanySaxHandler extends DefaultHandler {
    private Map<String, Cargo> cargoMap;
    private Map<String, Carrier> carrierMap;
    private List<TemporaryTransportation> temporaryTransportations;
    private Cargo curCargo;
    private Carrier curCarrier;
    private TemporaryTransportation tempTransportation;
    private Transportation transportation;
    private String id;

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder.setLength(0);
        switch (qName) {
            case "Cargos": {
                cargoMap = new HashMap<>();
                break;
            }
            case "Cargo": {
                String type = attributes.getValue("type");
                switch (type) {
                    case "FOOD": {
                        curCargo = new FoodCargo();
                        break;
                    }
                    case "CLOTHES": {
                        curCargo = new ClothesCargo();
                        break;
                    }
                }
                break;
            }
            case "Carriers": {
                carrierMap = new HashMap<>();
                break;
            }
            case "Carrier": {
                curCarrier = new Carrier();
                String type = attributes.getValue("type");
                switch (type) {
                    case "TRAIN": {
                        curCarrier.setCarrierType(CarrierType.TRAIN);
                        break;
                    }
                    case "CAR": {
                        curCarrier.setCarrierType(CarrierType.CAR);
                        break;
                    }
                    case "PLANE": {
                        curCarrier.setCarrierType(CarrierType.PLANE);
                        break;
                    }
                }
                break;
            }
            case "Transportations": {
                temporaryTransportations = new ArrayList<>();
                break;
            }
            case "Transportation": {
                transportation = new Transportation();
                tempTransportation = new TemporaryTransportation();
                tempTransportation.setTransportation(transportation);
                break;
            }

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String data = stringBuilder.toString();
        switch (qName) {
            case "id": {
                if (curCargo != null || curCarrier != null) {
                    id = data;
                }
                break;
            }
            case "name": {
                if (curCarrier != null) {
                    curCarrier.setName(data);
                } else if (curCargo != null){
                    curCargo.setName(data);
                }
                break;
            }
            case "weight": {
                curCargo.setWeight(Integer.parseInt(data));
                break;
            }
            case "size": {
                ClothesCargo cargo = (ClothesCargo) curCargo;
                cargo.setSize(data);
                curCargo = cargo;
                break;
            }
            case "material": {
                ClothesCargo cargo = (ClothesCargo) curCargo;
                cargo.setMaterial(data);
                curCargo = cargo;
                break;
            }
            case "expirationDate": {
                FoodCargo cargo = (FoodCargo) curCargo;
                cargo.setExpirationDate(LocalDateTime.parse(data, getDateFormatter()));
                curCargo = cargo;
                break;
            }
            case "storeTemperature": {
                FoodCargo cargo = (FoodCargo) curCargo;
                cargo.setStoreTemperature(Integer.parseInt(data));
                curCargo = cargo;
                break;
            }
            case "address": {
                curCarrier.setAddress(data);
                break;
            }
            case "cargoId": {
                tempTransportation.setCargoId(data);
                break;
            }
            case "carrierId": {
                tempTransportation.setCarrierId(data);
                break;
            }
            case "description": {
                transportation.setDescription(data);
                break;
            }
            case "billTo": {
                transportation.setBillTo(data);
                break;
            }
            case "beginDate": {
                transportation.setTransportationBeginDate(LocalDateTime.parse(data, getDateFormatter()));
            }
            case "Cargo": {
                cargoMap.put(id, curCargo);
                curCargo = null;
                break;
            }
            case "Carrier": {
                carrierMap.put(id, curCarrier);
                curCarrier = null;
                break;
            }
            case "Transportation": {
                //tempTransportation.setTransportation(transportation);
                temporaryTransportations.add(tempTransportation);
                break;
            }
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        String data = new String(chars, start, length);
        stringBuilder.append(data);
    }

    public Map<String, Cargo> getCargoMap() {
        return cargoMap;
    }

    public Map<String, Carrier> getCarrierMap() {
        return carrierMap;
    }

    public List<TemporaryTransportation> getTemporaryTransportation() {
        return temporaryTransportations;
    }
}
