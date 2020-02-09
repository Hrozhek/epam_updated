package hometask23.cargo.repo.jdbc;

import hometask23.cargo.domain.Cargo;
import hometask23.cargo.domain.CargoType;
import hometask23.cargo.domain.ClothesCargo;
import hometask23.cargo.domain.FoodCargo;
import hometask23.cargo.repo.CargoRepo;
import hometask23.common.solutions.utils.DataBaseUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static hometask23.common.solutions.utils.DataBaseUtils.executeUpdate;

public class CargoDatabaseRepoImpl implements CargoRepo {
    @Override
    public List<Cargo> getByName(String name) {
        String query = "SELECT * FROM PUBLIC.CARGO WHERE NAME = ?";
        return DataBaseUtils.selectSome(query, CargoMapper::mapCargo,
                (ps -> ps.setString(1, name)));
    }

    @Override
    public void add(Cargo cargo) {
        String query = "INSERT INTO PUBLIC.CARGO VALUES (?,?,?,?,?,?,?,?)";
        executeUpdate(query, ps -> {
                    int i = 0;
                    ps.setLong(++i, cargo.getId());
                    CargoType type = cargo.getCargoType();
                    ps.setString(++i, type.name());
                    ps.setString(++i, cargo.getName());
                    ps.setDouble(++i, cargo.getWeight());
                    switch(type) {
                        case CLOTHES:
                            ClothesCargo clothes = (ClothesCargo) cargo;
                            ps.setString(++i, clothes.getSize());
                            ps.setString(++i, clothes.getMaterial());
                            ps.setNull(++i, 4);
                            ps.setNull(++i, 93);
                            break;
                        case FOOD:
                            FoodCargo food = (FoodCargo) cargo;
                            ps.setNull(++i, 12);
                            ps.setNull(++i, 12);
                            ps.setInt(++i, food.getStoreTemperature());
                            ps.setTimestamp(++i, Timestamp.valueOf(food.getExpirationDate()));
                    }
                });
    }

    @Override
    public boolean update(Cargo cargo) {
        String query = "UPDATE PUBLIC.CARGO SET CARGO_TYPE = ?," +
                "NAME = ?, WEIGHT = ?," +
                " CLOTHES_SIZE = ?, CLOTHES_MATERIAL = ?," +
                "STORE_TEMPERATURE = ?, EXPIRY_DATE = ? WHERE ID = ?";
        int affectedCargos = executeUpdate(query, ps -> {
            int i = 0;
            CargoType type = cargo.getCargoType();
            ps.setString(++i, type.name());
            ps.setString(++i, cargo.getName());
            ps.setDouble(++i, cargo.getWeight());
            switch (type) {
                case CLOTHES:
                    ClothesCargo clothes = (ClothesCargo) cargo;
                    ps.setString(++i, clothes.getSize());
                    ps.setString(++i, clothes.getMaterial());
                    ps.setNull(++i, 4);
                    ps.setNull(++i, 93);
                    break;
                case FOOD:
                    FoodCargo food = (FoodCargo) cargo;
                    ps.setNull(++i, 12);
                    ps.setNull(++i, 12);
                    ps.setInt(++i, food.getStoreTemperature());
                    Timestamp timestamp = Timestamp.valueOf(food.getExpirationDate());
                    ps.setTimestamp(++i, timestamp);
            }
            ps.setLong(++i, cargo.getId());
        });
        return affectedCargos == 1;
    }

    @Override
    public Optional<Cargo> getById(Long id) {
        String query = "SELECT * FROM PUBLIC.CARGO WHERE ID = ?";
        return DataBaseUtils.selectOne(query, CargoMapper::mapCargo,
                (ps -> ps.setLong(1, id)));
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM PUBLIC.CARGO WHERE ID = ?";
        int affectedCargos = executeUpdate(query, ps -> {
            ps.setLong(1, id);
        });
        return affectedCargos == 1;
    }

    @Override
    public List<Cargo> getAll() {
        String query = "SELECT * FROM PUBLIC.CARGO";
        return DataBaseUtils.selectAll(query, CargoMapper::mapCargo);
    }
}
