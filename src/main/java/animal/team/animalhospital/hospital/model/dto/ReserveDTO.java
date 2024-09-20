package animal.team.animalhospital.hospital.model.dto;

import java.sql.Time;
import java.util.Date;

public class ReserveDTO {

    private int personCode;
    private Time reserveTime;
    private Date reserveDate;
    private String reserveText;
    private String reserveInformationCollection;

    public ReserveDTO() {
    }

    public ReserveDTO( int personCode, Time reserveTime, Date reserveDate, String reserveText, String reserveInformationCollection) {
        this.personCode = personCode;
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

    public Time getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Time reserveTime) {
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
                ", personCode=" + personCode +
                ", reserveTime=" + reserveTime +
                ", reserveDate=" + reserveDate +
                ", reserveText='" + reserveText + '\'' +
                ", reserveInformationCollection='" + reserveInformationCollection + '\'' +
                '}';
    }
}
