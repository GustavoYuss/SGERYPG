package mx.uv.fei.Logic;

public class SchoolGroup {

    private int idGroup;
    private String schoolPeriod;
    private String section;
    private int slots;
    private int idEducationalExperience;
    private int idTeacher;
    private String nrc;
    private int estatus;

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getSchoolPeriod() {
        return schoolPeriod;
    }

    public void setSchoolPeriod(String schoolPeriod) {
        this.schoolPeriod = schoolPeriod;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public int getIdEducationalExperience() {
        return idEducationalExperience;
    }

    public void setIdEducationalExperience(int idEducationalExperience) {
        this.idEducationalExperience = idEducationalExperience;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }
    
    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

}
