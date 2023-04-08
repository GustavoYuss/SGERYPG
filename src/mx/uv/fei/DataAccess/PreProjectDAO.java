package mx.uv.fei.Logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mx.uv.fei.DataAccess.DataBaseManager;

public class PreProjectDAO implements IPreProject {
    
    public ArrayList<PreProject> consultPreProjects( ) throws SQLException {
        
        ArrayList<PreProject> preProjects = new ArrayList();
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

    @Override
    public int addPreProject(PreProject preProject) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPreProject'");
    }

}
