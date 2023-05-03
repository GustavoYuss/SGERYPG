/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import mx.uv.fei.Logic.Deliverable;

/**
 *
 * @author yusgu
 */
public interface IDeliverable {
    public int registerDeliverable(Deliverable deliverable) throws SQLException;
    public int updateDeliverable(Deliverable deliverable) throws SQLException;
    public ArrayList<Deliverable> getAllDeliverable() throws SQLException;
}
