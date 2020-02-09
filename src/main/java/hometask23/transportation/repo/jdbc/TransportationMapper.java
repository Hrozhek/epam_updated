package hometask23.transportation.repo.jdbc;

import hometask23.transportation.domain.Transportation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TransportationMapper {
    private TransportationMapper(){}

    public static Transportation mapTransportation(ResultSet rs){
        try {
            Transportation transportation = new Transportation();
            transportation.setId(rs.getLong("ID"));
            //resolveCaroRef(rs.getLong("CARGO_REF"));
            //resolveCarrierRef(rs.getLong("CARRIER_REF"));
            /* Here must be difficult logic which will test if cargo and carrier with such id
            are present in storage an set references to them. If they not, method should execute JOIN and add
            cargo and carrier to the repo
             */
            transportation.setDescription(rs.getString("DESCRIPTION"));
            transportation.setBillTo(rs.getString("BILL_TO"));
            Timestamp beginDate = rs.getTimestamp("BEGIN_DATE");
            transportation.setTransportationBeginDate(beginDate.toLocalDateTime());
            return transportation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
