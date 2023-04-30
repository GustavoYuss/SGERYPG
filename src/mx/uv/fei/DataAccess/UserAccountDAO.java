/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.uv.fei.Logic.User;
import mx.uv.fei.Logic.UserAccount;

/**
 *
 * @author Migue
 */
public class UserAccountDAO {

    public UserAccountDAO() {
    }
    
    //@Override
    public ArrayList<UserAccount> consultAllUserAccount() throws SQLException {
        ArrayList<UserAccount> userAccountList = new ArrayList<>();
       
        String query = "SELECT NombreUsuario, Contraseña, idUsuario FROM cuenta";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while (resultSet.next()) {
            UserAccount userAccount = new UserAccount();
            
            userAccount.setNicknameIdentifier(resultSet.getString("NombreUsuario"));
            userAccount.setPassword(resultSet.getString("contraseña"));
            userAccount.setUser(resultSet.getInt("idUsuario"));

            userAccountList.add(userAccount);
        }
        
        connection.close();
        
        return userAccountList;
    }
    
    public int validateLogin(UserAccount userAccount) throws SQLException{
        
        ArrayList<UserAccount> userAccountList = consultAllUserAccount();
        
        for (UserAccount user : userAccountList) {
            if (user.getNicknameIdentifier().equals(userAccount.getNicknameIdentifier()) && user.getPassword().equals(userAccount.getPassword())) {
                return 1;
            }
        }
        
        return 0;
    }
    
}
