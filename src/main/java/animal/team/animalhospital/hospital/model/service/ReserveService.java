package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.ReserveMapper;
import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
    public int registNewReserve(ReserveDTO newReserve) {

        return   reserveMapper.registNewReserve(newReserve);

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

    public boolean isDuplicateReserve(ReserveDTO newReserve) {
        // 파라미터 맵 생성
        Map<String, Object> params = new HashMap<>();
        params.put("hospitalCode", newReserve.getHospitalCode());
        params.put("personCode", newReserve.getPersonCode());

        // reserveMapper에 파라미터 전달
        int count = reserveMapper.isDuplicateReserve(params); // 캐스팅 제거
        System.out.println("중복 예약 확인: " + count); // 쿼리 결과를 출력
        return count >= 1; // count가 0보다 크면 중복 예약이 있음
    }

}
