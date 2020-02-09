package hometask23.carrier.repo.jdbc;

import hometask23.carrier.domain.Carrier;
import hometask23.carrier.repo.CarrierRepo;
import hometask23.common.solutions.utils.DataBaseUtils;

import java.util.List;
import java.util.Optional;

import static hometask23.common.solutions.utils.DataBaseUtils.executeUpdate;

public class CarrierDatabaseRepoImpl implements CarrierRepo {
    @Override
    public List<Carrier> getByName(String name) {
        String query = "SELECT * FROM PUBLIC.CARRIER WHERE NAME = ?";
        return DataBaseUtils.selectSome(query, CarrierMapper::mapCarrier,
                (ps -> ps.setString(1, name)));
    }

    @Override
    public void add(Carrier carrier) {
        String query = "INSERT INTO PUBLIC.CARRIER VALUES(?,?,?,?)";
        executeUpdate(query, ps -> {
            int i = 0;
            ps.setLong(++i, carrier.getId());
            ps.setString(++i, carrier.getCarrierType().toString());
            ps.setString(++i, carrier.getName());
            ps.setString(++i, carrier.getAddress());
        });
    }

    @Override
    public boolean update(Carrier carrier) {
        String query = "UPDATE PUBLIC.CARRIER SET CARRIER_TYPE = ?, " +
                "NAME = ?, ADDRESS = ? WHERE ID = ?";
        int affectedCarriers = executeUpdate(query, ps -> {
            int i = 0;
            ps.setString(++i, carrier.getCarrierType().toString());
            ps.setString(++i, carrier.getName());
            ps.setString(++i, carrier.getAddress());
            ps.setLong(++i, carrier.getId());
        });
        return affectedCarriers == 1;
    }

    @Override
    public Optional<Carrier> getById(Long id) {
        String query = "SELECT * FROM PUBLIC.CARRIER WHERE ID = ?";
        return DataBaseUtils.selectOne(query, CarrierMapper::mapCarrier,
                (ps -> ps.setLong(1, id)));
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM PUBLIC.CARRIER WHERE ID = ?";
        int affectedCarriers = executeUpdate(query, ps -> {
            ps.setLong(1, id);
        });
        return affectedCarriers == 1;
    }

    @Override
    public List<Carrier> getAll() {
        String query = "SELECT * FROM PUBLIC.CARRIER";
        return DataBaseUtils.selectAll(query, CarrierMapper::mapCarrier);
    }
}
