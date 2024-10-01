package animal.team.animalhospital.hospital.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
public class ReserveDTO {



    private int personCode;
    private int hospitalCode;
    private int petPersonCode;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime reserveTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDate;
    private String reserveText;
    private String reserveInformationCollection;
    private String reserveStatus;

    public ReserveDTO() {
    }


    public ReserveDTO(int personCode, int hospitalCode, int petPersonCode, LocalTime reserveTime, LocalDate reserveDate, String reserveText, String reserveInformationCollection, String reserveStatus) {
        this.personCode = personCode;
        this.hospitalCode = hospitalCode;
        this.petPersonCode = petPersonCode;
        this.reserveTime = reserveTime;
        this.reserveDate = reserveDate;
        this.reserveText = reserveText;
        this.reserveInformationCollection = reserveInformationCollection;
        this.reserveStatus = reserveStatus;

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

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(LocalDate reserveDate) {
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

    public String getReserveStatus() {
        return reserveStatus;
    }

    public void setReserveStatus(String reserveStatus) {
        this.reserveStatus = reserveStatus;
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
                ", reserveStatus='" + reserveStatus + '\'' +
                '}';
    }
}
