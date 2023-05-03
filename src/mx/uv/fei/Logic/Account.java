/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.Logic;

/**
 *
 * @author yusgu
 */
public final class Account {
    private int idAccount;
    private String userName;
    private String password;
    private int idUser;

    public Account(User user){
        setUserName(user.geteMail());
        setPassword(user.geteMail().split("@")[0]);
        setIdUser(user.getIdUser());
    }

    public void setIdAccount(int idCount) {
        this.idAccount = idCount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getIdUser() {
        return idUser;
    }
}
