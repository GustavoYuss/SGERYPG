/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.Logic;
import java.sql.SQLException;
/**
 *
 * @author dany_
 */
public interface IGroup {
    int addGroup (Group group) throws SQLException;
    Group getGroupById(int GroupId);
}
