package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HospitalMapper {
    List<SidoDTO> findAllSigungu();

    List<SidoDTO> findAllSido();

    List<MainSubjectDTO> findAllMainSubject();

    List<HospitalDTO> findAllHospital();

    List<EupmyeondongDTO> findAllEupmyeondong();

    int hospitalSignup(HospitalDTO newUserInfo);

    UserDTO findByHospitalName(String username);

}
