/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static mx.uv.fei.DataAccess.SchoolGroupDAO.DATA_NOT_FOUND;
import static mx.uv.fei.DataAccess.SchoolGroupDAO.INVALID;
import static mx.uv.fei.DataAccess.SchoolGroupDAO.VALID;
import mx.uv.fei.controllers.ModelGroupTable;
import mx.uv.fei.Logic.SchoolGroup;
import mx.uv.fei.Logic.User;

/**
 *
 * @author Migue
 */
public interface ISchoolGroup {
    
    public int addClass(SchoolGroup group) throws SQLException;

    public int getGroupIdFromDatabase(String nrc) throws SQLException;

    public int checkIfGroupIsAlreadyRegistered(SchoolGroup group) throws SQLException;
    
    public int enrollStudentInGroup(User student, SchoolGroup group) throws SQLException;

    public void updateSlotsGroup(SchoolGroup group) throws SQLException;
        
    public ArrayList<ModelGroupTable> getRegisteredGroupsData() throws SQLException;

    public ArrayList<User> ShowStudentsWithoutCourse() throws SQLException;
    
    public int checkStudentAvailability(User student) throws SQLException;
    
    public ArrayList<User> viewListStudents (int idGroup) throws SQLException;
}
