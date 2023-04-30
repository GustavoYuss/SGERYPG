package mx.uv.fei.DataAccess;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mx.uv.fei.Logic.PreliminaryProject;
import mx.uv.fei.Logic.IPreliminaryProject;

public class PreliminaryProjectDAO implements IPreliminaryProject {
    
    public final static int VALID = 0;
    public final static int INVALID = 1;
    
    @Override
    public ArrayList<PreliminaryProject> consultAllPreliminaryProjects( ) throws SQLException {
        
        ArrayList<PreliminaryProject> preliminaryProjectsList = new ArrayList<PreliminaryProject>();
        String query = "Select * from AnteProyectos";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
                        
            PreliminaryProject preProject = new PreliminaryProject();
            
            preProject.setNameProjectResearchProject(resultSet.getString("nombreProyectoInvestigacion"));
            preProject.setNameReceptionWork(resultSet.getString("nombreTrabajoRecepcional"));
            preProject.setStatus(resultSet.getInt("idestado"));
            preProject.setRequirements(resultSet.getString("Requisitos"));
            preProject.setModality(resultSet.getInt("idmodalidad"));
            preProject.setLGCA(resultSet.getInt("idLGCA"));
            preProject.setResearchline(resultSet.getString("LineaInvestigacion"));
            preProject.setDuration(resultSet.getInt("duracion"));
            preProject.setDescriptionProjectInv(resultSet.getString("descripcionProyectoInv"));
            preProject.setDescriptionReceptionWork(resultSet.getString("descripcionTrabajoRecepcional"));
            preProject.setExpectedResults(resultSet.getString("resultadosEsperados"));

            preliminaryProjectsList.add(preProject);
        }

        connection.close();
        
        return preliminaryProjectsList;
    }

    public int addPreliminaryProject(PreliminaryProject preProject) throws SQLException {
        int result = 0;
        int checkPreProject = checkIfPreliminaryProjectIsAlreadyRegistered(preProject);
        
        if(checkPreProject == VALID){
            String query = "insert into Anteproyecto(nombreProyectoInvestigacion , "
                + "nombreTrabajorecepcional, idEstado, Requisitos, idModalidad, "
                + "idGlca, LineaInvestigacion, duracion, descripcionProyectoInv, "
                + "descripcionTrabajoRecepcional, resultadosEsperados, idCuerpoacademico) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, preProject.getNameProjectResearchProject());
            statement.setString(2, preProject.getNameReceptionWork());
            statement.setInt(3, preProject.getStatus());
            statement.setString(4, preProject.getRequirements());
            statement.setInt(5, preProject.getModality());
            statement.setInt(6, preProject.getLGCA());
            statement.setString(7, preProject.getResearchline());
            statement.setInt(8, preProject.getDuration());
            statement.setString(9,preProject.getDescriptionProjectInv());
            statement.setString(10, preProject.getDescriptionReceptionWork());
            statement.setString(11, preProject.getExpectedResults());
            statement.setInt(12, preProject.getAcademicStaff());

            result = statement.executeUpdate();
        }
        
        return checkPreProject;
    }
    
     public int checkIfPreliminaryProjectIsAlreadyRegistered(PreliminaryProject preProject) throws SQLException {
        
        int consultationResult = 0;
        String query = "SELECT COUNT(*) as verificar FROM anteproyecto "
            + "WHERE nombreTrabajoRecepcional = ?;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, preProject.getNameReceptionWork());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            if (resultSet.getInt("verificar") == 1){
                consultationResult = INVALID;
            } 
        }

        return consultationResult;
        
    }
    
    public int registerPreliminaryProjectResponsibles(ArrayList<String> teachersList, ArrayList<Integer> roles, 
        String nameReceptionWork) throws SQLException {
        
        int result = 0;
        
        for(int i = 0; i < teachersList.size(); i++){
            String query = "insert into responsablesanteProyecto (docente, rol, anteproyecto) "
                + "values(?,?,?);";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            UserDAO teacher = new UserDAO();
        
            statement.setInt(1, teacher.getIdUser(teachersList.get(i)));
            statement.setInt(2, roles.get(i));
            statement.setInt(3, getIdPreliminaryProject(nameReceptionWork));
            result = statement.executeUpdate();
        }    
        return result;   
    }
        
    public ArrayList<String> consultAllLgca() throws SQLException{
        
        ArrayList<String> lgcaList = new ArrayList<String>();
        String query = "Select lgca from lgca";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            lgcaList.add(resultSet.getString("lgca"));
        }
        
        return lgcaList;
    }
    
    public ArrayList<String> consultAllModality() throws SQLException{
        
        ArrayList<String> modalityList = new ArrayList<String>();
        String query = "Select modalidad from modalidad";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            modalityList.add(resultSet.getString("modalidad"));
        }
        
        return modalityList;
    }
    
    public ArrayList<String> consultAllRoles() throws SQLException{
        
        ArrayList<String> listRoles = new ArrayList<String>();
        String query = "SELECT rol FROM rol WHERE rol <> 'director';"; 

        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            listRoles.add(resultSet.getString("rol"));
        }
        
        return listRoles;
    }
    
    public ArrayList<String> consultAllAcademicStaff() throws SQLException{
        
        ArrayList<String> listAcademicStaff = new ArrayList<String>();
        String query = "Select CuerpoAcademico from cuerpoacademico";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            listAcademicStaff.add(resultSet.getString("CuerpoAcademico"));
        }
        
        return listAcademicStaff;
    }
    
    public int getIdModality (String modality) throws SQLException{
        
        String query = "Select idModalidad from modalidad where modalidad = ?";
        
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, modality);
        
        ResultSet resultSet = statement.executeQuery();
        
        int typeModality  = 0;
        
        if (resultSet.next()) {
            typeModality = resultSet.getInt("idModalidad");
        }
        
        return typeModality;
    }
    
    public int getIdLgca(String lgca) throws SQLException{
        
        String query = "Select idLgca from Lgca where Lgca = ?";
        
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, lgca);
        
        ResultSet resultSet = statement.executeQuery();
        
        int typeLgca  = 0;
        
        if (resultSet.next()) {
            typeLgca = resultSet.getInt("idLgca");
        }
        
        return typeLgca;
    }
    
    public int getIdRole(String role) throws SQLException{
        
        String query = "Select idRol from rol where rol = ?";
        
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, role);
        
        ResultSet resultSet = statement.executeQuery();
        
        int typeRole  = 0;
        
        if (resultSet.next()) {
            typeRole = resultSet.getInt("idRol");
        }
        
        return typeRole;
    
    }
    
    public int getIdAcademicStaff(String academicStaff) throws SQLException{
        
        String query = "Select idCuerpoAcademico from cuerpoAcademico "
            + "where cuerpoAcademico = ?";
        
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, academicStaff);
        
        ResultSet resultSet = statement.executeQuery();
        
        int typeAcademicStaff  = 0;
        
        if (resultSet.next()) {
            typeAcademicStaff = resultSet.getInt("idCuerpoAcademico");
        }
        
        return typeAcademicStaff;
    
    }
    
    public int getIdPreliminaryProject(String nameReceptionWork) throws SQLException{
        
        String query = "Select idanteproyecto from anteproyecto "
            + "where nombreTrabajoRecepcional = ?";
        
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, nameReceptionWork);
        
        ResultSet resultSet = statement.executeQuery();
        
        int idPreliminaryProject  = 0;
        
        if (resultSet.next()) {
            idPreliminaryProject = resultSet.getInt("idanteproyecto");
        }
        
        return idPreliminaryProject;
        
    }
}