/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.Logic;

/**
 *
 * @author Migue
 */
public class UserAccount {

    public UserAccount() {
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNicknameIdentifier() {
        return nicknameIdentifier;
    }

    public void setNicknameIdentifier(String nicknameIdentifier) {
        this.nicknameIdentifier = nicknameIdentifier;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int User) {
        this.User = User;
    }
    
    String Password;
    String nicknameIdentifier;
    int User;
    
    
}
