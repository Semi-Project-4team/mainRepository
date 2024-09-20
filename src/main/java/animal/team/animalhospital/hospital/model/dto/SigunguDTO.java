package animal.team.animalhospital.hospital.model.dto;

public class SigunguDTO {
    private int sigunguCode;
    private int sidoCode;
    private String name;

    public SigunguDTO() {
    }

    public SigunguDTO(int sigunguCode, int sidoCode, String name) {
        this.sigunguCode = sigunguCode;
        this.sidoCode = sidoCode;
        this.name = name;
    }

    public int getSigunguCode() {
        return sigunguCode;
    }

    public void setSigunguCode(int sigunguCode) {
        this.sigunguCode = sigunguCode;
    }

    public int getSidoCode() {
        return sidoCode;
    }

    public void setSidoCode(int sidoCode) {
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
