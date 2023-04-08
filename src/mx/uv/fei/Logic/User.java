package mx.uv.fei.Logic;

public class User {
    
    private int id;
    private String firstName;
    private String SecondName;
    private String lastName;
    private String mothersLastName;
    private String institutionalMail;
    private String idTypeUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String SecondName) {
        this.SecondName = SecondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMothersLastName() {
        return mothersLastName;
    }

    public void setMothersLastName(String mothersLastName) {
        this.mothersLastName = mothersLastName;
    }

    public String getInstitutionalMail() {
        return institutionalMail;
    }

    public void setInstitutionalMail(String institutionalMail) {
        this.institutionalMail = institutionalMail;
    }

    public String getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(String idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

}
