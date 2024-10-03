package animal.team.animalhospital.hospital.model.dto;

public class FavoriteDTO {

    private int personCode;
    private String favoriteName;
    private String favoritePermitNumber;
    private String favoriteHospitalCode;

    public FavoriteDTO() {
    }

    public FavoriteDTO(int personCode, String favoriteName, String favoritePermitNumber, String favoriteHospitalCode) {
        this.personCode = personCode;
        this.favoriteName = favoriteName;
        this.favoritePermitNumber = favoritePermitNumber;
        this.favoriteHospitalCode = favoriteHospitalCode;
    }

    public String getFavoritePermitNumber() {
        return favoritePermitNumber;
    }

    public void setFavoritePermitNumber(String favoritePermitNumber) {
        this.favoritePermitNumber = favoritePermitNumber;
    }

    public int getPersonCode() {
        return personCode;
    }

    public void setPersonCode(int personCode) {
        this.personCode = personCode;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public String getFavoriteHospitalCode() {
        return favoriteHospitalCode;
    }

    public void setFavoriteHospitalCode(String favoriteHospitalCode) {
        this.favoriteHospitalCode = favoriteHospitalCode;
    }

    @Override
    public String toString() {
        return "FavoriteDTO{" +
                "personCode=" + personCode +
                ", favoriteName='" + favoriteName + '\'' +
                ", favoritePermitNumber='" + favoritePermitNumber + '\'' +
                ", favoriteHospitalCode='" + favoriteHospitalCode + '\'' +
                '}';
    }
}
