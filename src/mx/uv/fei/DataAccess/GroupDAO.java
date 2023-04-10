/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import mx.uv.fei.Logic.Group;

/**
 *
 * @author dany_
 */
public class GroupDAO {
    public int addGroup(Group group) throws SQLException{
    String query = "insert into (periodoEscolar, seccion, cupo, idExperienciaeducativa, iddocente) values(?,?,?,?,?)";
            DataBaseManager dataBaseManager= new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, group.getPeriodSchool());
            statement.setString(2, group.getSection());
            statement.setString(3, group.getQuota());
            statement.setString(4, group.getIdEducationalExperience());
            statement.setString(5, group.getIdteacher());
        return 0;
    }
}
