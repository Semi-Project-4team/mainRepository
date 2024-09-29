package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    List<PersonDTO> findMyInfo(String userEmail);

    List<FavoriteDTO> findMyFavorite(String userEmail);

    List<PetDTO> findMyPet(String userEmail);

    void deleteFavorite(String name);

    List<ReserveDTO> findMyReserve(String userEmail);

    List<HospitalDTO> findMyHospital(String userEmail);

    PersonDTO selectMyInfoByEmail(String email);

    void updateMyInfo(PersonDTO person);
}
