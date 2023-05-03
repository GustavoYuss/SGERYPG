/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.util.ArrayList;
import java.sql.SQLException;
import mx.uv.fei.Logic.User;
import java.sql.ResultSet;
import javafx.collections.ObservableList;
import mx.uv.fei.Logic.UserTable;

/**
 *
 * @author yusgu
 */
public interface IUser {
    public int registerUser(User user) throws SQLException;
    public int checkUserExistence(User user) throws SQLException;
    public int updateUser(User user) throws SQLException;
    public ArrayList<User> getAllUsers() throws SQLException;
    public ArrayList<User> getUsersByType(int userType) throws SQLException;
    public ArrayList<User> makeUsersList(ResultSet resultSet) throws SQLException;
    public int getIdUser (User user) throws SQLException;
    public ObservableList<UserTable> getUsersTable() throws SQLException;
    public User getUserById(int id) throws SQLException;
}
