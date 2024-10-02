package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.HospitalMapper;
import animal.team.animalhospital.hospital.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class HospitalService {

    private final HospitalMapper hospitalMapper;

    @Autowired
    public HospitalService(HospitalMapper nameMapper) {
        this.hospitalMapper = nameMapper;
    }

    public List<EupmyeondongDTO> findAllEupmyeondong(int sigunguCode) {
        return hospitalMapper.findAllEupmyeondong(sigunguCode);
    }

    public List<HospitalDTO> findAllHospital() {
        return hospitalMapper.findAllHospital();
    }

    public HospitalDTO findByHospitalCode(int hospitalCode) {
        return hospitalMapper.findByHospitalCode(hospitalCode);
    }

    public List<MainSubjectDTO> findAllMainSubject() {
        return hospitalMapper.findAllMainSubject();
    }

    public List<SidoDTO> findAllSido() {
        return hospitalMapper.findAllSido();
    }

    public List<SigunguDTO> findAllSigungu(int sidoCode) {
        return hospitalMapper.findAllSigungu(sidoCode);
    }

    public void updateHospital(HospitalDTO hospital) {
        hospitalMapper.updateHospital(hospital);
    }


    public List<ReviewDTO> findReviewByCode1(int code) {
        return hospitalMapper.findReviewByCode1(code);

    }

    public List<HospitalDTO> getHospitalsByEupmyeondong(int eupmyeondongCode) {
        return hospitalMapper.getHospitalsByEupmyeondong(eupmyeondongCode);
    }

    @Transactional
    public void deleteHospital(int code) {
        hospitalMapper.deleteHospital(code);
    }

    public void updateHospitalPhoto(Map<String, String> photoPaths) {
//        String photoPathsAsString = photoPaths.toString();
        hospitalMapper.updateHospitalPhoto(photoPaths);
    }

    public String findPhotoByCode(int code) {
        return hospitalMapper.findPhotoByCode(code);
    }

    public List<HospitalDTO> searchHospital(String query) {
        return hospitalMapper.searchHospital(query);

    }

    public List<PersonDTO> searchUser(String query) { return hospitalMapper.searchUser(query);
    }

    public List<HospitalDTO> searchUserHospital(String query) { return hospitalMapper.searchUserHospital(query);
    }
}


