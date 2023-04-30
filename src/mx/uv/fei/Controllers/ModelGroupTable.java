/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mx.uv.fei.DataAccess.DataBaseManager;
import mx.uv.fei.DataAccess.SchoolGroupDAO;

/**
 *
 * @author Migue
 */
public class ModelGroupTable {
    
    private int id;
    private String SchoolPeriod;
    private String section;
    private int slots;
    private String educationalExperience;
    private String teacher;
    private String status;
    private String NRC;
    
    public ModelGroupTable(int id, String SchoolPeriod, String section, int slots, 
            String EducationalExperience, String teacher, String status, String NRC) {
        
        this.id = id;
        this.SchoolPeriod = SchoolPeriod;
        this.section = section;
        this.slots = slots;
        this.educationalExperience = EducationalExperience;
        this.teacher = teacher;
        this.status = status;
        this.NRC = NRC;
        
    }

    public ModelGroupTable() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolPeriod() {
        return SchoolPeriod;
    }

    public void setSchoolPeriod(String SchoolPeriod) {
        this.SchoolPeriod = SchoolPeriod;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public String getEducationalExperience() {
        return educationalExperience;
    }

    public void setEducationalExperience(String EducationalExperience) {
        this.educationalExperience = EducationalExperience;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNRC() {
        return NRC;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public ObservableList<ModelGroupTable> getDataFromSchoolGroups() {
        ObservableList<ModelGroupTable> groupsList = FXCollections.observableArrayList();
        SchoolGroupDAO group = new SchoolGroupDAO();

        try {
            ArrayList<ModelGroupTable> groups = group.getRegisteredGroupsData();
            for (ModelGroupTable groupsAux : groups) {
                groupsList.add(new ModelGroupTable(groupsAux.getId(), groupsAux.getSchoolPeriod(), 
                  groupsAux.getSection(), groupsAux.getSlots(), groupsAux.getEducationalExperience(), 
                  groupsAux.getTeacher(), groupsAux.getStatus(), groupsAux.getNRC()));
            }
        } catch (SQLException exception) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
        
        return groupsList;
    }
    
    
}
