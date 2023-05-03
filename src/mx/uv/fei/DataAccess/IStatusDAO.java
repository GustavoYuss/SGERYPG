/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import mx.uv.fei.Logic.Status;

/**
 *
 * @author yusgu
 */

public interface IStatusDAO {
    public ArrayList<Status> getAllStatus() throws SQLException;
}
