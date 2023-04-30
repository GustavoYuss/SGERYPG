package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< Updated upstream
=======
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
>>>>>>> Stashed changes

import mx.uv.fei.Logic.User;

public class UserDAO {

<<<<<<< Updated upstream
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
=======
    public int addUser (User user) throws SQLException{
        UserDAO checker = new UserDAO();
        int result = 0;
        int verify = checker.verifyRegistration(user);
        
        if (verify == 0 && checker.checkEmail(user) == 0) {
            
            String query = "insert into usuario (PrimerNombre, SegundoNombre, ApellidoPaterno, ApellidoMaterno, CorreoInstitucional, clave, idTipoUsuario) values(?, ?,?,?,?,?,?)";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getMothersLastName());
            statement.setString(5, user.getInstitutionalMail());
            statement.setString(6, user.getKey());
            statement.setInt(7, user.getTypeUser());

            result = statement.executeUpdate();
            return result;
            
        } else if (verify == 1) {
            return verify;
        } else {
            return result = -1;
        }
    }

    public int getIdUser(String key) throws SQLException {
        int result = 0;
        String query = "select * from usuario where clave = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, key);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("clave").equals(key)) {
>>>>>>> Stashed changes

                result = resultSet.getInt("idUsuario");

            } else {

                result = -1;

            }
        }

        return result;
    }

<<<<<<< Updated upstream
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

=======
    public int verifyRegistration(User user) throws SQLException {
        int result = 0;
        String query = "select * from usuario where clave = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, user.getKey());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("clave").equals(user.getKey())){
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }

    public ArrayList<User> consultAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from Usuario";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        
        while (resultSet.next()) {
            User user = new User();
            user.setFirstName(resultSet.getString("PrimerNombre"));
            user.setSecondName(resultSet.getString("SegundoNombre"));
            user.setLastName(resultSet.getString("ApellidoPaterno"));
            user.setInstitutionalMail(resultSet.getString("CorreoInstitucional"));
            user.setTypeUser(resultSet.getInt("idTipoUsuario"));
            user.setKey(resultSet.getString("Clave"));
            users.add(user);
        }
        connection.close();
        
        return users;
    }
    
    public ArrayList<User> consultTeacher(User user) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from Usuario where idUsuario = 2 and clave = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        //statement.setString(1, user.getKey());
        
        
        while (resultSet.next()) {
            User userAux = new User();
            userAux.setFirstName(resultSet.getString("PrimerNombre"));
            userAux.setSecondName(resultSet.getString("SegundoNombre"));
            userAux.setLastName(resultSet.getString("ApellidoPaterno"));
            userAux.setInstitutionalMail(resultSet.getString("CorreoInstitucional"));
            userAux.setTypeUser(resultSet.getInt("idTipoUsuario"));
            userAux.setKey(resultSet.getString("Clave"));
            users.add(user);
        }
        connection.close();
        
        return users;
    }
    
    public ArrayList<User> consultAllTeachers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from Usuario where idtipoUsuario = 2;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("idUsuario"));
            user.setFirstName(resultSet.getString("PrimerNombre"));
            user.setSecondName(resultSet.getString("SegundoNombre"));
            user.setLastName(resultSet.getString("ApellidoPaterno"));
            user.setMothersLastName(resultSet.getString("ApellidoMaterno"));
            user.setInstitutionalMail(resultSet.getString("CorreoInstitucional"));
            user.setTypeUser(resultSet.getInt("idTipoUsuario"));
            user.setKey(resultSet.getString("Clave"));
            users.add(user);
        }
        connection.close();
        
        return users;
    }
    
    public ArrayList<String> viewFullNameAllTeachers() throws SQLException {
        
        ArrayList<String> teachers = new ArrayList<>();
        String query = "SELECT CONCAT_WS(' ', PrimerNombre, SegundoNombre, ApellidoPaterno, ApellidoMaterno, '(', clave, ')') AS profesor FROM Usuario WHERE idTipoUsuario = 2;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery(query);
        
        
        while (resultSet.next()) {
            teachers.add(resultSet.getString("profesor"));
        }
        connection.close();
        
        return teachers;
    }

    public int checkEmail(User user) {

        int result = 1;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "estudiantes+.uv+.mx$");

        Matcher mather = pattern.matcher(user.getInstitutionalMail());
        if (mather.find() == true) {
            result = 0;
        }
        return result;

    }
>>>>>>> Stashed changes
}
