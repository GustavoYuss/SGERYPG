/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mx.uv.fei.Logic.Account;

/**
 *
 * @author yusgu
 */

public class AccountDAO implements IAccount{

    @Override
    public int registerAccount(Account account) throws SQLException {
        int result = 0;
        
        if(checkDuplicateAccount(account) == 0){
            String query = "insert into cuenta(nombreusuario,contraseña,idusuario) values (?, ?, ?)";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, account.getUserName());
            statement.setString(2, account.getPassword());
            statement.setInt(3, account.getIdUser());

            result = statement.executeUpdate();
            connection.close();
        }

        return result;
    }

    @Override
    public int getAccountId(Account account) throws SQLException {
        int idUser;
        String query = "select * from cuenta where nombreusuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection(); 
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,account.getUserName());
        
        ResultSet resultSet = statement.executeQuery();
        
        resultSet.next();
        idUser = resultSet.getInt("idcuenta");
        connection.close();

        return idUser;
    }

    @Override
    public int checkDuplicateAccount(Account account) throws SQLException {
        int ressult = 0;
        String query = "SELECT * FROM cuenta WHERE idusuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, account.getIdUser());
        ResultSet resultSet = statement.executeQuery();
       
        if(resultSet.next()){
            ressult = resultSet.getInt("idusuario");
        }
         
        return ressult;
    }
    
}
