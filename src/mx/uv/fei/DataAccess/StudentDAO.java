package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
<<<<<<< Updated upstream
import java.sql.SQLException;

import mx.uv.fei.Logic.User;

public class StudentDAO implements IUser{

    public int addUser (User user) throws SQLException{
        UserVerifierDAO checker = new UserVerifierDAO();
        int result = 0;
        int verify = checker.verifyRegistrationStudent(user);
        
        if (verify == 0 && checker.checkEmail(user) == 0) {
            
            String query = "insert into usuario (PrimerNombre, SegundoNombre, ApellidoPaterno, ApellidoMaterno, CorreoInstitucional) values(?,?,?,?,?)";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getSecondName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getMothersLastName());
            statement.setString(5, user.getInstitutionalMail());
            result = statement.executeUpdate();
            return result;
            
        } else if (verify == 1) {
            return verify;
        } else {
            return result = -1;
        }
    }
    
    public int addStudent (User user, String enrollmentType) throws SQLException{
        UserDAO student = new UserDAO();
        UserVerifierDAO checker = new UserVerifierDAO();
        int verify = checker.verifyRegistrationStudent(user);
        int result = 0;

        if (verify == 0) {
            String query = "insert into alumno(idUsuario, Matricula ,TipoInscripcion) values(?,?,?)";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, student.getIdUser(user));
            statement.setString(2, user.getKey());
            statement.setString(3, enrollmentType);
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.uv.fei.Logic.User;

public class StudentDAO {
    
    public int addStudent (User user) throws SQLException{
        UserDAO student = new UserDAO();
        int verify = verifyStudent(user);
        int result = 0;

        if (verify == 0) {

            String query = "insert into alumno (idtipoinscripcion, idUsuario) values(?,?)";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getTypeUser());
            statement.setInt(2, student.getIdUser(user.getKey()));
>>>>>>> Stashed changes
            result = statement.executeUpdate();
            return result;

        } else if (verify == 1) {
            return verify;
        } else {
            return result = -1;
        }
    }
<<<<<<< Updated upstream
=======

    public int verifyStudent(User user) throws SQLException {
        int result = 0;
        UserDAO checker = new UserDAO();
        user.setId(checker.getIdUser(user.getKey()));

        String query = "select * from alumno where idUsuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getId());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("idUsuario").equals(user.getId())){
                result = 1;
            } else {
                result = -1;
            }
        }
        return result;
    }

    public int checkStudentEmail(User user) {

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

    public int getIdStudent(User user) throws SQLException {
        int result = 0;
        String query = "select * from Alumno where idUsuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, user.getId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            if (resultSet.getInt("idUsuario") == user.getId()) {

                result = resultSet.getInt("idAlumno");

            } else {

                result = -1;

            }
        }

        return result;
    }

    public ArrayList<User> consultAllStudents() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from Usuario where idtipoUsuario = 1;";
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
>>>>>>> Stashed changes
    
}
