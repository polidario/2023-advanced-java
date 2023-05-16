package fr.epita.advjava;

import fr.epita.advjava.datamodel.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UsersDAO {

    public void create(User user){
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "user", "user");

        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (?, ?)");
        insertStatement.setInt(1, user.getId());
        insertStatement.setString(2, user.getName());
        insertStatement.execute();
    }


    public void search(User user){

    }
}
