package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyPageMapper {

    List<PersonDTO> findMyInfo(String userEmail);

    List<FavoriteDTO> findMyFavorite(String userEmail);

    List<PetDTO> findMyPet(String userEmail);

    void deleteFavorite(Map<String, String> name);

    List<ReserveDTO> findMyReserve(String userEmail);

    List<HospitalDTO> findMyHospital(String userEmail);

    PersonDTO selectMyInfoByEmail(String email);

    void updateMyInfo(PersonDTO person);

    PetDTO findMyPetAlone(String userEmail);

    void updateMyPet(PetDTO petDTO);

    void insertMyPet(PetDTO newPet);

    void insertMyPetAlone(String userEmail);

    List<HospitalDTO> findHospitalInfo(String userEmail);

    List<ReserveDTO> findHospitalReserve(String userEmail);

    List<PersonDTO> findProtector(String userEmail);

    void userWithDrawal(String userEmail);

    void hospitalWithDrawal(String userEmail);
}
