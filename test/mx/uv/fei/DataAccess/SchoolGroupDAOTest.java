/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.util.ArrayList;
import mx.uv.fei.Logic.SchoolGroup;
import mx.uv.fei.Logic.User;
import mx.uv.fei.controllers.ModelGroupTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Migue
 */
public class SchoolGroupDAOTest {
    
    public SchoolGroupDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddClass() throws Exception {
        System.out.println("addClass");
        SchoolGroup group = new SchoolGroup();
        
        group.setIdEducationalExperience(2);
        group.setIdTeacher(2);
        group.setNrc("12110");
        group.setSchoolPeriod("Enero-Febrero");
        group.setSection("Matutino");
        group.setSlots(30);
        group.setEstatus(1);
        
        SchoolGroupDAO instance = new SchoolGroupDAO();
        int expResult = 1;
        int result = instance.addClass(group);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetGroupIdFromDatabase() throws Exception {
        System.out.println("getGroupIdFromDatabase");
        String nrc = "12110";
        SchoolGroupDAO instance = new SchoolGroupDAO();
        int expResult = 59;
        int result = instance.getGroupIdFromDatabase(nrc);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckIfGroupIsAlreadyRegistered() throws Exception {
        System.out.println("checkIfGroupIsAlreadyRegistered");
        SchoolGroup group = new SchoolGroup();
        
        group.setIdEducationalExperience(2);
        group.setIdTeacher(2);
        group.setNrc("12110");
        group.setSchoolPeriod("Enero-Febrero");
        group.setSection("Matutino");
        group.setSlots(30);
        group.setEstatus(1);
        
        SchoolGroupDAO instance = new SchoolGroupDAO();
        int expResult = 1;
        int result = instance.checkIfGroupIsAlreadyRegistered(group);
        assertEquals(expResult, result);
    }

    @Test
    public void testEnrollStudentInGroup() throws Exception {
        System.out.println("enrollStudentInGroup");
        User student = new User();
        
        student.setFirstName("Alma");
        student.setSecondName("Alberto");
        student.setLastName("Martínez ");
        student.setMothersLastName("Cruz");
        student.setInstitutionalMail("martinezcruz@estudiantes.mx");
        student.setTypeUser(1);
        student.setKey("zS14387");
                
                
        SchoolGroup group = new SchoolGroup();
        
        group.setIdEducationalExperience(2);
        group.setIdTeacher(2);
        group.setNrc("12110");
        group.setSchoolPeriod("Enero-Febrero");
        group.setSection("Matutino");
        group.setSlots(30);
        group.setEstatus(1);
        
        SchoolGroupDAO instance = new SchoolGroupDAO();
        int expResult = 1;
        int result = instance.enrollStudentInGroup(student, group);
        assertEquals(expResult, result);
    }

    @Test
    public void testUpdateSlotsGroup() throws Exception {
        System.out.println("updateSlotsGroup");
        SchoolGroup group = new SchoolGroup();
        
        group.setIdEducationalExperience(2);
        group.setIdTeacher(2);
        group.setNrc("12110");
        group.setSchoolPeriod("Enero-Febrero");
        group.setSection("Matutino");
        group.setSlots(30);
        group.setEstatus(1);
        
        SchoolGroupDAO instance = new SchoolGroupDAO();
        instance.updateSlotsGroup(group);
    }

    @Test
    public void testCheckStudentAvailability() throws Exception {
        System.out.println("checkStudentAvailability");
        
        User student = new User();
        
        student.setId(13);
        student.setFirstName("Alma");
        student.setSecondName("Alberto");
        student.setLastName("Martínez ");
        student.setMothersLastName("Cruz");
        student.setInstitutionalMail("martinezcruz@estudiantes.mx");
        student.setTypeUser(1);
        student.setKey("zS14387");
        
        SchoolGroupDAO instance = new SchoolGroupDAO();
        int expResult = 1;
        int result = instance.checkStudentAvailability(student);
        assertEquals(expResult, result);

    }

    @Test
    public void testViewListStudents() throws Exception {
        System.out.println("viewListStudents");
        int idGroup = 0;
        SchoolGroupDAO instance = new SchoolGroupDAO();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.viewListStudents(idGroup);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetRegisteredGroupsData() throws Exception {
        System.out.println("getRegisteredGroupsData");
        SchoolGroupDAO instance = new SchoolGroupDAO();
        ArrayList<ModelGroupTable> expResult = null;
        ArrayList<ModelGroupTable> result = instance.getRegisteredGroupsData();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowStudentsWithoutCourse() throws Exception {
        System.out.println("ShowStudentsWithoutCourse");
        SchoolGroupDAO instance = new SchoolGroupDAO();
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.ShowStudentsWithoutCourse();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
