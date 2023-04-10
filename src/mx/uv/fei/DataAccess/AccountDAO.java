 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.uv.fei.Logic.Account;

/**
 *
 * @author dany_
 */
public class AccountDAO {
    public int addAccount(Account account) throws SQLException{
    String query = "insert into (NombreUsuario, contraseña, Modificada, idUsuario) values(?,?,?,?)";
            DataBaseManager dataBaseManager= new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getUserName()) ;
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getModified());
            statement.setString(4, account.getIdUser());
        return 0;
    }
}

