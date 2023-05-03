/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mx.uv.fei.Logic.Advance;
import mx.uv.fei.Logic.AdvanceFile;
import mx.uv.fei.Logic.User;

/**
 *
 * @author yusgu
 */
public class AdvanceDAO implements IAdvance{

    @Override
    public ArrayList<Advance> getAllAdvance(User user) throws SQLException {
        ArrayList<Advance> advances = new ArrayList<>();
        String query = "select * from avance where idalumno = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setInt(1, user.getIdUser());
        ResultSet resultSet = statement.executeQuery();
        advances = fillArrayList(resultSet);
        connection.close();
        
        return advances;
    }

    @Override
    public int registerAdvance(Advance advance) throws SQLException {
        int result;
        
        String query = "insert into avance(titulo,descripcion,idalumno,idtrabajorecepcional) values (?,?,?,?);";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, advance.getTitle());
        statement.setString(2, advance.getDescription());
        statement.setInt(3, advance.getIdStudent());
        statement.setInt(4, advance.getIdReceptionWork());
        
        result = statement.executeUpdate();
        connection.close();
        
        return result;
    }
    
    private ArrayList<Advance> fillArrayList(ResultSet resultSet) throws SQLException{
        
        ArrayList<Advance> advances = new ArrayList<>();
        while(resultSet.next()){
            Advance advance = new Advance();
            advance.setIdAdvance(resultSet.getInt("idavance"));
            advance.setTitle(resultSet.getString("titulo"));
            advance.setDescription(resultSet.getString("descripcion"));
            advance.setIdReceptionWork(resultSet.getInt("idtrabajorecepcional"));
            advance.setIdStudent(resultSet.getInt("idalumno"));
            
            advances.add(advance);
        }
        return advances;
    }

    @Override
    public int registerFileAdvance(AdvanceFile file) throws SQLException {
        int result;
        String query = "insert into avancearchivo(direccionarchivo, nombreOriginal,identificadorunico,idavance) values (?,?,?,?);";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, file.getSource());
        statement.setString(2, file.getOriginalName());
        statement.setString(3, file.getNewName());
        statement.setInt(4, file.getIdAdvance());
        
        result = statement.executeUpdate();
        connection.close();
        
        return result;
    }

    @Override
    public int getLastID(int user) throws SQLException {
        int result;
        String query = "select idavance from avance where idalumno = ? order by idavance DESC limit 1;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setInt(1, user);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        result = resultSet.getInt("idavance");
        connection.close();
        
        return result;
    } 

    @Override
    public ArrayList<AdvanceFile> getAllFilesAvance(Advance advance) throws SQLException {
        ArrayList<AdvanceFile> files = new ArrayList<>();
        String query = "select nombreOriginal from avancearchivo where idavance = ?";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setInt(1, advance.getIdAdvance());
        ResultSet resultSet = statement.executeQuery();
        
        while(resultSet.next()){
            AdvanceFile file = new AdvanceFile();
            file.setOriginalName(resultSet.getString("nombreOriginal"));
            files.add(file);
        }
        connection.close();
        
        return files;
    }
}
