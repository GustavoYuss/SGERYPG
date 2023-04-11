/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sgerypg;

import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import mx.uv.fei.DataAccess.AddStudentDAO;
import mx.uv.fei.DataAccess.DataBaseManager;
import mx.uv.fei.Logic.User;


/**
 *
 * @author yusgu
 */
public class SGERYPG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        User datosUsuario = new User();
        AddStudentDAO student = new AddStudentDAO();


        datosUsuario.setFirstName("Roberto");
        datosUsuario.setSecondName("Andres");
        datosUsuario.setLastName("Martinez");
        datosUsuario.setMothersLastName("Andrade");
        datosUsuario.setInstitutionalMail("Robert@gmail.com");
        datosUsuario.setKey("zS2234588854");
    
        
        try {
            int result = 0, result2 = 0;
            result = student.addUser(datosUsuario);
            result2 = student.addStudent(datosUsuario, "1da Inscripcion");
            System.out.println("resultado es: " + result + "\n el id es: " + result2);
        } catch (SQLException exception ) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, 
                        null, exception);
        }
        
    }
}
