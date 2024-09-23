package animal.team.animalhospital.hospital.model.dto;

public class PersonDTO {

    private int personCode;
    private String name;
    private String email;
    private String password;
    private String birth;
    private String phoneNumber;
    private String informationCollection;

    // Getter and Setter methods (camelCase)
    public PersonDTO() {
    }

    public PersonDTO(int personCode, String name, String email, String password, String birth, String phoneNumber, String informationCollection) {
        this.personCode = personCode;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        informationCollection = informationCollection;
    }

    public int getPersonCode() {
        return personCode;
    }

    public void setPersonCode(int personCode) {
        this.personCode = personCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInformationCollection() {
        return informationCollection;
    }

    public void setInformationCollection(String informationCollection) {
        informationCollection = informationCollection;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "personCode=" + personCode +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birth='" + birth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", InformationCollection='" + informationCollection + '\'' +
                '}';
    }
}
