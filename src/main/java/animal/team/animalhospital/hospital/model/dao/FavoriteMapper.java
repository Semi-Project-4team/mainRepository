package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteMapper {

    List<FavoriteDTO> findAllFavorite();

    void insertFavorite(FavoriteDTO favorite);

    FavoriteDTO selectFavoriteByCode(int code);

    void updateFavorite(FavoriteDTO favorite);

    void deleteFavorite(int code);
}
