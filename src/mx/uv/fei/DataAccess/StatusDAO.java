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
import mx.uv.fei.Logic.Status;

/**
 *
 * @author yusgu
 */
public class StatusDAO implements IStatusDAO{

    @Override
    public ArrayList<Status> getAllStatus() throws SQLException {
        ArrayList<Status> statusList = new ArrayList<>();
        
        String query = "select * from estado";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while(resultSet.next()){
            Status status = new Status();
            status.setId(resultSet.getInt("idestado"));
            status.setStatus(resultSet.getString("estado"));
            
            statusList.add(status);   
        }
        connection.close();
        
        return statusList;
    }
}
