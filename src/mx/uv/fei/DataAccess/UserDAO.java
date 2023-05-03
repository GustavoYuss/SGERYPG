/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;


import mx.uv.fei.Logic.User;
import mx.uv.fei.Logic.UserTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author yusgu
 */
public class UserDAO implements IUser{

    @Override
    public int registerUser(User user) throws SQLException { 
        int result = 0;
        
        if(checkUserExistence(user) == 0){
            String query = "insert into usuario(PrimerNombre,SegundoNombre,ApellidoPaterno,ApellidoMaterno,CorreoInstitucional,clave, idtipousuario, idestado) values (?,?,?,?,?,?,?,?);";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getMiddleName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getSecondSurname());
            statement.setString(5, user.geteMail());
            statement.setInt(6, user.getClave());
            statement.setInt(7, user.getIdUserType());
            statement.setInt(8, user.getIdStatus());
            
            result = statement.executeUpdate();
            connection.close();
        }
        return result;
    }

    @Override
    public int checkUserExistence(User user) throws SQLException {
        int result = 0;  
        
        String query = "select count(*) as count from usuario where clave = ? or CorreoInstitucional = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setInt(1, user.getClave());
        statement.setString(2, user.geteMail());
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            result = resultSet.getInt("count");
            connection.close();
        }

        return result;
    }

    @Override
    public int updateUser(User user) throws SQLException {

        int result;

        String query = "update usuario set PrimerNombre = ?,SegundoNombre = ?,ApellidoPaterno = ?,ApellidoMaterno = ?,CorreoInstitucional = ?,clave = ?,idtipousuario = ? where idusuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getMiddleName());
        statement.setString(3, user.getLastName());
        statement.setString(4, user.getSecondSurname());
        statement.setString(5, user.geteMail());
        statement.setInt(6, user.getClave());
        statement.setInt(7, user.getIdUserType());
        statement.setInt(8, user.getIdUser());
        
        result = statement.executeUpdate();
        connection.close();
        
        return result;
    }

    @Override
    public ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        
        String query = "select * from usuario";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        users = makeUsersList(resultSet);
        connection.close();
        
        return users;
    }

    @Override
    public ArrayList<User> makeUsersList(ResultSet resultSet) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        
        while(resultSet.next()){
            User user = new User();
            user.setIdUser(resultSet.getInt("idusuario"));
            user.setFirstName(resultSet.getString("PrimerNombre"));
            user.setMiddleName(resultSet.getString("SegundoNombre"));
            user.setLastName(resultSet.getString("ApellidoPaterno"));
            user.setSecondSurname(resultSet.getString("ApellidoMaterno"));
            user.seteMail(resultSet.getString("CorreoInstitucional"));
            user.setClave(resultSet.getInt("clave"));
            user.setIdUserType(resultSet.getInt("idtipousuario"));
            user.setIdStatus(resultSet.getInt("idEstado"));
            
            users.add(user);
        }
        return users;
    }

    @Override
    public ArrayList<User> getUsersByType(int userType) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        
        String query = "select * from usuario where idtipousuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection(); 
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,userType);
        ResultSet resultSet = statement.executeQuery(query);
        users = makeUsersList(resultSet);
        connection.close();
        
        return users;
    }

    @Override
    public int getIdUser(User user) throws SQLException {
        int idUser;
        String query = "select * from usuario where clave = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection(); 
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,user.getClave());
        
        ResultSet resultSet = statement.executeQuery();
        
        resultSet.next();
        idUser = resultSet.getInt("idusuario");
        connection.close();

        return idUser;
    }

    @Override
    public ObservableList<UserTable> getUsersTable() throws SQLException {
        ObservableList<UserTable> result = FXCollections.observableArrayList();
        
        String query = "select T1.idusuario, concat(T1.primernombre, ' ', T1.segundoNombre, ' ', T1.apellidopaterno, ' ', T1.apellidomaterno) AS nombreCompleto, T1.correoinstitucional, T1.clave, T2.tipousuario, T3.estado from usuario T1 inner join tipousuario T2 on T2.idtipousuario = T1.idtipousuario inner join estado T3 on T3.idestado = T1.idestado;";
        
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            UserTable user = new UserTable();
            user.setId(resultSet.getInt("idusuario"));
            user.setFullName(resultSet.getString("nombrecompleto"));
            user.setStatus(resultSet.getString("estado"));
            user.setUserType(resultSet.getString("tipousuario"));
            user.setHola(resultSet.getString("Correoinstitucional"));
            user.setClave(resultSet.getInt("clave"));
            
            result.add(user);
        }

        return result;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = new User();
        
        String query = "select T1.primernombre, T1.segundoNombre, T1.apellidopaterno, T1.apellidomaterno, T1.correoinstitucional, T1.clave, T2.tipousuario, T3.estado, T3.idestado "
                + "from usuario T1 inner join tipousuario T2 on T2.idtipousuario = T1.idtipousuario "
                + "inner join estado T3 on T3.idestado = T1.idestado where idusuario = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection(); 
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        user.setFirstName(resultSet.getString("primernombre"));
        user.setMiddleName(resultSet.getString("segundonombre"));
        user.setLastName(resultSet.getString("apellidopaterno"));
        user.setSecondSurname(resultSet.getString("apellidomaterno"));
        user.seteMail(resultSet.getString("correoinstitucional"));
        user.setClave(resultSet.getInt("clave"));
        user.setType(resultSet.getString("tipousuario"));
        user.setStatus(resultSet.getString("estado"));
        user.setIdStatus(resultSet.getInt("idestado"));
                
        return user;
    }
}
