package hometask23.common.solutions.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static final String URL = "jdbc:h2:D:\\databases\\test.txt";
    private static final String USER_NAME = "Nikita";
    private static final String PASS = "hrozhek";


    static {
        config.setJdbcUrl(URL);
        config.setUsername(USER_NAME);
        config.setPassword(PASS);
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
