package hometask12.common.business.files;

import hometask13.common.business.files.TemporaryTransportation;
import hometask13.common.solutions.utils.ParseBySeparator;
import hometask13.transportation.domain.Transportation;

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

    public static void parse(String fileLine, List<hometask13.common.business.files.TemporaryTransportation> transportationList) {
        hometask13.common.business.files.TemporaryTransportation temporaryTransportation = new TemporaryTransportation();
        Transportation transportationWrapped = new Transportation();
        List<String> fields = ParseBySeparator.getSeparated(fileLine, SEPARATOR);

        String description = fields.get(INDEX_OF_DESCRIPTION);
        String billTo = fields.get(INDEX_OF_BILL_TO);
        String beginDate = fields.get(INDEX_OF_BEGIN_DATE);

        if (!description.isEmpty()) {
            transportationWrapped.setDescription(description);
        }
        if (!billTo.isEmpty()) {
            transportationWrapped.setBillTo(billTo);
        }
        if (!beginDate.isEmpty()) {
            transportationWrapped.setTransportationBeginDate(new Date(Long.parseLong(beginDate)));
        }

        temporaryTransportation.setCargoId(fields.get(INDEX_OF_CARGO_ID));
        temporaryTransportation.setCarrierId(fields.get(INDEX_OF_CARRIER_ID));
        temporaryTransportation.setTransportation(transportationWrapped);
        transportationList.add(temporaryTransportation);
    }
}