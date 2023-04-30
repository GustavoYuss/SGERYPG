package mx.uv.fei.controllers;

import mx.uv.fei.controllers.StudentTableModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mx.uv.fei.DataAccess.SchoolGroupDAO;
import mx.uv.fei.Logic.SchoolGroup;
import mx.uv.fei.Logic.User;
import sgerypg.sgerypg;

public class StudentRegistrationController implements Initializable {
    
    @FXML
    private TableView<StudentTableModel> tblEnrolledStudents;
    
    @FXML
    private TableColumn<StudentTableModel, String> tblNameColumn;
    
    @FXML
    private TableColumn<StudentTableModel, String> tblLastNameColumn;
    
    @FXML
    private TableColumn<StudentTableModel, String> tblMotherLastNameColumn;
    
    @FXML
    private TableColumn<StudentTableModel, String> tblKeyColumn;
    
    @FXML
    private TableColumn<StudentTableModel, String> tblInstitutionalMailColumn;
    
    StudentTableModel TABLE_STUDENTS_WITHOUT_COURSE = new StudentTableModel();
    
    public static ModelGroupTable SELECTED_GROUP = new ModelGroupTable();
    
    User STUDENT = new User();
    
    SchoolGroup GROUP = new SchoolGroup();
    
    //SGERYPG sgerypg = new MenuPrincipal();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            addStudentsTable(TABLE_STUDENTS_WITHOUT_COURSE.getStudentsWithoutCourse());
        } catch (SQLException ex) {
            Logger.getLogger(StudentRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void exitStudentRegistration(ActionEvent event) throws IOException {
        sgerypg.setRoot("/mx/uv/fei/GUI/GroupDetails");
    }

    public static void courseObject(ModelGroupTable object) {
        SELECTED_GROUP = object;
    }
            
    public void getItemDetails() throws IndexOutOfBoundsException {
        TablePosition tablePosition = tblEnrolledStudents.getSelectionModel().getSelectedCells().get(0);

        if (tablePosition.getRow() == -1) {
            throw new IndexOutOfBoundsException("No se ha seleccionado ningún elemento en la tabla.");
        }

        int row = tablePosition.getRow();

        STUDENT.setFirstName((String) tblEnrolledStudents.getColumns().get(0).getCellObservableValue(row).getValue());
        STUDENT.setLastName((String) tblEnrolledStudents.getColumns().get(1).getCellObservableValue(row).getValue());
        STUDENT.setMothersLastName((String) tblEnrolledStudents.getColumns().get(2).getCellObservableValue(row).getValue());
        STUDENT.setKey((String) tblEnrolledStudents.getColumns().get(3).getCellObservableValue(row).getValue());
        STUDENT.setInstitutionalMail((String) tblEnrolledStudents.getColumns().get(4).getCellObservableValue(row).getValue());
    }
    
    public void prepareObjectGroup(){
        GROUP.setSection(SELECTED_GROUP.getSection());
        GROUP.setSlots(SELECTED_GROUP.getSlots());
        GROUP.setNrc(SELECTED_GROUP.getNRC());
        GROUP.setSchoolPeriod(SELECTED_GROUP.getSchoolPeriod());
    }

    @FXML
    private void addStudentsTable(ObservableList<StudentTableModel> studentslist){
        
        this.tblNameColumn.setCellValueFactory(new PropertyValueFactory("Nombre"));
        this.tblLastNameColumn.setCellValueFactory(new PropertyValueFactory("Apellido Paterno"));
        this.tblMotherLastNameColumn.setCellValueFactory(new PropertyValueFactory("Apellido Materno"));
        this.tblKeyColumn.setCellValueFactory(new PropertyValueFactory("Matricula"));
        this.tblInstitutionalMailColumn.setCellValueFactory(new PropertyValueFactory("Correo Institucional"));
                
        tblNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblMotherLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("motherLastName"));
        tblKeyColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        tblInstitutionalMailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            
        tblEnrolledStudents.setItems(studentslist);
    }
    
    @FXML
    private void registerStudentGroup(ActionEvent event) throws IOException {
        SchoolGroupDAO rergister = new SchoolGroupDAO();
        getItemDetails();
        prepareObjectGroup();
        
        if (tblEnrolledStudents.getSelectionModel().getSelectedItems().size() > 0) {
            try {
                System.out.println(STUDENT.getFirstName());
                System.out.println(GROUP.getIdEducationalExperience());
                rergister.enrollStudentInGroup(STUDENT, GROUP);
            } catch (SQLException ex) {
                System.out.println("Se ha producido una excepción SQLException:");
                System.out.println("Mensaje: " + ex.getMessage());
                System.out.println("Código de error: " + ex.getErrorCode());
                System.out.println("Estado SQL: " + ex.getSQLState());
                ex.printStackTrace();
            }
        } else {
          System.out.println("No se ha seleccionado ningún elemento en la tabla.");
        }
    }
}
