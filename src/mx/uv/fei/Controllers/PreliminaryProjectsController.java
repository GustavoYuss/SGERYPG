/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import mx.uv.fei.DataAccess.DataBaseManager;
import mx.uv.fei.DataAccess.PreliminaryProjectDAO;
import mx.uv.fei.DataAccess.UserDAO;
import mx.uv.fei.Logic.PreliminaryProject;
import sgerypg.sgerypg;


/**
 * FXML Controller class
 *
 * @author Migue
 */

public class PreliminaryProjectsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ComboBox cboTeacher;
    
    @FXML
    private ComboBox cboDirector;
    
    @FXML
    private ComboBox cboModalityTR;
    
    @FXML
    private ComboBox cboLGAC;
    
    @FXML
    private ComboBox cboAcademicStaff;
    
    @FXML
    private ComboBox cboRole;
    
    @FXML
    private TextField txtDuration;
    
    @FXML
    private TextField txtaNameResearchProject;

    @FXML
    private TextField txtNameTR;

    @FXML
    private TextArea txtPreprojectRequirements;

    @FXML
    private TextArea txtaDescriptionPI;

    @FXML
    private TextArea txtaDescriptionTR;

    @FXML
    private TextArea txtaExpectedResults;

    @FXML
    private TextArea txtaLineResearch;
    
    @FXML
    private Button butAddResponsible;
    
    @FXML
    private Button butExit;
    
    @FXML
    private Label lbCount;
    
    public final static int VALID_FIELDS = 0;
    public final static int INVALID_FIELDS = 1;
    public final static int PROPOSED = 4;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillResponsibleComboBoxes();
        fillModalityComboBox();
        fillAcademicStaffComboBox();
        fillLgacComboBox();
        fillRolesComboBox();
        
    }
    
    public void fillResponsibleComboBoxes() {
        
        UserDAO connection = new UserDAO();
        ArrayList<String> teacherList = null;
        try {
            teacherList = connection.viewFullNameAllTeachers();
        } catch (SQLException exception) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
        ObservableList<String> teacherListModel = FXCollections.observableArrayList(teacherList);
        cboTeacher.setItems(teacherListModel);
        cboDirector.setItems(teacherListModel);
        
    }
   
    public void fillModalityComboBox() {
        
        PreliminaryProjectDAO connection = new PreliminaryProjectDAO();
        ArrayList<String> modalityList = null;
        try {
            modalityList = connection.consultAllModality();
        } catch (SQLException exception) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
        ObservableList<String> modalityModel = FXCollections.observableArrayList(modalityList);
        cboModalityTR.setItems(modalityModel);
        
    }
    
    public void fillAcademicStaffComboBox(){
        
       PreliminaryProjectDAO connection = new PreliminaryProjectDAO();
       ArrayList<String> academicStaffList = null;
       try{
           academicStaffList = connection.consultAllAcademicStaff();
       }catch (SQLException exception) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
       ObservableList<String> academicStaffModel = FXCollections.observableArrayList(academicStaffList);
       cboAcademicStaff.setItems(academicStaffModel);
       
    }
    
    public void fillLgacComboBox() {
        
        PreliminaryProjectDAO connection = new PreliminaryProjectDAO();
        ArrayList<String> lgacList = null;
        try {
            lgacList = connection.consultAllLgca();
        } catch (SQLException exception) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
        ObservableList<String> lgacModel = FXCollections.observableArrayList(lgacList);
        cboLGAC.setItems(lgacModel);
        
    }
    
    public void fillRolesComboBox() {
        
        PreliminaryProjectDAO connection = new PreliminaryProjectDAO();
        ArrayList<String> rolesList = null;
        try {
            rolesList = connection.consultAllRoles();
        } catch (SQLException exception) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
        ObservableList<String> rolesModel = FXCollections.observableArrayList(rolesList);
        cboRole.setItems(rolesModel);
        
    }
    
    public String LGCA = "";
    public String ACADEMIC_STAFF = "";
    public String MODALITYTR = "";

    public void getInformationFromComboBoxes() {
        
        LGCA = cboLGAC.getSelectionModel().getSelectedItem().toString();
        MODALITYTR = cboModalityTR.getSelectionModel().getSelectedItem().toString();
        ACADEMIC_STAFF = cboAcademicStaff.getSelectionModel().getSelectedItem().toString();
 
    }
    
    ArrayList<String> RESPONSIBLE_LIST_PRELIMINARYPROJECT = new ArrayList<>();
    ArrayList<Integer> LIST_ROLES_RESPONSIBLE_FOR_PROJECT = new ArrayList<>();
    
    int CLIKS = 4;
    
    @FXML
    private void addPreliminaryProjectResponsibles(ActionEvent event) throws IOException, SQLException {
        PreliminaryProjectDAO conection = new PreliminaryProjectDAO();
        
        if(!cboTeacher.getSelectionModel().isEmpty() && !cboRole.getSelectionModel().isEmpty()){
            String responsible = cboTeacher.getSelectionModel().getSelectedItem().toString();
            if(!RESPONSIBLE_LIST_PRELIMINARYPROJECT.contains(extractKeyFromCboTeacher(responsible))){
                CLIKS--;
                if (CLIKS < 0){
                    butAddResponsible.setDisable(true);
                    lbCount.setText(String.valueOf(CLIKS));
                }else{
                    RESPONSIBLE_LIST_PRELIMINARYPROJECT.add(extractKeyFromCboTeacher(responsible));
                    LIST_ROLES_RESPONSIBLE_FOR_PROJECT.add(conection.getIdRole(cboRole.getSelectionModel().getSelectedItem().toString()));
                    lbCount.setText(String.valueOf(CLIKS));
                }
            }
        }
        
    }
    
    public void addPreliminaryProjectDirector(){
        if(!cboDirector.getSelectionModel().isEmpty() ){
            String director = cboDirector.getSelectionModel().getSelectedItem().toString();
            RESPONSIBLE_LIST_PRELIMINARYPROJECT.add(extractKeyFromCboTeacher(director));
            LIST_ROLES_RESPONSIBLE_FOR_PROJECT.add(1);
        }
    }

    public PreliminaryProject getPreliminaryProject(){
    
        PreliminaryProject objPreProject = new PreliminaryProject();
        PreliminaryProjectDAO objPreProjectDAO = new PreliminaryProjectDAO();
        
        objPreProject.setNameProjectResearchProject(txtaNameResearchProject.getText());
        objPreProject.setNameReceptionWork(txtNameTR.getText());
        objPreProject.setRequirements(txtPreprojectRequirements.getText());
        objPreProject.setExpectedResults(txtaExpectedResults.getText());
        objPreProject.setDescriptionProjectInv(txtaDescriptionPI.getText());
        objPreProject.setDescriptionReceptionWork(txtaDescriptionTR.getText());
        objPreProject.setResearchline(txtaLineResearch.getText());
        objPreProject.setDuration(Integer.parseInt(txtDuration.getText()));
        objPreProject.setStatus(PROPOSED);
        
        getInformationFromComboBoxes();
        
        try {
            objPreProject.setAcademicStaff(objPreProjectDAO.getIdAcademicStaff(ACADEMIC_STAFF));
            objPreProject.setLGCA(objPreProjectDAO.getIdLgca(LGCA));
            objPreProject.setModality(objPreProjectDAO.getIdModality(MODALITYTR));
        } catch (SQLException ex) {
            Logger.getLogger(PreliminaryProjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return objPreProject;
    }
        
    public String extractKeyFromCboTeacher(String text) {
        
        String[] parts = text.split("[()]");
        if (parts.length >= 2) {
            return parts[1].replaceAll("\\s", "");
        } else {
            return "";
        }
        
    }
    
    @FXML
    private void registerPreliminaryProject(ActionEvent event) {
        
        if(checkFields() == VALID_FIELDS && emptyComboBoxes() == VALID_FIELDS && LIST_ROLES_RESPONSIBLE_FOR_PROJECT.size() > 1){
            PreliminaryProjectDAO conection = new PreliminaryProjectDAO();
            PreliminaryProject preliminaryProjects = getPreliminaryProject();
            getInformationFromComboBoxes();
            addPreliminaryProjectDirector();
            int result = 0;
            
            try {
                result =conection.addPreliminaryProject(preliminaryProjects);
            } catch (SQLException ex) {
                Logger.getLogger(PreliminaryProjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(result == 0){
                try {
                    conection.registerPreliminaryProjectResponsibles(RESPONSIBLE_LIST_PRELIMINARYPROJECT,
                            LIST_ROLES_RESPONSIBLE_FOR_PROJECT, preliminaryProjects.getNameReceptionWork());
                } catch (SQLException ex) {
                    Logger.getLogger(PreliminaryProjectsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            restoreFields();
        }
    }
    
    public int checkFields() {
        int nonEmptyFields = VALID_FIELDS;

        if(txtDuration.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }
        if(txtNameTR.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }
        if(txtPreprojectRequirements.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }
        if(txtaDescriptionPI.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }
        if(txtaDescriptionTR.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }
        if(txtaExpectedResults.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }
        if(txtaLineResearch.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }
        if(txtaNameResearchProject.getText().isEmpty()){
            nonEmptyFields = INVALID_FIELDS;
        }

        return nonEmptyFields;
    }
    
    public int emptyComboBoxes() {
        int nonEmptyComboBox = VALID_FIELDS;

        if(cboModalityTR.getSelectionModel().isEmpty()){
            nonEmptyComboBox = INVALID_FIELDS;
        }
        
        if(cboLGAC.getSelectionModel().isEmpty()){
            nonEmptyComboBox = INVALID_FIELDS;
        }
        
        if(cboAcademicStaff.getSelectionModel().isEmpty()){
            nonEmptyComboBox = INVALID_FIELDS;
        }

        return nonEmptyComboBox;
    }
    
    public void restoreFields() {
        
        txtDuration.clear();
        txtNameTR.clear();
        txtPreprojectRequirements.clear();
        txtaDescriptionPI.clear();
        txtaDescriptionTR.clear();
        txtaExpectedResults.clear();
        txtaLineResearch.clear();
        txtaNameResearchProject.clear();
        cboModalityTR.setValue(null);
        cboLGAC.setValue(null);
        cboAcademicStaff.setValue(null);
        cboDirector.setValue(null);
        cboRole.setValue(null);
        cboTeacher.setValue(null);
        CLIKS = 4;
        lbCount.setText(String.valueOf(CLIKS));

    }
    
    @FXML
    private void logOut(ActionEvent event) throws IOException {
        sgerypg.setRoot("/mx/uv/fei/GUI/Login");
    }
    
}
