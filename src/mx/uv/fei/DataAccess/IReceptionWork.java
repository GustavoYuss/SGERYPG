/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import mx.uv.fei.Logic.ReceptionWork;

/**
 *
 * @author yusgu
 */
public interface IReceptionWork {
    public ArrayList<ReceptionWork> getAllReceptionWorks() throws SQLException;
    public ArrayList<ReceptionWork> getAllReceptionWorksByUser() throws SQLException;
}
