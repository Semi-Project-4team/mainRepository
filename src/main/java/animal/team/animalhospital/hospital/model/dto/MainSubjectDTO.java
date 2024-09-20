package animal.team.animalhospital.hospital.model.dto;

public class MainSubjectDTO {
    private int subjectCode;
    private int reserveCode;
    private int personCode;
    private int userCode;
    private String name;

    public MainSubjectDTO() {
    }


    public MainSubjectDTO(int subjectCode, int reserveCode, int personCode, int userCode, String name) {
        this.subjectCode = subjectCode;
        this.reserveCode = reserveCode;
        this.personCode = personCode;
        this.userCode = userCode;
        this.name = name;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getReserveCode() {
        return reserveCode;
    }

    public void setReserveCode(int reserveCode) {
        this.reserveCode = reserveCode;
    }

    public int getPersonCode() {
        return personCode;
    }

    public void setPersonCode(int personCode) {
        this.personCode = personCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "mainSubjectDTO{" +
                "subjectCode=" + subjectCode +
                ", reserveCode=" + reserveCode +
                ", personCode=" + personCode +
                ", userCode=" + userCode +
                ", name='" + name + '\'' +
                '}';
    }
}
