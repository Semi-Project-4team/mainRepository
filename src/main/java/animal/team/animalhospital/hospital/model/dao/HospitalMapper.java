package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HospitalMapper {
    List<SigunguDTO> findAllSigungu(int sidoCode);

    List<SidoDTO> findAllSido();

    List<MainSubjectDTO> findAllMainSubject();

    List<HospitalDTO> findAllHospital();

    HospitalDTO findByHospitalCode(int code);

    List<EupmyeondongDTO> findAllEupmyeondong(int sigunguCode);

    int hospitalSignup(HospitalDTO newUserInfo);

    HospitalDTO hospitalFindEmail(HospitalDTO hospitalEmail);

    HospitalDTO findByHospitalName(String hospitalEmail);

    void updateHospital(HospitalDTO hospital);

    String findByHospitalEmail(String email);

    int updatePasswordHospital(HospitalDTO newUserInfo);

    List<ReviewDTO> findReviewByCode(int code);

    List<ReviewDTO> findReviewByCode1(int code);
}
