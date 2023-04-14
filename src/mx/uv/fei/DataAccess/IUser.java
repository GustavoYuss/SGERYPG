package mx.uv.fei.DataAccess;
import java.sql.SQLException;

import mx.uv.fei.Logic.User;

public interface IUser {
    
    int addUser (User user) throws SQLException;

}
