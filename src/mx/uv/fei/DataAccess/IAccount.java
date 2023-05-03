/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.DataAccess;

import mx.uv.fei.Logic.Account;
import java.sql.SQLException;

/**
 *
 * @author yusgu
 */

public interface IAccount {
    public int registerAccount(Account account) throws SQLException;
    public int getAccountId(Account account) throws SQLException;
    public int checkDuplicateAccount(Account account) throws SQLException; 
}
