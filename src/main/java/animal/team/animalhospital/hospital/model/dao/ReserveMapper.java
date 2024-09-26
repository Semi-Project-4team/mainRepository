package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReserveMapper {

    List<ReserveDTO> findAllReserve();

    ReserveDTO findReserveByCode(int code);

    void registNewReserve(ReserveDTO newReserve);

    void deleteReserve(int code);

    void updateReserve(ReserveDTO reserve);

    void updateReserve1(ReserveDTO reserve);

    void resistNewReserve(int code);

    ReserveDTO findReserveByCode1(Map<String, Object> params);
}
