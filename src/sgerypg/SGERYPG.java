/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sgerypg;

import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import mx.uv.fei.DataAccess.StudentDAO;
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
        StudentDAO student = new StudentDAO();


        datosUsuario.setFirstName("Andrea");
        datosUsuario.setSecondName("laura");
        datosUsuario.setLastName("Rincon");
        datosUsuario.setMothersLastName("Ayala");
        datosUsuario.setInstitutionalMail("AndresAya@gmail.com");
        datosUsuario.setKey("zS57693200");
    
        
        try {
            int result = 0, result2 = 0;
            result = student.addUser(datosUsuario);
            result2 = student.addStudent(datosUsuario, "2da Inscripcion");
            System.out.println("resultado es: " + result + " y " + result2);
        } catch (SQLException exception ) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, 
                        null, exception);
        }
        
    }
}
