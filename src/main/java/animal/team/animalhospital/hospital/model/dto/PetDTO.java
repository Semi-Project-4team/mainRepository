package animal.team.animalhospital.hospital.model.dto;

public class PetDTO {

    private int petPersonCode;
    private int personCode;
    private int userCode;
    private String petName;
    private String petType;
    private String petBirth;
    private String petVaccination;
    private String petChip;
    private String petProfile;
    private String petGender;

    public PetDTO() {
    }

    public PetDTO(int petPersonCode, int personCode, int userCode, String petName, String petType, String petBirth, String petVaccination, String petChip, String petProfile, String petGender) {
        this.petPersonCode = petPersonCode;
        this.personCode = personCode;
        this.userCode = userCode;
        this.petName = petName;
        this.petType = petType;
        this.petBirth = petBirth;
        this.petVaccination = petVaccination;
        this.petChip = petChip;
        this.petProfile = petProfile;
        this.petGender = petGender;
    }

    public int getPetPersonCode() {
        return petPersonCode;
    }

    public void setPetPersonCode(int petPersonCode) {
        this.petPersonCode = petPersonCode;
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

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetBirth() {
        return petBirth;
    }

    public void setPetBirth(String petBirth) {
        this.petBirth = petBirth;
    }

    public String getPetVaccination() {
        return petVaccination;
    }

    public void setPetVaccination(String petVaccination) {
        this.petVaccination = petVaccination;
    }

    public String getPetChip() {
        return petChip;
    }

    public void setPetChip(String petChip) {
        this.petChip = petChip;
    }

    public String getPetProfile() {
        return petProfile;
    }

    public void setPetProfile(String petProfile) {
        this.petProfile = petProfile;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    @Override
    public String toString() {
        return "PetDTO{" +
                "petPersonCode=" + petPersonCode +
                ", personCode=" + personCode +
                ", userCode=" + userCode +
                ", petName='" + petName + '\'' +
                ", petType='" + petType + '\'' +
                ", petBirth='" + petBirth + '\'' +
                ", petVaccination='" + petVaccination + '\'' +
                ", petChip='" + petChip + '\'' +
                ", petProfile='" + petProfile + '\'' +
                ", petGender='" + petGender + '\'' +
                '}';
    }
}
