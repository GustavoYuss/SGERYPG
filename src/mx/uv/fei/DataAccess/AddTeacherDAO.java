package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.uv.fei.Logic.IAddUser;
import mx.uv.fei.Logic.User;

public class AddTeacherDAO implements IAddUser{

    public int addUser (User user) throws SQLException{
        UserVerifierDAO checker = new UserVerifierDAO();
        int result = 0;
        int verify = checker.VerifyRegistrationTeacher(user);
        
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
    
    public int addTeacher (User user, String Type) throws SQLException{
        UserDAO teacher = new UserDAO();
        int idUser = teacher.getIdUser(user);
        UserVerifierDAO checker = new UserVerifierDAO();
        int verify = checker.VerifyRegistrationStudent(user);
        int result = 0;

        if (verify == 0) {
            String query = "insert into Docente (claveDocente, idUsuario, idtipodocente) values(?,?,?)";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getKey());
            if (idUser != -1){
            statement.setInt(2, idUser);
            }
            statement.setInt(3, teacher.type(Type));
            result = statement.executeUpdate();
            return result;
        
        } else if (verify == 1) {
            return verify;
        } else {
            return result = -1;
        }
    }
}
