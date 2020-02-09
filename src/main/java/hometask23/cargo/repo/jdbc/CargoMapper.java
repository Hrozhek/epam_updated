package hometask23.cargo.repo.jdbc;

import hometask23.cargo.domain.Cargo;
import hometask23.cargo.domain.CargoType;
import hometask23.cargo.domain.ClothesCargo;
import hometask23.cargo.domain.FoodCargo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public final class CargoMapper {
    private CargoMapper() {
    }

    public static Cargo mapCargo(ResultSet rs) {
        try {
            Cargo cargo = null;
            CargoType type = CargoType.valueOf(rs.getString("CARGO_TYPE"));
            if (type == CargoType.CLOTHES) {
                ClothesCargo temp = new ClothesCargo();
                temp.setMaterial(rs.getString("CLOTHES_MATERIAL"));
                temp.setSize(rs.getString("CLOTHES_SIZE"));
                cargo = temp;
            } else {
                FoodCargo temp = new FoodCargo();
                temp.setStoreTemperature(rs.getInt("STORE_TEMPERATURE"));
                Timestamp expiryDate = rs.getTimestamp("EXPIRY_DATE");
                temp.setExpirationDate(expiryDate.toLocalDateTime());
                cargo = temp;
            }
            cargo.setId(rs.getLong("ID"));
            cargo.setName(rs.getString("NAME"));
            cargo.setWeight(rs.getInt("WEIGHT"));
            return cargo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}