package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.PetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    List<PersonDTO> findMyInfo();

    List<FavoriteDTO> findMyFavorite();

    List<PetDTO> findMyPet();

    void deleteFavorite(String name);

}
