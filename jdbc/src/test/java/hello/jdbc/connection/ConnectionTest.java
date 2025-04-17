package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        // 커넥션을 가져올 떄마다 URL, USERNAME, PASSWORD 파라미터를 계속 전달해야함
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceDriverManager() throws SQLException {
        // DriverManagerDataSource - 항상 새로운 커넥션 획득 -> 인터페이스를 통해 커넥션 획득
        // 처음 커넥션 획득할 때만 URL, USERNAME, PASSWORD 선언해주면 됨 -> 설정과 사용을 분리할 수 있음(변경에 유연한 대처 가능)
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // connection pooling
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);

        Thread.sleep(1000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection(); // URL, USERNAME, PASSWORD 를 안받아도 됨.
        Connection con2 = dataSource.getConnection();
//        Connection con3 = dataSource.getConnection();
//        Connection con4 = dataSource.getConnection();
//        Connection con5 = dataSource.getConnection();
//        Connection con6 = dataSource.getConnection();
//        Connection con7 = dataSource.getConnection();
//        Connection con8 = dataSource.getConnection();
//        Connection con9 = dataSource.getConnection();
//        Connection con10 = dataSource.getConnection();
//        Connection con11 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }
}
