package animal.team.animalhospital.hospital.model.dto;

public class SigunguDTO {
    private String sigunguCode;
    private String sidoCode;
    private String name;

    public SigunguDTO() {
    }

    public SigunguDTO(String sigunguCode, String sidoCode, String name) {
        this.sigunguCode = sigunguCode;
        this.sidoCode = sidoCode;
        this.name = name;
    }

    public String getSigunguCode() {
        return sigunguCode;
    }

    public void setSigunguCode(String sigunguCode) {
        this.sigunguCode = sigunguCode;
    }

    public String getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(String sidoCode) {
        this.sidoCode = sidoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "sigunguDTO{" +
                "sigunguCode=" + sigunguCode +
                ", sidoCode=" + sidoCode +
                ", name='" + name + '\'' +
                '}';
    }
}
