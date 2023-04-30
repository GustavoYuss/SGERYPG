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
import mx.uv.fei.Logic.User;

/**
 *
 * @author Migue
 */
public class StudentTableModel {
    
    private int id;
    private String name;
    private String lastName;
    private String motherLastName;
    private String key;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentTableModel(int id, String name, String lastName, String motherLastName, String key, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.key = key;
        this.email = email;
    }
    
    public StudentTableModel(){
        
    }
    
    public ObservableList<StudentTableModel> getStudentsWithoutCourse() throws SQLException {
        
    ObservableList<StudentTableModel> studentsList = FXCollections.observableArrayList();
    SchoolGroupDAO schoolGroupDao = new SchoolGroupDAO();
            
    try {
        ArrayList<User> students = schoolGroupDao.ShowStudentsWithoutCourse();
        for (User student : students) {
            studentsList.add(new StudentTableModel(student.getId(), student.getFirstName(), student.getLastName(),
                student.getMothersLastName(), student.getKey(), student.getInstitutionalMail()));
        }
    } catch (SQLException exception) {
        Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
    }
        
    return studentsList;
}

    
    public ObservableList<StudentTableModel> getListStudentsFromGroup(String nrc){
        
       ObservableList<StudentTableModel> studentslist = FXCollections.observableArrayList();
       SchoolGroupDAO student = new SchoolGroupDAO();
            
        try {
            ArrayList<User> students = student.viewListStudents(student.getGroupIdFromDatabase(nrc));
            for (User Alumn : students){
                studentslist.add(new StudentTableModel(Alumn.getId(), Alumn.getFirstName(), Alumn.getLastName(), Alumn.getMothersLastName(), 
                        Alumn.getKey(), Alumn.getInstitutionalMail()));
            }
        } catch (SQLException exception) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, exception);
        }
        
        return studentslist;
    }     
}
