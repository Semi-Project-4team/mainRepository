package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.ReserveMapper;
import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ReserveService {

    private final ReserveMapper reserveMapper;

    @Autowired
    public ReserveService(ReserveMapper reserveMapper) {

        this.reserveMapper = reserveMapper;
    }


    public List<ReserveDTO> findAllReserve() {

        return reserveMapper.findAllReserve();
    }

    public ReserveDTO findReserveByCode(int code) {

        return reserveMapper.findReserveByCode(code);
    }

    @Transactional
    public void registNewReserve(ReserveDTO newReserve) {

        reserveMapper.registNewReserve(newReserve);

    }

//    public String resistNewReserve(int code) {
//        return reserveMapper.resistNewReserve(code);
//    }

    @Transactional
    public void deleteReserve(int code) {

        reserveMapper.deleteReserve(code);
    }

//    @Transactional
//    public void updateReserve(ReserveDTO reserve) {
//        reserveMapper.updateReserve(reserve);
//    }

    public ReserveDTO findReserveByCode1(Map<String, Integer> params) {
        return reserveMapper.findReserveByCode1(params);
    }

    public void updateReserve1(ReserveDTO reserve) {
        reserveMapper.updateReserve1(reserve);
    }

    public void deleteReserve1(Map<String, Object> params) {
        reserveMapper.deleteReserve1(params);
    }
}
