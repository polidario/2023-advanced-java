import fr.epita.advjava.UsersDAO;
import fr.epita.advjava.datamodel.User;
import fr.epita.advjava.services.exceptions.DatamodelCreationException;
import fr.epita.advjava.services.exceptions.DatamodelSearchException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

public class TestDatabaseConnection {


    private Connection connection;

    @BeforeEach
    public void setup() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:test", "user", "user");
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE USERS(ID INT, NAME VARCHAR(255))");
        preparedStatement.execute();

    }

    @Test
    public void testCreate() throws DatamodelCreationException, SQLException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO();
        User user = new User();
        user.setId(1);
        user.setName("Thomas");


        //when
        dao.create(user);

        //then
        ResultSet resultSet = this.connection.prepareStatement("SELECT * FROM USERS WHERE ID = 1").executeQuery();
        String name = null;
        while (resultSet.next()){
            name = resultSet.getString("NAME");
        }

        Assertions.assertEquals(name, "Thomas");

    }    @Test
    public void testSearch() throws SQLException, DatamodelSearchException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO();
        this.connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (1, 'Thomas')").execute();

        //when
        User criteria = new User();
        criteria.setId(1);
        criteria.setName("Thomas");
        List users = dao.search(criteria);

        //then
        Assertions.assertEquals(users.size(), 1);


    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
    }
}
