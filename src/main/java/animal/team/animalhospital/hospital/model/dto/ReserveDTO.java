package animal.team.animalhospital.hospital.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;
public class ReserveDTO {



    private int personCode;
    private int hospitalCode;
    private int petPersonCode;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime reserveTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reserveDate;
    private String reserveText;
    private String reserveInformationCollection;

    public ReserveDTO() {
    }


    public ReserveDTO(int personCode, int hospitalCode, int petPersonCode, LocalTime reserveTime, Date reserveDate, String reserveText, String reserveInformationCollection) {
        this.personCode = personCode;
        this.hospitalCode = hospitalCode;
        this.petPersonCode = petPersonCode;
        this.reserveTime = reserveTime;
        this.reserveDate = reserveDate;
        this.reserveText = reserveText;
        this.reserveInformationCollection = reserveInformationCollection;
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

    public int getPetPersonCode() {
        return petPersonCode;
    }

    public void setPetPersonCode(int petPersonCode) {
        this.petPersonCode = petPersonCode;
    }

    public LocalTime getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(LocalTime reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public String getReserveText() {
        return reserveText;
    }

    public void setReserveText(String reserveText) {
        this.reserveText = reserveText;
    }

    public String getReserveInformationCollection() {
        return reserveInformationCollection;
    }

    public void setReserveInformationCollection(String reserveInformationCollection) {
        this.reserveInformationCollection = reserveInformationCollection;
    }

    @Override
    public String toString() {
        return "ReserveDTO{" +
                "personCode=" + personCode +
                ", hospitalCode=" + hospitalCode +
                ", petPersonCode=" + petPersonCode +
                ", reserveTime=" + reserveTime +
                ", reserveDate=" + reserveDate +
                ", reserveText='" + reserveText + '\'' +
                ", reserveInformationCollection='" + reserveInformationCollection + '\'' +
                '}';
    }

}
