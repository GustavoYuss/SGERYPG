/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mx.uv.fei.DataAccess.PreliminaryProjectDAO;
import mx.uv.fei.DataAccess.UserAccountDAO;
import mx.uv.fei.Logic.PreliminaryProject;
import mx.uv.fei.Logic.UserAccount;
import sgerypg.sgerypg;

/**
 *
 * @author Migue
 */
public class LoginController implements Initializable{
    
    @FXML
    private Button butLogIn;

    @FXML
    private PasswordField pwfPassword;

    @FXML
    private TextField txtPersonalUserName;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public UserAccount getNicknameIdentifier(){
        UserAccount userAccount = new UserAccount();
        
        userAccount.setNicknameIdentifier(txtPersonalUserName.getText());
        userAccount.setPassword(pwfPassword.getText());
        
        return userAccount;
    }
    
    @FXML
    private void logIn(ActionEvent event) throws IOException {
        
        if(checkFields() == 0){
            UserAccount userAccount = getNicknameIdentifier();
            UserAccountDAO conection = new UserAccountDAO();
            
            int result = 0;
            try {
                result = conection.validateLogin(userAccount);
            } catch (SQLException ex) {
                Logger.getLogger(PreliminaryProjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(result == 1){
                sgerypg scene = new sgerypg();
                scene.setRoot("/mx/uv/fei/GUI/PreliminaryProjects");
            }
        }
    }
    
    public int checkFields(){
        int nonEmptyFields = 0;

        if(txtPersonalUserName.getText().isEmpty()){
            nonEmptyFields = 1;
        }
        if(pwfPassword.getText().isEmpty()){
            nonEmptyFields = 1;
        }
        
        return nonEmptyFields;
    }
    
    
    
}
