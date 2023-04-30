package mx.uv.fei.Logic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPreliminaryProject {

    int addPreliminaryProject (PreliminaryProject preProject) throws SQLException;
    ArrayList<PreliminaryProject> consultAllPreliminaryProjects( ) throws SQLException;

}
