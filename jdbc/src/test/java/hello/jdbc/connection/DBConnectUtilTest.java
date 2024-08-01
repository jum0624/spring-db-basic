package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DBConnectUtilTest {

    @Test
    public void connection() throws Exception {
        // given


        // when
        Connection connection = DBConnectUtil.getConnection();

        // then
        assertThat(connection).isNotNull();

    }

}