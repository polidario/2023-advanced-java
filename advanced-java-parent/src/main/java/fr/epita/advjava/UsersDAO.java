package fr.epita.advjava;

import fr.epita.advjava.datamodel.User;
import fr.epita.advjava.services.Configuration;
import fr.epita.advjava.services.exceptions.DatamodelCreationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UsersDAO {

    public void create(User user) throws DatamodelCreationException {
        try {
            Connection connection = Configuration.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (?, ?)");
            insertStatement.setInt(1, user.getId());
            insertStatement.setString(2, user.getName());
            insertStatement.execute();
        }catch (Exception e){
            DatamodelCreationException creationException = new DatamodelCreationException();
            creationException.initCause(e);
            throw creationException;
        }
    }


    public void search(User user){

    }
}
