package animal.team.animalhospital.hospital.model.dto;

public class FavoriteDTO {

    private int personCode;
    private String favoriteName;

    public FavoriteDTO() {
    }

    public FavoriteDTO(int personCode, String favoriteName) {
        this.personCode = personCode;
        this.favoriteName = favoriteName;
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
                '}';
    }
}
