package animal.team.animalhospital.hospital.model.dto;

import animal.team.animalhospital.auth.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class PersonDTO implements UserDetails {

    private int personCode;
    private String name;
    private String email;
    private String password;
    private String birth;
    private String phoneNumber;
    private String informationCollection;

    // Getter and Setter methods (camelCase)
    public PersonDTO() {
    }

    public PersonDTO(int personCode, String name, String email, String password, String birth, String phoneNumber, String informationCollection) {
        this.personCode = personCode;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.informationCollection = informationCollection;
    }

    public int getPersonCode() {
        return personCode;
    }

    public void setPersonCode(int personCode) {
        this.personCode = personCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if(!getUsername().equals("admin")) {
            // 개인회원 권한 부여
            authorities.add(UserRole.USER_PERSON::getRole);
        } else {
            // MASTER 권한 부여
            authorities.add(UserRole.MASTER::getRole);
        }
        System.out.println("개인 권한을 부여함 : " + authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        System.out.println("개인 비밀번호 요구함 : " + this.password);
        return this.password;
    }

    @Override
    public String getUsername() {
        System.out.println("개인 이메일 요구함 : " + this.email);
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInformationCollection() {
        if(Objects.equals(informationCollection, "on"))
            return "Y";
        return "N";
    }

    public void setInformationCollection(String informationCollection) {
        this.informationCollection = informationCollection;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "personCode=" + personCode +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birth='" + birth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", InformationCollection='" + informationCollection + '\'' +
                '}';
    }
}
