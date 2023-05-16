import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestDatabaseConnection {


    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:test", "user", "user");
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE USERS(ID INT, NAME VARCHAR(255))");
        preparedStatement.execute();

    }

    @Test
    public void test() throws SQLException {

        //given (handled by setup())

        //when
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (0, 'Thomas')");
        insertStatement.execute();


        //when
        PreparedStatement selectStatement = connection.prepareStatement("SELECT ID,NAME FROM USERS");
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            System.out.println(id + " " + name);
        }

    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
    }
}
