package mx.uv.fei.Logic;
import java.sql.SQLException;

public interface IAddUser {
    
    int addUser (User user) throws SQLException;

}
