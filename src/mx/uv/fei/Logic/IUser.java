package mx.uv.fei.Logic;

import java.sql.SQLException;

public interface IUser {

    int addUser (User user, String userType) throws SQLException;
    int addStudent (User user, String enrollment) throws SQLException;
    
}
