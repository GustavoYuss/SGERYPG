package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mx.uv.fei.Logic.IPreProject;
import mx.uv.fei.Logic.PreProject;

public class PreProjectDAO implements IPreProject {
    
    public ArrayList<PreProject> consultPreProjects( ) throws SQLException {
        
        ArrayList<PreProject> preProjects = new ArrayList<PreProject>();
        String query = "Select * from AnteProyectos";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            PreProject preProject = new PreProject();
            preProject.setTitle(resultSet.getString("Titulo"));
            preProject.setNameReceptionWork(resultSet.getString("nombreTrabajoRecepcional"));
            preProject.setLGCA(resultSet.getInt("idLGCA"));
            preProject.setStatus(resultSet.getString("Estado"));
            preProject.setRequirements(resultSet.getString("Requisitos"));
            preProject.setModality(resultSet.getInt("Modalidad"));
            preProject.setDescription(resultSet.getString("Descripcion"));
            preProjects.add(preProject);
        }

        connection.close();
        
        return preProjects;
    }

    public int addPreProject(PreProject preProject) throws SQLException {
        int result = 0;
        String query = "insert into Anteproyecto(titulo, nombreTrabajoRecepcional, descripcion, estado, requisitos, modalidad, idLgca, idEstado) values(?,?,?,?,?,?,?,?)";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, preProject.getTitle());
        statement.setString(2, preProject.getNameReceptionWork());
        statement.setString(3, preProject.getDescription());
        statement.setString(4, preProject.getStatus());
        statement.setString(5, preProject.getRequirements());
        statement.setInt(6, preProject.getModality());
        statement.setInt(7, preProject.getLGCA());
        statement.setString(8, preProject.getStatus());

        result = statement.executeUpdate();
        return result;
    }
}