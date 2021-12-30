package org.but.feec.project3.data;

import javafx.scene.control.Alert;
import org.but.feec.project3.api.*;
import org.but.feec.project3.config.DataSourceConfig;
import org.but.feec.project3.controller.LoginController;
import org.but.feec.project3.exceptions.DataAccessException;

import java.sql.*;

public class PersonRepository {
    public PersonAuthView findPersonByEmail(String email) {
        try (Connection connection = DataSourceConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT email, pwd" +
                             " FROM bpc_bds_project.persons p" +
                             " WHERE p.email = ?")
        ) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToPersonAuth(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Find person failed.");
        }
        return null;
    }

    /*public void editPerson(ProductEditView personEditView) {
        String insertPersonSQL = "UPDATE bpc_bds_project.person p SET email = ?, first_name = ?, nickname = ?, surname = ? WHERE p.id_person = ?";
        String checkIfExists = "SELECT email FROM bpc_bds_project.person p WHERE p.id_person = ?";
        try (Connection connection = DataSourceConfig.getConnection();
             // would be beneficial if I will return the created entity back
             PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS)) {
            // set prepared statement variables
            preparedStatement.setString(1, personEditView.getEmail());
            preparedStatement.setString(2, personEditView.getFirstName());
            preparedStatement.setString(3, personEditView.getNickname());
            preparedStatement.setString(4, personEditView.getSurname());
            preparedStatement.setLong(5, personEditView.getId());

            try {
                // TODO set connection autocommit to false
                /* HERE */
           /*     try (PreparedStatement ps = connection.prepareStatement(checkIfExists, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setLong(1, personEditView.getId());
                    ps.execute();
                } catch (SQLException e) {
                    throw new DataAccessException("This person for edit do not exists.");
                }

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new DataAccessException("Creating person failed, no rows affected.");
                }
                // TODO commit the transaction (both queries were performed)
                /* HERE */
         /*   } catch (SQLException e) {
                // TODO rollback the transaction if something wrong occurs
                /* HERE */
       /*     } finally {
                // TODO set connection autocommit back to true
                /* HERE */
     /*       }
        } catch (SQLException e) {
            throw new DataAccessException("Creating person failed operation on the database failed.");
        }
    }*/

    private PersonAuthView mapToPersonAuth(ResultSet rs) throws SQLException {
        PersonAuthView person = new PersonAuthView();
        person.setEmail(rs.getString("email"));
        person.setPassword(rs.getString("pwd"));

        return person;
    }

}
