package animal.team.animalhospital.hospital.model.dto;

import java.util.Date;

public class HospitalDTO {

    private int hospitalCode;
    private int subjectCode;
    private int eupmyeondongCode;
    private int userCode;
    private String name;
    private String permitNumber;
    private String email;
    private String password;
    private String informationCollection;
    private Date time;
    private String detailAddress;
    private String introText;
    private String photo;

    public HospitalDTO() {
    }

    public HospitalDTO(int hospitalCode, int subjectCode, int eupmyeondongCode, int userCode, String name, String permitNumber, String email, String password, String informationCollection, Date time, String detailAddress, String introText, String photo) {
        this.hospitalCode = hospitalCode;
        this.subjectCode = subjectCode;
        this.eupmyeondongCode = eupmyeondongCode;
        this.userCode = userCode;
        this.name = name;
        this.permitNumber = permitNumber;
        this.email = email;
        this.password = password;
        this.informationCollection = informationCollection;
        this.time = time;
        this.detailAddress = detailAddress;
        this.introText = introText;
        this.photo = photo;
    }

    public int getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(int hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public int getEupmyeondongCode() {
        return eupmyeondongCode;
    }

    public void setEupmyeondongCode(int eupmyeondongCode) {
        this.eupmyeondongCode = eupmyeondongCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInformationCollection() {
        return informationCollection;
    }

    public void setInformationCollection(String informationCollection) {
        this.informationCollection = informationCollection;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "hospitalDTO{" +
                "hospitalCode=" + hospitalCode +
                ", subjectCode=" + subjectCode +
                ", eupmyeondongCode=" + eupmyeondongCode +
                ", userCode=" + userCode +
                ", name='" + name + '\'' +
                ", permitNumber='" + permitNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", informationCollection='" + informationCollection + '\'' +
                ", time=" + time +
                ", detailAddress='" + detailAddress + '\'' +
                ", introText='" + introText + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
