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
import mx.uv.fei.Logic.ReceptionWork;

/**
 *
 * @author yusgu
 */
public class ReceptionWorkDAO implements IReceptionWork{

    @Override
    public ArrayList<ReceptionWork> getAllReceptionWorks() throws SQLException {
        ArrayList<ReceptionWork> receptionWorkList = new ArrayList<>();
        String query = "select * from trabajorecepcional;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while(resultSet.next()){
            ReceptionWork receptionWork = new ReceptionWork();
            receptionWork.setIdReceptionWork(resultSet.getInt("idtrabajoreceptional"));
            receptionWork.setTittle(resultSet.getString("nombre"));
            receptionWork.setIdStatus(resultSet.getInt("idestado"));
            receptionWork.setIddraft(resultSet.getInt("idanteproyecto"));
            receptionWorkList.add(receptionWork);
        }
        connection.close();
        
        return receptionWorkList;
    }

    @Override
    public ArrayList<ReceptionWork> getAllReceptionWorksByUser() throws SQLException {
        ArrayList<ReceptionWork> receptionWorkList = new ArrayList<>();
        String query = "";
        
        return receptionWorkList;
    }
}
