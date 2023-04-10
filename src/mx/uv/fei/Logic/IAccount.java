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
public interface IAccount {
     int addAccount (Account account) throws SQLException;
     Group getAccountById(int AccountId);
}
