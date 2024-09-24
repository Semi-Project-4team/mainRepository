package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HospitalMapper {
    List<SigunguDTO> findAllSigungu(int sidoCode);

    List<SidoDTO> findAllSido();

    List<MainSubjectDTO> findAllMainSubject();

    List<HospitalDTO> findAllHospital();

    HospitalDTO findByHospitalCode(int code);

    List<EupmyeondongDTO> findAllEupmyeondong(int sigunguCode);

    int hospitalSignup(HospitalDTO newUserInfo);

    HospitalDTO findByHospitalName(String hospitalEmail);

}
