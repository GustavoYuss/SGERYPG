package mx.uv.fei.Logic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPreProject {

    int addPreProject (PreProject preProject) throws SQLException;
    ArrayList<PreProject> consultPreProjects( ) throws SQLException;

}
