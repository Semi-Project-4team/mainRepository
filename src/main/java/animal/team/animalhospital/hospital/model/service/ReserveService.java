package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.ReserveMapper;
import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public void deleteReserve(int code) {

        reserveMapper.deleteReserve(code);
    }

    @Transactional
    public void updateReserve(ReserveDTO reserve) {
        reserveMapper.updateReserve(reserve);
    }
}
