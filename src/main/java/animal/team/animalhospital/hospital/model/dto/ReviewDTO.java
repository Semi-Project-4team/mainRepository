package animal.team.animalhospital.hospital.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ReviewDTO {

    private int personCode;
    private int hospitalCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reviewWriteDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reviewModifyDate;
    private int reviewScore;
    private String reviewPhoto;
    private String reviewExplanation;

    public ReviewDTO() {
    }

    public ReviewDTO( int personCode, int hospitalCode, Date reviewWriteDate, Date reviewModifyDate, int reviewScore, String reviewPhoto, String reviewExplanation) {
        this.personCode = personCode;
        this.hospitalCode = hospitalCode;
        this.reviewWriteDate = reviewWriteDate;
        this.reviewModifyDate = reviewModifyDate;
        this.reviewScore = reviewScore;
        this.reviewPhoto = reviewPhoto;
        this.reviewExplanation = reviewExplanation;
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

    public Date getReviewWriteDate() {
        return reviewWriteDate;
    }

    public void setReviewWriteDate(Date reviewWriteDate) {
        this.reviewWriteDate = reviewWriteDate;
    }

    public Date getReviewModifyDate() {
        return reviewModifyDate;
    }

    public void setReviewModifyDate(Date reviewModifyDate) {
        this.reviewModifyDate = reviewModifyDate;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewPhoto() {
        return reviewPhoto;
    }

    public void setReviewPhoto(String reviewPhoto) {
        this.reviewPhoto = reviewPhoto;
    }

    public String getReviewExplanation() {
        return reviewExplanation;
    }

    public void setReviewExplanation(String reviewExplanation) {
        this.reviewExplanation = reviewExplanation;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                ", personCode=" + personCode +
                ", hospitalCode=" + hospitalCode +
                ", reviewWriteDate=" + reviewWriteDate +
                ", reviewModifyDate=" + reviewModifyDate +
                ", reviewScore=" + reviewScore +
                ", reviewPhoto='" + reviewPhoto + '\'' +
                ", reviewExplanation='" + reviewExplanation + '\'' +
                '}';
    }
}
