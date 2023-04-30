
package mx.uv.fei.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mx.uv.fei.controllers.ModelGroupTable;
import mx.uv.fei.Logic.SchoolGroup;
import mx.uv.fei.Logic.User;

public class SchoolGroupDAO implements ISchoolGroup {
    
   public final static int VALID = 0;
   public final static int INVALID = 1;
   public final static int DATA_NOT_FOUND = -1;
    
   @Override
    public int addClass(SchoolGroup group) throws SQLException{
        
        if (group == null) {
            throw new NullPointerException("group no puede ser null");
        }
        
        int checkGroup = checkIfGroupIsAlreadyRegistered(group);
        int rowsAffected  = 0;
        
        if (checkGroup == VALID) {

            String query = "insert into grupo (periodoEscolar, seccion, cupo, idExperienciaEducativa,"
                + "idDocente, NRC, idEstado) values(?,?,?,?,?,?,?)";
            
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, group.getSchoolPeriod());
            statement.setString(2, group.getSection());
            statement.setInt(3, group.getSlots());
            statement.setInt(4, group.getIdEducationalExperience());
            statement.setInt(5, group.getIdTeacher());
            statement.setString(6, group.getNrc());
            statement.setInt(7, 1);

            rowsAffected  = statement.executeUpdate();
            return rowsAffected ;
        } 
            
        return checkGroup;
    }

   @Override
    public int getGroupIdFromDatabase(String nrc) throws SQLException { 
        
        int consultationResult = 0;
        String specificCourseQuery = "SELECT * FROM grupo WHERE nrc = ?;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(specificCourseQuery);
        statement.setString(1, nrc);

        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            if (resultSet.getString("NRC").equals(nrc)){

                consultationResult = resultSet.getInt("idGrupo");

            } else {

                consultationResult = DATA_NOT_FOUND;

            }
        }

        return consultationResult;
    }

   @Override
    public int checkIfGroupIsAlreadyRegistered(SchoolGroup group) throws SQLException {
        
        int consultationResult = 0;
        String specificCourseQuery = "SELECT COUNT(*) as verificar FROM grupo WHERE nrc = ?;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(specificCourseQuery);
        statement.setString(1, group.getNrc());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            if (resultSet.getInt("verificar") == 1){
                consultationResult = INVALID;
            } 
        }

        return consultationResult;
        
    }

   @Override
    public int enrollStudentInGroup(User student, SchoolGroup group) throws SQLException {
        
        int registerEnrollmentResult = 0;
        UserDAO assistant = new UserDAO();
        student.setId(assistant.getIdUser(student.getKey()));

        if ((group.getSlots() > 0) && (checkStudentAvailability(student) == VALID)) {
            
            StudentDAO coneccion = new StudentDAO();
            String query = "INSERT INTO grupoAlumno(idgrupo, idAlumno) values(?,?);";
            DataBaseManager dataBaseManager = new DataBaseManager();
            Connection connection = dataBaseManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, getGroupIdFromDatabase(group.getNrc()));
            statement.setInt(2, coneccion.getIdStudent(student));

            registerEnrollmentResult = statement.executeUpdate();
            connection.close();
            
            group.setSlots(group.getSlots() - 1);
            updateSlotsGroup(group);
            
        }
        
        return registerEnrollmentResult;
        
    }

   @Override
    public void updateSlotsGroup(SchoolGroup group) throws SQLException {
        
        String query = "UPDATE grupo SET cupo = ? WHERE nrc = ? AND idgrupo = ?;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, (group.getSlots() - 1));
        statement.setString(2, group.getNrc());
        statement.setInt(3, getGroupIdFromDatabase(group.getNrc()));
        int consultationResult = statement.executeUpdate();

        connection.close();
        
    }

   @Override
    public int checkStudentAvailability(User student) throws SQLException {
        
        int consultationResult = 0;
        
        String query = "SELECT COUNT(*) as verificar FROM grupoAlumno WHERE idAlumno = ?;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);        
        statement.setInt(1, student.getId());
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()){
            if (resultSet.getInt("verificar") == 1) {
                consultationResult = INVALID;
            }
        }
        
        connection.close();
        
        return consultationResult;
        
    }
    
    
   @Override
    public ArrayList<User> viewListStudents (int idGroup) throws SQLException {
        ArrayList<User> studentsList = new ArrayList<>();
       
        String queryListStudents = "SELECT Usuario.primerNombre, Usuario.ApellidoPaterno, "
            + "Usuario.ApellidoMaterno, Usuario.clave, Usuario.correoInstitucional FROM Usuario INNER JOIN "
            + "Alumno ON Usuario.idUsuario = Alumno.idUsuario INNER JOIN GrupoAlumno ON GrupoAlumno.idAlumno "
            + "= Alumno.idAlumno WHERE GrupoAlumno.idGrupo = ?;";
        
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(queryListStudents);
        statement.setInt(1, idGroup);
        
        ResultSet resultSet = statement.executeQuery();
        
        while (resultSet.next()) {
            User student = new User();
            student.setFirstName(resultSet.getString("primerNombre"));
            student.setLastName(resultSet.getString("ApellidoPaterno"));
            student.setMothersLastName(resultSet.getString("ApellidoMaterno"));
            student.setInstitutionalMail(resultSet.getString("correoInstitucional"));
            student.setKey(resultSet.getString("clave"));
            studentsList.add(student);
        }
        connection.close();
        
        return studentsList;
        
    }
    
   @Override
    public ArrayList<ModelGroupTable> getRegisteredGroupsData() throws SQLException {
    ArrayList<ModelGroupTable> groupsList = new ArrayList<>();

    String query = "SELECT GRUPO.idGRUPO, GRUPO.PERIODOESCOLAR, GRUPO.SECCION, GRUPO.CUPO, "
        + "EXPERIENCIAEDUCATIVA.EXPERIENCIAEDUCATIVA, USUARIO.primernombre, GRUPO.NRC, "
        + "ESTADO.ESTADO FROM GRUPO INNER JOIN EXPERIENCIAEDUCATIVA ON GRUPO.IDEXPERIENCIAEDUCATIVA "
        + "= EXPERIENCIAEDUCATIVA.IDEXPERIENCIAEDUCATIVA INNER JOIN USUARIO ON GRUPO.idDocente = "
        + "USUARIO.IDusuario INNER JOIN ESTADO ON GRUPO.IDESTADO = ESTADO.IDESTADO;";

    try (Connection connection = new DataBaseManager().getConnection();
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {
         
        while (resultSet.next()) {
            ModelGroupTable group = new ModelGroupTable();
            group.setId(resultSet.getInt("GRUPO.idGRUPO"));
            group.setSchoolPeriod(resultSet.getString("GRUPO.PERIODOESCOLAR"));
            group.setNRC(resultSet.getString("GRUPO.NRC"));
            group.setEducationalExperience(resultSet.getString("EXPERIENCIAEDUCATIVA.EXPERIENCIAEDUCATIVA"));
            group.setSection(resultSet.getString("GRUPO.SECCION"));
            group.setSlots(resultSet.getInt("GRUPO.CUPO"));
            group.setTeacher(resultSet.getString("primernombre"));
            group.setStatus(resultSet.getString("ESTADO"));
            groupsList.add(group);
        }
    }

    return groupsList;
}

    
   @Override
    public ArrayList<User> ShowStudentsWithoutCourse() throws SQLException {
        ArrayList<User> studentList = new ArrayList<>();
       
        String query = "SELECT Usuario.primerNombre, Usuario.ApellidoPaterno, Usuario.ApellidoMaterno, "
            + "Usuario.clave, Usuario.correoInstitucional FROM Usuario INNER JOIN Alumno ON Usuario.idUsuario "
            + "= Alumno.idUsuario LEFT JOIN GrupoAlumno ON GrupoAlumno.idAlumno = Alumno.idAlumno "
            + "WHERE GrupoAlumno.idGrupo IS NULL;";
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while (resultSet.next()) {
            User student = new User();
            student.setFirstName(resultSet.getString("primerNombre"));
            student.setLastName(resultSet.getString("ApellidoPaterno"));
            student.setMothersLastName(resultSet.getString("ApellidoMaterno"));
            student.setInstitutionalMail(resultSet.getString("correoInstitucional"));
            student.setKey(resultSet.getString("clave"));
            studentList.add(student);
        }
        
        connection.close();
        
        return studentList;
    }
}
