/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.uv.fei.Logic;

import java.util.regex.Pattern;

/**
 *
 * @author yusgu
 */
public class User {

    private int idUser;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private String eMail;
    private int clave;
    private int idUserType;
    private int idStatus;
    private String status;
    private String Type;

    private final Pattern teacherEmailPattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(uv.mx)");
    private final Pattern studentEmailPattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(estudiantes.uv.mx)");
    
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public void seteMail(String eMail) {
        if(checkEmail(eMail) > 0){
            this.eMail = eMail;
            setIdUserType(checkEmail(this.eMail));
        }
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public void setIdUserType(int idUserType) {
        this.idUserType = idUserType;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public String geteMail() {
        return eMail;
    }

    public int getClave() {
        return clave;
    }

    public int getIdUserType() {
        return idUserType;
        
    }

    public int getIdStatus() {
        return idStatus;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    
    private int checkEmail(String email){
        int result = -1;
        
        if(studentEmailPattern.matcher(email).matches()){
            result = 1;
        }else if(teacherEmailPattern.matcher(email).matches()){
            result = 2;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        return this.clave == ((User) obj).getClave();
    }
    
}
