/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mx.uv.fei.Logic.Advance;

/**
 *
 * @author yusgu
 */
public class AdvanceDAO implements IAdvance{

    @Override
    public int registerAdvance(Advance advance) throws SQLException{
        
        int result;
        
        String query = "insert into avance(titulo,descripcion,fechaentrega,idalumno,idtrabajorecepcional) values (?,?,?,?,?);";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, advance.getTitle());
        statement.setString(2, advance.getDescription());
        statement.setString(3, advance.getDuetodate());
        statement.setInt(4, advance.getIdstudent()); 
        statement.setInt(5, advance.getIdtrabajorecepcional()); 
        result = statement.executeUpdate();
        dataBaseManager.closeConnection();
        
        return result;
    }

    @Override
    public ArrayList<Advance> getAllAdvances() throws SQLException{
        
        ArrayList<Advance> advances = new ArrayList();
        
        String query = "select * from avance;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while(resultSet.next()){
            Advance advance = new Advance();
            advance.setId(resultSet.getInt("idavance"));
            advance.setTitle(resultSet.getString("titulo"));
            advance.setDescription(resultSet.getString("descripcion"));
            advance.setDuetodate(resultSet.getString("fechaentrega"));
            advance.setIdstudent(resultSet.getInt("idalumno"));
            advance.setIdtrabajorecepcional(resultSet.getInt("idtrabajorecepcional"));
            advances.add(advance);
        }
        dataBaseManager.closeConnection();
        
        return advances;
        
    }
    
}
