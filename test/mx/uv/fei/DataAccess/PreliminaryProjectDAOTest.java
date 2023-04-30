/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import mx.uv.fei.Logic.PreliminaryProject;
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
public class PreliminaryProjectDAOTest {
    
    public PreliminaryProjectDAOTest() {
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
    public void testConsultAllPreProjects() throws Exception {
        System.out.println("consultAllPreProjects");
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        ArrayList<PreliminaryProject> expResult = null;
        ArrayList<PreliminaryProject> result = instance.consultAllPreliminaryProjects();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSuccessfulAddPreProject() throws Exception {
        System.out.println("addPreProject");
        PreliminaryProject preProject = new PreliminaryProject();
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        
        preProject.setNameProjectResearchProject("Calidad de software");
        preProject.setNameReceptionWork("Seguridad de software");
        preProject.setDuration(6);
        preProject.setRequirements("Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos");
        preProject.setDescriptionProjectInv("En la medida que el software cobra mayor relevancia en las actividades diarias de una  sociedad, el interés por tener productos de software confiables se incrementa. Algunos estudios muestran una alta prevalencia de problemas de calidad en los productos de software que se desarrollan y la relación de estos problemas con la experiencia y capacitación de programadores. Por otra parte, ha habido un avance considerable en el diseño de estrategias para mejora de la calidad del software, sin embargo, en algunas organizaciones existe resistencia en la adopción de prácticas orientadas a la incorporación de dichas estrategias. Por otra parte, el aspecto humano juega un papel fundamental en el desarrollo de productos de software de calidad. Existen estudios donde se definen algunas hipótesis orientadas a validar si aspectos de diversidad de personalidad están relacionadas positivamente en la calidad del producto final.");
        preProject.setDescriptionReceptionWork("El presente trabajo tiene como finalidad, realizar una revisión sistemática de la literatura  sobre la diversidad (personalidad, género, entre otros aspectos) de los equipos de desarrollo  y la relación que guardan con la calidad de los productos de software que desarrollan. \n" +
        "Adicionalmente se pretende que la revisión sistemática documente el impacto que tienen los  hallazgos identificados en la revisión en los productos de software");
        preProject.setExpectedResults("Reporte de la revisión sistemática de la literatura y Artículo para publicación en evento académico");
        preProject.setLGCA(1);
        preProject.setModality(1);
        preProject.setResearchline("**omitir**");
        preProject.setStatus(4);
        preProject.setAcademicStaff(1);
        
        int expResult = 0;
        int result = 0;
        try {
            result = instance.addPreliminaryProject(preProject);
        } catch (SQLException exception) {
            String errorMessage = "Error al agregar el preproyecto a la base de datos: " + exception.getMessage();
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, errorMessage, exception);
        }
        assertEquals(expResult, result);

    }
    
    @Test
    public void testErrorAddPreProject() throws Exception {
        System.out.println("addPreProject");
        PreliminaryProject preProject = new PreliminaryProject();
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        
        preProject.setNameProjectResearchProject("Calidad de software");
        preProject.setNameReceptionWork("Ingenieria Artificial");
        preProject.setDuration(6);
        preProject.setRequirements("Tecnologías para la construcción de software, Principios de Construcción de Software, Diseño de Software, Procesos de Software, Administración de proyectos");
        preProject.setDescriptionProjectInv("En la medida que el software cobra mayor relevancia en las actividades diarias de una  sociedad, el interés por tener productos de software confiables se incrementa. Algunos estudios muestran una alta prevalencia de problemas de calidad en los productos de software que se desarrollan y la relación de estos problemas con la experiencia y capacitación de programadores. Por otra parte, ha habido un avance considerable en el diseño de estrategias para mejora de la calidad del software, sin embargo, en algunas organizaciones existe resistencia en la adopción de prácticas orientadas a la incorporación de dichas estrategias. Por otra parte, el aspecto humano juega un papel fundamental en el desarrollo de productos de software de calidad. Existen estudios donde se definen algunas hipótesis orientadas a validar si aspectos de diversidad de personalidad están relacionadas positivamente en la calidad del producto final.");
        preProject.setDescriptionReceptionWork("El presente trabajo tiene como finalidad, realizar una revisión sistemática de la literatura  sobre la diversidad (personalidad, género, entre otros aspectos) de los equipos de desarrollo  y la relación que guardan con la calidad de los productos de software que desarrollan. \n" +
        "Adicionalmente se pretende que la revisión sistemática documente el impacto que tienen los  hallazgos identificados en la revisión en los productos de software");
        preProject.setExpectedResults("Reporte de la revisión sistemática de la literatura y Artículo para publicación en evento académico");
        preProject.setLGCA(1);
        preProject.setModality(1);
        preProject.setResearchline("**omitir**");
        preProject.setStatus(4);
        preProject.setAcademicStaff(1);
        
        int expResult = 1;
        int result = 0;
        try {
            result = instance.addPreliminaryProject(preProject);
        } catch (SQLException exception) {
            String errorMessage = "Error al agregar el preproyecto a la base de datos: " + exception.getMessage();
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, errorMessage, exception);
        }
        assertEquals(expResult, result);

    }

    @Test
    public void testRegisterPreliminaryProjectResponsibles() throws Exception {
        System.out.println("registerPreliminaryProjectResponsibles");
        ArrayList<String> teachersList = new ArrayList<>();
        ArrayList<Integer> roles = new ArrayList<>();
        
        teachersList.add("281657910");
        teachersList.add("zS57693200");
        teachersList.add("zS39041");
        
        roles.add(1);
        roles.add(2);
        roles.add(3);
        
        String nameReceptionWork = "Diversidad en equipos de desarrollo y su relación con la calidad de software";
        
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        
        int expResult = 1;
        int result = instance.registerPreliminaryProjectResponsibles(teachersList, roles, nameReceptionWork);
        assertEquals(expResult, result);

    }

    @Test
    public void testConsultAllLgca() throws Exception {
        System.out.println("consultAllLgca");
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("L1. Gestion, modelado y desarrollo de software.");
        expResult.add("L1. Aprendizaje Computacional");
        expResult.add("L2. Tecnologías de software.");
        
        
        ArrayList<String> result = instance.consultAllLgca();
        assertEquals(expResult, result);

    }

    @Test
    public void testConsultAllModality() throws Exception {
        System.out.println("consultAllModality");
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Revision Sistematica de la Literatura");
        expResult.add("Tesis");
        expResult.add("Tesina");
        expResult.add("Articulo");
        expResult.add("Revision Multivocal de la Literatura");
        
        ArrayList<String> result = instance.consultAllModality();
        assertEquals(expResult, result);

    }

    @Test
    public void testConsultAllRoles() throws Exception {
        System.out.println("consultAllRoles");
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Codirector");
        expResult.add("Sinodal");
        
        ArrayList<String> result = instance.consultAllRoles();
        assertEquals(expResult, result);

    }

    @Test
    public void testConsultAllAcademicStaff() throws Exception {
        System.out.println("consultAllAcademicStaff");
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        ArrayList<String> expResult = new ArrayList<>();
        expResult.add("Ingeniería y Tecnologías de Software");

        
        ArrayList<String> result = instance.consultAllAcademicStaff();
        assertEquals(expResult, result);
    }
    

    @Test
    public void testGetIdModality() throws Exception {
        System.out.println("getIdModality");
        String modality = "Tesis";
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        
        int expResult = 2;
        int result = instance.getIdModality(modality);
        assertEquals(expResult, result);

    }

    @Test
    public void testGetIdLgca() throws Exception {
        System.out.println("getIdLgca");
        String lgca = "L1. Aprendizaje Computacional";
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        int expResult = 4;
        int result = instance.getIdLgca(lgca);
        
        assertEquals(expResult, result);

    }

    @Test
    public void testGetIdRole() throws Exception {
        System.out.println("getIdRole");
        String role = "Sinodal";
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        int expResult = 3;
        int result = instance.getIdRole(role);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testGetIdAcademicStaff() throws Exception {
        System.out.println("getIdAcademicStaff");
        String academicStaff = "Ingeniería y Tecnologías de Software";
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        int expResult = 1;
        int result = instance.getIdAcademicStaff(academicStaff);
        
        assertEquals(expResult, result);
    }

    @Test
    public void testGetIdPreliminaryProject() throws Exception {
        System.out.println("getIdPreliminaryProject");
        String nameReceptionWork = "Diversidad en equipos de desarrollo y su relación con la calidad de software";
        PreliminaryProjectDAO instance = new PreliminaryProjectDAO();
        int expResult = 25;
        int result = instance.getIdPreliminaryProject(nameReceptionWork);
        
        assertEquals(expResult, result);
    }
    
}
