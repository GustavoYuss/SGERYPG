/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.SQLException;
import java.util.ArrayList;
import mx.uv.fei.Logic.Advance;
import mx.uv.fei.Logic.AdvanceFile;
import mx.uv.fei.Logic.User;

/**
 *
 * @author yusgu
 */
public interface IAdvance {
    public ArrayList<Advance> getAllAdvance(User user) throws SQLException;
    public int registerAdvance(Advance advance) throws SQLException;
    public int registerFileAdvance(AdvanceFile file) throws SQLException;
    public int getLastID(int user)throws SQLException;
    public ArrayList<AdvanceFile> getAllFilesAvance(Advance advance) throws SQLException;
}
