package hometask23.common.solutions.utils;

import hometask23.common.solutions.database.JdbcConsumer;
import hometask23.common.solutions.database.JdbcFunction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static hometask23.common.solutions.database.DataSource.getConnection;

public final class DataBaseUtils {
    private DataBaseUtils() {

    }

    public static int executeUpdate(String sql,
                                    JdbcConsumer<PreparedStatement> psConsumer) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
        ) {
            psConsumer.accept(ps);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectAll(String sql,
                                        JdbcFunction<ResultSet, T> rsConverter) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
            return commonSelect(rsConverter, ps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> selectSome(String sql,
                                         JdbcFunction<ResultSet, T> rsConverter,
                                         JdbcConsumer<PreparedStatement> statementSetter) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
            statementSetter.accept(ps);
            return commonSelect(rsConverter, ps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Optional<T> selectOne(String sql,
                                            JdbcFunction<ResultSet, T> rsConverter,
                                            JdbcConsumer<PreparedStatement> statementSetter) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);) {
            statementSetter.accept(ps);
            List<T> answer = commonSelect(rsConverter, ps);
            if (answer.isEmpty()) {
                return Optional.empty();
            } else {
                int elementPosition = 0;
                return Optional.of(answer.get(elementPosition));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> List<T> commonSelect(JdbcFunction<ResultSet, T> rsConverter,
                                            PreparedStatement ps) {
        try {
            ResultSet resultSet = ps.executeQuery();
            List<T> result = new ArrayList<>();
            while (resultSet.next()) {
                T t = rsConverter.apply(resultSet);
                result.add(t);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
