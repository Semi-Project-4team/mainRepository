package animal.team.animalhospital.hospital.model.dto;

public class FavoriteDTO {

    private int personCode;
    private int hospitalCode;
    private String favoriteName;

    public FavoriteDTO() {
    }

    public FavoriteDTO(int personCode, int hospitalCode, String favoriteName) {
        this.personCode = personCode;
        this.hospitalCode = hospitalCode;
        this.favoriteName = favoriteName;
    }

    public int getPersonCode() {
        return personCode;
    }

    public void setPersonCode(int personCode) {
        this.personCode = personCode;
    }

    public int getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(int hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    @Override
    public String toString() {
        return "FavoriteDTO{" +
                "personCode=" + personCode +
                ", hospitalCode=" + hospitalCode +
                ", favoriteName='" + favoriteName + '\'' +
                '}';
    }
}
