package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    List<PersonDTO> findMyInfo(String userEmail);

    List<FavoriteDTO> findMyFavorite(String userEmail);

    void deleteFavorite(String name);
}
