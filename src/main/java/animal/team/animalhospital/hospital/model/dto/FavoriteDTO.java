package animal.team.animalhospital.hospital.model.dto;

public class FavoriteDTO {

    private int personCode;
    private String favoriteName;
    private String favoritePermitNumber;

    public FavoriteDTO() {
    }

    public FavoriteDTO(int personCode, String favoriteName, String favoritePermitNumber) {
        this.personCode = personCode;
        this.favoriteName = favoriteName;
        this.favoritePermitNumber = favoritePermitNumber;
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

    @Override
    public String toString() {
        return "FavoriteDTO{" +
                "personCode=" + personCode +
                ", favoriteName='" + favoriteName + '\'' +
                ", favoritePermitNumber='" + favoritePermitNumber + '\'' +
                '}';
    }
}
