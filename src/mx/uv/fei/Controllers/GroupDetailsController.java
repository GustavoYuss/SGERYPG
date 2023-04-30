/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.controllers;

import mx.uv.fei.controllers.ModelGroupTable;
import mx.uv.fei.controllers.StudentRegistrationController;
import mx.uv.fei.controllers.StudentTableModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sgerypg.sgerypg;

/**
 *
 * @author Migue
 */
public class GroupDetailsController implements Initializable {

    @FXML
    private TableView<StudentTableModel> tblEnrolledStudents;

    @FXML
    private TableColumn<StudentTableModel, String> colName;

    @FXML
    private TableColumn<StudentTableModel, String> colLastName;

    @FXML
    private TableColumn<StudentTableModel, String> colMotherLastName;

    @FXML
    private TableColumn<StudentTableModel, String> colKey;

    @FXML
    private TableColumn<StudentTableModel, String> colInstitutionalMail;
    
    @FXML
    private Label ibSchoolPeriodLabel;
    
    @FXML
    private Label lbNRCLabel;
    
    @FXML
    private Label lbSlotsLabel;
    
    @FXML
    private Label lbSectionLabel;
    
    @FXML
    private Label lbTeacherLabel;
    
    @FXML
    private Label lbEducationalExperience;

    StudentTableModel TABLE = new StudentTableModel();

    public static ModelGroupTable dataImport = new ModelGroupTable();

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        addStudentsTable(TABLE.getListStudentsFromGroup(dataImport.getNRC()));
        showGroupData();
    }
        
    public static void setData(ModelGroupTable aux){
        dataImport = aux;
    }
    
    @FXML
    private void addStudentsTable(ObservableList<StudentTableModel> studentslist) {

        this.colName.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.colLastName.setCellValueFactory(new PropertyValueFactory("Apellido Paterno"));
        this.colMotherLastName.setCellValueFactory(new PropertyValueFactory("Apellido Materno"));
        this.colKey.setCellValueFactory(new PropertyValueFactory("Matricula"));
        this.colInstitutionalMail.setCellValueFactory(new PropertyValueFactory("Correo Institucional"));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colMotherLastName.setCellValueFactory(new PropertyValueFactory<>("motherLastName"));
        colKey.setCellValueFactory(new PropertyValueFactory<>("key"));
        colInstitutionalMail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tblEnrolledStudents.setItems(studentslist);
    }
    
    private void showGroupData() {
        ibSchoolPeriodLabel.setText(dataImport.getSchoolPeriod());
        lbNRCLabel.setText(dataImport.getNRC());
        lbSlotsLabel.setText(String.valueOf(dataImport.getSlots()));
        lbSectionLabel.setText(dataImport.getSection());
        lbTeacherLabel.setText(dataImport.getTeacher());
        lbEducationalExperience.setText(dataImport.getEducationalExperience());
    }


    @FXML
    private void openStudentRegistration(ActionEvent event) throws IOException {
        StudentRegistrationController coneccion = new StudentRegistrationController();
            coneccion.courseObject(dataImport);
            sgerypg.setRoot("/mx/uv/fei/GUI/StudentRegistration");
    }
   
    @FXML
    private void exitGroupsDetails(ActionEvent event) throws IOException {
        sgerypg.setRoot("/mx/uv/fei/GUI/Groups");
    }
    
}
