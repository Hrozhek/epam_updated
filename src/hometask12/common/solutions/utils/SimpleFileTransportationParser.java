package hometask12.common.solutions.utils;

import hometask12.common.solutions.files.TemporaryTransportation;
import hometask12.storage.initor.FromFileStorageInitor;
import hometask12.transportation.domain.Transportation;

import java.util.Date;
import java.util.List;

public class SimpleFileTransportationParser {
    private final static Character SEPARATOR = '|';
    private final static short INDEX_OF_CARGO_ID = 0;
    private final static short INDEX_OF_CARRIER_ID = 1;
    private final static short INDEX_OF_DESCRIPTION = 2;
    private final static short INDEX_OF_BILL_TO = 3;
    private final static short INDEX_OF_BEGIN_DATE = 4;

    private static String transportationType;

    public static void parse(String fileLine, List<TemporaryTransportation> transportationList) {
        TemporaryTransportation temporaryTransportation = new TemporaryTransportation();
        Transportation transportationWrapped = new Transportation();
        List<String> fields = ParseBySeparator.getSeparated(fileLine, SEPARATOR);
        transportationWrapped.setDescription(fields.get(INDEX_OF_DESCRIPTION));
        transportationWrapped.setBillTo(fields.get(INDEX_OF_BILL_TO));
        transportationWrapped.setTransportationBeginDate(new Date(Integer.parseInt(fields.get(INDEX_OF_BEGIN_DATE))));
        temporaryTransportation.setCargoId(fields.get(INDEX_OF_CARGO_ID));
        temporaryTransportation.setCarrierId(fields.get(INDEX_OF_CARRIER_ID));
        temporaryTransportation.setTransportation(transportationWrapped);
        transportationList.add(temporaryTransportation);
    }
}
