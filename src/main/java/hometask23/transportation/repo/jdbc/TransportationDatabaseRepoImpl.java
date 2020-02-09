package hometask23.transportation.repo.jdbc;

import hometask23.common.solutions.utils.DataBaseUtils;
import hometask23.transportation.domain.Transportation;
import hometask23.transportation.repo.TransportationRepo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static hometask23.common.solutions.utils.DataBaseUtils.executeUpdate;

public class TransportationDatabaseRepoImpl implements TransportationRepo {
    @Override
    public void add(Transportation transportation) {
        String query = "INSERT INTO PUBLIC.TRANSPORTATIONS VALUES (?,?,?,?,?,?)";
        executeUpdate(query, ps -> {
            int i = 0;
            ps.setLong(++i, transportation.getId());
            ps.setLong(++i, transportation.getCargo().getId());
            ps.setLong(++i, transportation.getCarrier().getId());
            ps.setString(++i, transportation.getDescription());
            ps.setString(++i, transportation.getBillTo());
            Timestamp timestamp = Timestamp.valueOf(transportation.getTransportationBeginDate());
            ps.setTimestamp(++i, timestamp);
        });
    }

    @Override
    public boolean update(Transportation transportation) {
        String query = "UPDATE PUBLIC.TRANSPORTATIONS SET CARGO_REF = ?, " +
                "CARRIER_REF = ?, DESCRIPTION = ?, BILL_TO = ?, BEGIN_DATE = ? WHERE ID = ?";
        int affectedTransportations = executeUpdate(query, ps -> {
            int i = 0;
            ps.setLong(++i, transportation.getCargo().getId());
            ps.setLong(++i, transportation.getCarrier().getId());
            ps.setString(++i, transportation.getDescription());
            ps.setString(++i, transportation.getBillTo());
            Timestamp timestamp = Timestamp.valueOf(transportation.getTransportationBeginDate());
            ps.setTimestamp(++i, timestamp);
            ps.setLong(++i, transportation.getId());
        });
        return affectedTransportations == 1;
    }

    @Override
    public Optional<Transportation> getById(Long id) {
        String query = "SELECT * FROM PUBLIC.TRANSPORTATIONS WHERE ID = ?";
        return DataBaseUtils.selectOne(query, TransportationMapper::mapTransportation,
                (ps -> ps.setLong(1, id)));
    }

    @Override
    public boolean deleteById(Long id) {
        String query = "DELETE FROM PUBLIC.TRANSPORTATIONS WHERE ID = ?";
        int affectedTransportations = executeUpdate(query, ps -> {
            ps.setLong(1, id);
        });
        return affectedTransportations == 1;
    }

    @Override
    public List<Transportation> getAll() {
        String query = "SELECT * FROM PUBLIC.TRANSPORTATIONS";
        return DataBaseUtils.selectAll(query, TransportationMapper::mapTransportation);
    }
}
