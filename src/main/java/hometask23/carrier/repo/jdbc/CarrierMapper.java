package hometask23.carrier.repo.jdbc;

import hometask23.carrier.domain.Carrier;
import hometask23.carrier.domain.CarrierType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarrierMapper {
    private CarrierMapper() {
    }

    public static Carrier mapCarrier(ResultSet rs) {
        try {
            Carrier carrier = new Carrier();
            carrier.setId(rs.getLong("ID"));
            carrier.setName(rs.getString("NAME"));
            carrier.setCarrierType(CarrierType.valueOf(rs.getString("CARRIER_TYPE")));
            carrier.setAddress(rs.getString("ADDRESS"));
            return carrier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
