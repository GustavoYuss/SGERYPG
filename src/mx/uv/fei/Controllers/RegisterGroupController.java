/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mx.uv.fei.DataAccess.DataBaseManager;
import mx.uv.fei.DataAccess.SchoolGroupDAO;
import mx.uv.fei.DataAccess.UserDAO;
import mx.uv.fei.Logic.SchoolGroup;

import sgerypg.sgerypg;


/**
 * FXML Controller class
 *
 * @author Migue
 */
public class RegisterGroupController implements Initializable {

    @FXML
    private Button buyExitWindow;
    
    @FXML
    private Button butRegister;
    
    @FXML
    private TextField txtKeyTeacher;
    
    @FXML
    private TextField txtNrc;
    
    @FXML
    private TextField txtSchoolPeriod;
    
    @FXML
    private TextField txtSection;
    
    @FXML
    private TextField txtSlots;  
    
    @FXML
    private ComboBox cboEducationalExperience;
    
    SchoolGroup GROUP = new SchoolGroup();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       fillComboBoxEducationalExperience();
    }
    
    public int getIdEducationalExperience(){
        String EducationalExperience = cboEducationalExperience.getSelectionModel().getSelectedItem().toString();
        if(EducationalExperience.equalsIgnoreCase("Proyecto Guiado")){
            return 1;
        }else{
            return 2;
        }     
    }
    
    public void prepareGroupObjectCreate(){
        GROUP.setNrc(txtNrc.getText());
        GROUP.setSchoolPeriod(txtSchoolPeriod.getText());
        GROUP.setSlots(Integer.parseInt(txtSlots.getText()));
        GROUP.setSection(txtSection.getText());
        GROUP.setIdEducationalExperience(getIdEducationalExperience());
        
        UserDAO user = new UserDAO();
        
        try{
            GROUP.setIdTeacher(user.getIdUser(txtKeyTeacher.getText()));
        }catch(SQLException exception){
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    public void fillComboBoxEducationalExperience(){
       ObservableList<String> list = FXCollections.observableArrayList("Experiencia Recepcional", 
         "Proyecto Guiado");
       cboEducationalExperience.setItems(list);
    }
    
    @FXML
    private void registerGroupInDataBase(ActionEvent event){        
        SchoolGroupDAO logger = new SchoolGroupDAO();
        prepareGroupObjectCreate();
        try{
            logger.addClass(GROUP);
        }catch(SQLException exception){
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    @FXML
    private void exitRegisterGroups(ActionEvent event) throws IOException {
        sgerypg.setRoot("/mx/uv/fei/GUI/Groups");
    }
}
