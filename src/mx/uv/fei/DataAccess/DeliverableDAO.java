/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.uv.fei.Logic.Deliverable;

/**
 *
 * @author yusgu
 */
public class DeliverableDAO implements IDeliverable{

    @Override
    public int registerDeliverable(Deliverable deliverable) throws SQLException {

        int result;
        
        String query = "insert into entregable(titulo,descripcion,idtrabajorecepcional) values (?,?,?);";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, deliverable.getTitle());
        statement.setString(2, deliverable.getDescription());
        statement.setInt(3, deliverable.getIdtrabajorecepcional()); 
        result = statement.executeUpdate();
        dataBaseManager.closeConnection();
        
        return result;
    }

    @Override
    public int updateDeliverable(Deliverable deliverable) throws SQLException {

        int result;
        
        String query = "update entregable set titulo = ?, descripcion = ?, idtrabajorecepcional = ? where identregable = ?;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, deliverable.getTitle());
        statement.setString(2, deliverable.getDescription());
        statement.setInt(3, deliverable.getIdtrabajorecepcional()); 
        statement.setInt(4, deliverable.getId());
        result = statement.executeUpdate();
        dataBaseManager.closeConnection();
        
        return result;
    }

    @Override
    public ArrayList<Deliverable> getAllDeliverable() throws SQLException {
       
        ArrayList<Deliverable> advances = new ArrayList();
        
        String query = "select * from entregable;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while(resultSet.next()){
            Deliverable deliverable = new Deliverable();
            deliverable.setId(resultSet.getInt("identregable"));
            deliverable.setTitle(resultSet.getString("titulo"));
            deliverable.setDescription(resultSet.getString("Descripcion"));
            deliverable.setIdtrabajorecepcional(resultSet.getInt("idtrabajorecepcional"));
            advances.add(deliverable);
        }
        dataBaseManager.closeConnection();
        
        return advances;
    }
    
}
