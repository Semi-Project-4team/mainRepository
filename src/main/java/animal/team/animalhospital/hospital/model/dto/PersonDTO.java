package animal.team.animalhospital.hospital.model.dto;

public class PersonDTO {

    private int personCode;
    private int userCode;
    private String personName;
    private String personEmail;
    private String personPassword;
    private String personBirth;
    private String personPhoneNumber;
    private String personInformationCollection;

    // Getter and Setter methods (camelCase)
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPassword() {
        return personPassword;
    }

    public void setPersonPassword(String personPassword) {
        this.personPassword = personPassword;
    }

    public String getPersonBirth() {
        return personBirth;
    }

    public void setPersonBirth(String personBirth) {
        this.personBirth = personBirth;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public void setPersonPhoneNumber(String personPhoneNumber) {
        this.personPhoneNumber = personPhoneNumber;
    }

    public String getPersonInformationCollection() {
        return personInformationCollection;
    }

    public void setPersonInformationCollection(String personInformationCollection) {
        this.personInformationCollection = personInformationCollection;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "personCode=" + personCode +
                ", userCode=" + userCode +
                ", personName='" + personName + '\'' +
                ", personEmail='" + personEmail + '\'' +
                ", personPassword='" + personPassword + '\'' +
                ", personBirth='" + personBirth + '\'' +
                ", personPhoneNumber='" + personPhoneNumber + '\'' +
                ", personInformationCollection='" + personInformationCollection + '\'' +
                '}';
    }
}
