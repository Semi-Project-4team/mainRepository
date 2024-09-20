package animal.team.animalhospital.hospital.model.dto;

public class EupmyeondongDTO {

    private int eupmyeondongCode;
    private String eupmyeondongName;
    private int sigunguCode;

    public EupmyeondongDTO() {
    }

    public EupmyeondongDTO(int eupmyeondongCode, String eupmyeondongName, int sigunguCode) {
        this.eupmyeondongCode = eupmyeondongCode;
        this.eupmyeondongName = eupmyeondongName;
        this.sigunguCode = sigunguCode;
    }

    public int getEupmyeondongCode() {
        return eupmyeondongCode;
    }

    public void setEupmyeondongCode(int eupmyeondongCode) {
        this.eupmyeondongCode = eupmyeondongCode;
    }

    public String getEupmyeondongName() {
        return eupmyeondongName;
    }

    public void setEupmyeondongName(String eupmyeondongName) {
        this.eupmyeondongName = eupmyeondongName;
    }

    public int getSigunguCode() {
        return sigunguCode;
    }

    public void setSigunguCode(int sigunguCode) {
        this.sigunguCode = sigunguCode;
    }

    @Override
    public String toString() {
        return "eupmyeondongDTO{" +
                "eupmyeondongCode=" + eupmyeondongCode +
                ", eupmyeondongName='" + eupmyeondongName + '\'' +
                ", sigunguCode=" + sigunguCode +
                '}';
    }
}
