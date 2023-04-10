package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mx.uv.fei.Logic.IUser;
import mx.uv.fei.Logic.User;

public class AddStudentDAO implements IUser{
    
    public int addUser (User user) throws SQLException{
        int result = 0;
        int verify = VerifyRegistration(user);
        
        if (verify == 0 && checkEmail(user) == 0) {
            
            String query = "insert into Usuario(PrimerNombre, SegundoNombre, ApellidoPaterno, ApellidoMaterno, CorreoInstitucional, tipoUsuario) values(?,?,?,?,?)";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName().toUpperCase());
            statement.setString(2, user.getSecondName().toUpperCase());
            statement.setString(3, user.getLastName().toUpperCase());
            statement.setString(4, user.getMothersLastName().toUpperCase());
            statement.setString(5, user.getInstitutionalMail().toUpperCase());
            result = statement.executeUpdate();
            return result;
            
        } else if (verify == 1) {
            return verify;
        } else {
            return result = -1;
        }
    }

    public int addStudent (User user, String enrollmentType) throws SQLException{
        int result = 0;
        int idUser = getIdUser(user);

        String query = "insert into alumno(Matricula, idUsuario, tipoInscripcion) values(?,?)";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getKey());
        if (idUser != -1){
        statement.setInt(2, idUser);
        }
        statement.setString(3, enrollmentType);
        result = statement.executeUpdate();
        return result;
    }
    
    public int VerifyRegistration(User user) throws SQLException {
        int result = 0;
        String query = "select * from alumno where correoElectronico = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getInstitutionalMail());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("Matricula").toUpperCase().equals(user.getKey())){
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }

    public int getIdUser(User user) throws SQLException {
        int result = 0;
        String query = "select * from usuario where apellidoPaterno = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getLastName());

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("PrimerNombre").toUpperCase().equals(user.getFirstName()) 
                    && resultSet.getString("ApellidoPaterno").equals(user.getLastName())
                            && resultSet.getString("ApellidoMaterno").equals(user.getMothersLastName())){

                result = resultSet.getInt("idUsuario");

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
