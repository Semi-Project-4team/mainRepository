package animal.team.animalhospital.hospital.model.dto;

public class FavoriteDTO {

    private int personCode;
    private int hospitalCode;
    private String favoriteName;
    private String favoritePermitNumber;
    private String favoriteHospitalCode;
    private String photo;

    public FavoriteDTO() {
    }

    public FavoriteDTO(int personCode, int hospitalCode, String favoriteName, String favoritePermitNumber, String favoriteHospitalCode, String photo) {
        this.personCode = personCode;
        this.hospitalCode = hospitalCode;
        this.favoriteName = favoriteName;
        this.favoritePermitNumber = favoritePermitNumber;
        this.favoriteHospitalCode = favoriteHospitalCode;
        this.photo = photo;
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

    public String getFavoriteHospitalCode() {
        return favoriteHospitalCode;
    }

    public void setFavoriteHospitalCode(String favoriteHospitalCode) {
        this.favoriteHospitalCode = favoriteHospitalCode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "FavoriteDTO{" +
                "personCode=" + personCode +
                ", hospitalCode=" + hospitalCode +
                ", favoriteName='" + favoriteName + '\'' +
                ", favoritePermitNumber='" + favoritePermitNumber + '\'' +
                ", favoriteHospitalCode='" + favoriteHospitalCode + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
