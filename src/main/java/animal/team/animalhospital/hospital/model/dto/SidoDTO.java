package animal.team.animalhospital.hospital.model.dto;

public class SidoDTO {
    private int sidoCode;
    private String name;

    public SidoDTO() {
    }

    public SidoDTO(int sidoCode, String name) {
        this.sidoCode = sidoCode;
        this.name = name;
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
        return "sidoDTO{" +
                "sidoCode=" + sidoCode +
                ", name='" + name + '\'' +
                '}';
    }
}
