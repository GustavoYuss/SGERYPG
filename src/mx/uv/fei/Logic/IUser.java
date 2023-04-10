package mx.uv.fei.Logic;

import java.sql.SQLException;

public interface IUser {

    int addUser (User user) throws SQLException;
    int UserType(String userType) throws SQLException;
    int checkEmail(User user);
    int verifyUser(User user) throws SQLException;

}
