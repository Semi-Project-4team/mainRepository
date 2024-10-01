package animal.team.animalhospital.hospital.model.dto;

import animal.team.animalhospital.auth.model.UserRole;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalTime;
import java.util.*;

public class HospitalDTO implements UserDetails {

    private int hospitalCode;
    private String subjectCode;
//    private int subjectCode;
    private String eupmyeondongCode;
    private int userCode;
    private String name;
//    private String username;
    private String permitNumber;
    private String email;
    private String password;
    private String informationCollection;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime lunchStartTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime lunchEndTime;

    private String detailAddress;
    private String introText;
    private String phoneNumber;
    private String photo;
    private String status;
    private String isAccountDrawal;

    public HospitalDTO() {
    }

    public HospitalDTO(int hospitalCode, String subjectCode, String eupmyeondongCode, int userCode, String name, String permitNumber, String email, String password, String informationCollection, LocalTime startTime, LocalTime endTime, LocalTime lunchStartTime, LocalTime lunchEndTime, String detailAddress, String introText, String phoneNumber, String photo, String status, String isAccountDrawal) {
        this.hospitalCode = hospitalCode;
        this.subjectCode = subjectCode;
        this.eupmyeondongCode = eupmyeondongCode;
        this.userCode = userCode;
        this.name = name;
        this.permitNumber = permitNumber;
        this.email = email;
        this.password = password;
        this.informationCollection = informationCollection;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lunchStartTime = lunchStartTime;
        this.lunchEndTime = lunchEndTime;
        this.detailAddress = detailAddress;
        this.introText = introText;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.status = status;
        this.isAccountDrawal = isAccountDrawal;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getLunchStartTime() {
        return lunchStartTime;
    }

    public void setLunchStartTime(LocalTime lunchStartTime) {
        this.lunchStartTime = lunchStartTime;
    }

    public LocalTime getLunchEndTime() {
        return lunchEndTime;
    }

    public void setLunchEndTime(LocalTime lunchEndTime) {
        this.lunchEndTime = lunchEndTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(int hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getEupmyeondongCode() {
        return eupmyeondongCode;
    }

    public void setEupmyeondongCode(String eupmyeondongCode) {
        this.eupmyeondongCode = eupmyeondongCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(UserRole.ADMIN_HOSPITAL::getRole);
        System.out.println("병원 권한을 부여함 : " + authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println("병원 비밀번호 요구함 : " + this.password);
        return this.password;
    }

    @Override
    public String getUsername() {
        System.out.println("병원 이메일 요구함 : " + this.email);
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInformationCollection() {
        if(Objects.equals(informationCollection, "on"))
            return "Y";
        return "N";
    }

    public void setInformationCollection(String informationCollection) {
        this.informationCollection = informationCollection;
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

    public String getIsAccountDrawal() {
        return isAccountDrawal;
    }

    public void setIsAccountDrawal(String isAccountDrawal) {
        this.isAccountDrawal = isAccountDrawal;
    }

    @Override
    public String toString() {
        return "HospitalDTO{" +
                "hospitalCode=" + hospitalCode +
                ", subjectCode='" + subjectCode + '\'' +
                ", eupmyeondongCode='" + eupmyeondongCode + '\'' +
                ", userCode=" + userCode +
                ", name='" + name + '\'' +
                ", permitNumber='" + permitNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", informationCollection='" + informationCollection + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", lunchStartTime=" + lunchStartTime +
                ", lunchEndTime=" + lunchEndTime +
                ", detailAddress='" + detailAddress + '\'' +
                ", introText='" + introText + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", photo='" + photo + '\'' +
                ", status='" + status + '\'' +
                ", isAccountDrawal='" + isAccountDrawal + '\'' +
                '}';
    }
}
