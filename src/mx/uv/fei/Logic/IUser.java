package mx.uv.fei.Logic;

import java.sql.SQLException;

public interface IUser {

    int addUser (User user) throws SQLException;
    int VerifyRegistration(User user) throws SQLException;

}
