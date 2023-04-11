package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uv.fei.Logic.User;

public class UserVerifierDAO {
    
    public int VerifyRegistrationStudent(User user) throws SQLException {
        int result = 0;
        String query = "select * from alumno where matricula = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getKey());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("Matricula").equals(user.getKey())){
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }

    public int VerifyRegistrationTeacher(User user) throws SQLException {
        int result = 0;
        String query = "select * from Docente where claveDocente = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getInstitutionalMail());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("claveDocente").equals(user.getKey())){
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }

    public int checkEmail(User user) {
        int result = 1;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(user.getInstitutionalMail());
        if (mather.find() == true) {
            result = 0;
        } 
        
        return result;
    }    
}
