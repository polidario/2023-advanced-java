import fr.epita.advjava.UsersDAO;
import fr.epita.advjava.datamodel.User;
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
    public void testCreate() throws SQLException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO();
        User user = new User();
        user.setId(1);
        user.setName("Thomas");


        //when
        dao.create(user);

        //then

    }    @Test
    public void testSearch() throws SQLException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO();
        User user = new User();
        user.setId(1);
        user.setName("Thomas");

        //when
        PreparedStatement selectStatement = connection.prepareStatement("SELECT ID,NAME FROM USERS");
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            System.out.println(id + " " + name);
        }

        //then

    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
    }
}
