package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mx.uv.fei.Logic.User;

public class UserDAO {

    public int getIdUser(User user) throws SQLException {
        int result = 0;
        String query = "select * from usuario where apellidoPaterno = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getLastName());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("PrimerNombre").equals(user.getFirstName()) 
                    && resultSet.getString("ApellidoPaterno").equals(user.getLastName())
                            && resultSet.getString("ApellidoMaterno").equals(user.getMothersLastName())){

                result = resultSet.getInt("idUsuario");

            } else {

                result = -1;

            }
        }

        return result;
    }

    public int type(String userType) throws SQLException {
        String query = "select * from Tipousuario where TipoUsuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userType);
        ResultSet resultSet = statement.executeQuery();

        int idUserType = resultSet.getInt("TipoUsuario");
        return idUserType;
    }

}
