/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mx.uv.fei.controllers;

import mx.uv.fei.controllers.ModelGroupTable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import sgerypg.sgerypg;

/**
 * FXML Controller class
 *
 * @author Migue
 */
public class GroupsController implements Initializable {
    
    sgerypg scene = new sgerypg();
    
    @FXML
    private TableColumn<ModelGroupTable, String> tblEducationalExperienceColumn;

    @FXML
    private TableColumn<ModelGroupTable, String> tblNrcColumn;

    @FXML
    private TableColumn<ModelGroupTable, String> tblSchoolPeriodColumn;

    @FXML
    private TableColumn<ModelGroupTable, String> tblSectionColumn;

    @FXML
    private TableColumn<ModelGroupTable, Integer> tblSlotsColumn;

    @FXML
    private TableColumn<ModelGroupTable, String> tblStatusColumn;

    @FXML
    private TableColumn<ModelGroupTable, String> tblTeacherColumn;

    @FXML
    private TableView<ModelGroupTable> tblGroupData;
    
    ModelGroupTable groupDetailsTable = new ModelGroupTable();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         fillTableGroups(groupDetailsTable.getDataFromSchoolGroups());
    }
    
    @FXML
    private void fillTableGroups(ObservableList<ModelGroupTable> Groupslist){
        
        this.tblEducationalExperienceColumn.setCellValueFactory(new PropertyValueFactory("educationalExperience"));
        this.tblNrcColumn.setCellValueFactory(new PropertyValueFactory("NRC"));
        this.tblSchoolPeriodColumn.setCellValueFactory(new PropertyValueFactory("SchoolPeriod"));
        this.tblSectionColumn.setCellValueFactory(new PropertyValueFactory("section"));
        this.tblSlotsColumn.setCellValueFactory(new PropertyValueFactory("slots"));
        this.tblStatusColumn.setCellValueFactory(new PropertyValueFactory("Status"));
        this.tblTeacherColumn.setCellValueFactory(new PropertyValueFactory("Teacher"));

        tblGroupData.setItems(Groupslist);
    }

    public void retrieveDataTable() {
        TablePosition tablePosition = tblGroupData.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        ModelGroupTable item = tblGroupData.getItems().get(row);
        groupDetailsTable.setSchoolPeriod((String) tblGroupData.getColumns().get(0).getCellObservableValue(item).getValue());
        groupDetailsTable.setSection((String) tblGroupData.getColumns().get(1).getCellObservableValue(item).getValue());
        groupDetailsTable.setSlots((int) tblGroupData.getColumns().get(2).getCellObservableValue(item).getValue());
        groupDetailsTable.setEducationalExperience((String) tblGroupData.getColumns().get(3).getCellObservableValue(item).getValue());
        groupDetailsTable.setNRC((String) tblGroupData.getColumns().get(4).getCellObservableValue(item).getValue());
        groupDetailsTable.setTeacher((String) tblGroupData.getColumns().get(5).getCellObservableValue(item).getValue());
        groupDetailsTable.setStatus((String) tblGroupData.getColumns().get(6).getCellObservableValue(item).getValue());
    }
    
    @FXML
    private void openGroupDetailsWindow(ActionEvent event) throws IOException {
        if(tblGroupData.getSelectionModel().getSelectedItems().size() > 0){ 
            retrieveDataTable();
            GroupDetailsController groupDetailsController = new GroupDetailsController();
            groupDetailsController.setData(groupDetailsTable);
            scene.setRoot("/mx/uv/fei/GUI/GroupDetails");
        }
    }
        
    @FXML
    private void openGroupRegistrationWindow(ActionEvent event) throws IOException {
        scene.setRoot("/mx/uv/fei/GUI/RegisterGroup");
    }   
}

