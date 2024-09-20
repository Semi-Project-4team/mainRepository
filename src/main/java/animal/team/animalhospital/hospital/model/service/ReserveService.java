package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.ReserveMapper;
import animal.team.animalhospital.hospital.model.dao.ReviewMapper;
import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {

    private final ReserveMapper reserveMapper;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReserveService(ReserveMapper reserveMapper, ReviewMapper reviewMapper) {
        this.reserveMapper = reserveMapper;
        this.reviewMapper = reviewMapper;
    }


    public List<ReserveDTO> findAllReserve() {
        return reserveMapper.findAllReserve();
    }

    public ReserveDTO findReserveByCode(int code) {
        return reserveMapper.findReserveByCode(code);
    }
}
