package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            result = statement.executeUpdate();
            return result;

        } else if (verify == 1) {
            return verify;
        } else {
            return result = -1;
        }
    }
    
}
