package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.EupmyeondongDTO;
import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
import animal.team.animalhospital.hospital.model.dto.MainSubjectDTO;
import animal.team.animalhospital.hospital.model.dto.SidoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HospitalMapper {
    List<SidoDTO> findAllSigungu();

    List<SidoDTO> findAllSido();

    List<MainSubjectDTO> findAllMainSubject();

    List<HospitalDTO> findAllHospital();

    List<EupmyeondongDTO> findAllEupmyeondong();
}
