package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.HospitalMapper;
import animal.team.animalhospital.hospital.model.dao.PersonMapper;
import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

//    private final UserController userController;
    private PasswordEncoder encoder;
    private HospitalMapper hospitalMapper;
    private PersonMapper personMapper;

    @Autowired
    public UserService(PasswordEncoder encoder, HospitalMapper userMapper, PersonMapper personMapper) {
        this.encoder = encoder;
        this.hospitalMapper = userMapper;
        this.personMapper = personMapper;
//        this.userController = userController;
    }


    @Transactional
    public Integer hospitalSignup(HospitalDTO newUserInfo) {

//        System.out.println(newUserInfo.toString());
        System.out.println("평문 : " + newUserInfo.getPassword());
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        System.out.println("암호문 : " + newUserInfo.getPassword());

        Integer result = null;

        try {
            result = hospitalMapper.hospitalSignup(newUserInfo);
        } catch (DuplicateKeyException e) {     // 데이터 무결성 위반(중복 키) 발생 시 처리
            result = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }
        System.out.println("(기업) 회원가입 처리 결과 => " + result);

        return result;
    }

    @Transactional
    public Integer personSignup(PersonDTO newUserInfo) {

        System.out.println("평문 : " + newUserInfo.getPassword());
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        System.out.println("암호문 : " + newUserInfo.getPassword());

        Integer result = null;

        try {
            result = personMapper.personSignup(newUserInfo);
        } catch (DuplicateKeyException e) {     // 데이터 무결성 위반(중복 키) 발생 시 처리
            result = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }
        System.out.println("(개인) 회원가입 처리 결과 => " + result);

        return result;
    }

    public HospitalDTO findByHospitalName(String hospitalEmail) {

        System.out.println("findByHospitalName : " + hospitalEmail);

        HospitalDTO foundHospitalName = hospitalMapper.findByHospitalName(hospitalEmail);

        System.out.println("foundHospitalName = " + foundHospitalName);

        if (!Objects.isNull(foundHospitalName)) {
            return foundHospitalName;
        } else {
            return null;
        }
    }

    public PersonDTO findByPersonName(String personEmail) {

        System.out.println("findByPersonName1 : " + personEmail);

        PersonDTO foundPersonName = personMapper.findByPersonName(personEmail);

        System.out.println("findByPersonName2 = " + personEmail);

        if (!Objects.isNull(foundPersonName)) {
            return foundPersonName;
        } else {
            return null;
        }
    }

    public HospitalDTO hospitalFindEmail(HospitalDTO newUserInfo) {

        System.out.println("hospitalFindEmail : " + newUserInfo);

        HospitalDTO foundHospitalInfo = hospitalMapper.hospitalFindEmail(newUserInfo);

        System.out.println("hospitalFindEmail = " + foundHospitalInfo);

        if (!Objects.isNull(foundHospitalInfo)) {
            return foundHospitalInfo;
          
        } else {
            return null;
        }
    }

    public PersonDTO personFindEmail(PersonDTO newUserInfo) {
        System.out.println("personFindEmail : " + newUserInfo);

        PersonDTO foundPersonInfo = personMapper.personFindEmail(newUserInfo);

        System.out.println("personFindEmail = " + foundPersonInfo);

        if (!Objects.isNull(foundPersonInfo)) {
            return foundPersonInfo;
        } else {
            return null;
        }
    }


    public boolean findByHospitalEmail(String email) {
        System.out.println("findByHospitalEmail : " + email);

        String hospitalEmail = hospitalMapper.findByHospitalEmail(email);

        System.out.println("findByHospitalEmail = " + hospitalEmail);

        if (!Objects.isNull(hospitalEmail)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findByPersonEmail(String email) {
        System.out.println("findByPersonEmail : " + email);

        String personEmail = personMapper.findByPersonEmail(email);

        System.out.println("findByPersonEmail = " + personEmail);

        if (!Objects.isNull(personEmail)) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public Integer updatePasswordHospital(HospitalDTO newUserInfo) {

        System.out.println("비밀번호 변경 평문 : " + newUserInfo.getPassword());
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        System.out.println("비밀번호 변경 암호문 : " + newUserInfo.getPassword());

        Integer result = null;

        try {
            result = hospitalMapper.updatePasswordHospital(newUserInfo);
        } catch (DuplicateKeyException e) {     // 데이터 무결성 위반(중복 키) 발생 시 처리
            result = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }
        System.out.println("(기업) 비밀번호 처리 결과 => " + result);

        return result;

    }

    @Transactional
    public Integer updatePasswordPerson(PersonDTO newUserInfo) {

        System.out.println("비밀번호 변경 평문 : " + newUserInfo.getPassword());
        newUserInfo.setPassword(encoder.encode(newUserInfo.getPassword()));
        System.out.println("비밀번호 변경 암호문 : " + newUserInfo.getPassword());

        Integer result = null;

        try {
            result = personMapper.updatePasswordPerson(newUserInfo);
        } catch (DuplicateKeyException e) {     // 데이터 무결성 위반(중복 키) 발생 시 처리
            result = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            result = 0;
            e.printStackTrace();
        }
        System.out.println("(개인) 비밀번호 처리 결과 => " + result);

        return result;
    }


    public boolean isPersonSignCheck(String email) {
        System.out.println("isPersonSignCheck email = " + email);

        Integer result = null;

        result = personMapper.isPersonSignCheck(email);

        System.out.println("isPersonSignCheck result = " + result);

        return result >= 1;
    }

    public boolean isHospitalSignCheck(String email) {

        System.out.println("isHospitalSignCheck email = " + email);

        Integer result = null;

        result = hospitalMapper.isHospitalSignCheck(email);

        System.out.println("isHospitalSignCheck result = " + result);

        return result >= 1;
    }

    public void updateHospitalStatus(Map<String, String> request) {
//        // 유저 정보를 DB에서 가져와 상태를 변경
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
//        user.setActive(isActive);
//        userRepository.save(user); // 상태 저장
//        System.out.println("userId = " + userId);
//        System.out.println("isActive = " + isActive);

        System.out.println("request = " + request);

        hospitalMapper.updateHospitalStatus(request);


    }

    public void updateHospitalDrawal(Map<String, String> hospitalStatus) {
        System.out.println("hospitalStatus = " + hospitalStatus);

        hospitalMapper.updateHospitalDrawal(hospitalStatus);

    }
}
