package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.HospitalMapper;
import animal.team.animalhospital.hospital.model.dto.EupmyeondongDTO;
import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
import animal.team.animalhospital.hospital.model.dto.MainSubjectDTO;
import animal.team.animalhospital.hospital.model.dto.SidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalMapper hospitalMapper;

    @Autowired
    public HospitalService(HospitalMapper nameMapper) {
        this.hospitalMapper = nameMapper;
    }

    public List<EupmyeondongDTO> findAllEupmyeondong() {
        return hospitalMapper.findAllEupmyeondong();
    }

    public List<HospitalDTO> findAllHospital() {
        return hospitalMapper.findAllHospital();
    }

    public List<MainSubjectDTO> findAllMainSubject() {
        return hospitalMapper.findAllMainSubject();
    }

    public List<SidoDTO> findAllSido() {
        return hospitalMapper.findAllSido();
    }

    public List<SidoDTO> findAllSigungu() {
        return hospitalMapper.findAllSigungu();
    }
}
