package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.FavoriteMapper;
import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteService {

    private final FavoriteMapper favoriteMapper;

    @Autowired
    public FavoriteService(FavoriteMapper favoriteMapper) { this.favoriteMapper = favoriteMapper; }

    public List<FavoriteDTO> findAllFavorite() { return favoriteMapper.findAllFavorite(); }

    @Transactional
    public void insertFavorite(FavoriteDTO newFavorite) {
        favoriteMapper.insertFavorite(newFavorite);
    }

    public FavoriteDTO selectFavoriteByCode(int code) {
        return favoriteMapper.selectFavoriteByCode(code);
    }

    @Transactional
    public void deleteFavorite(int code) { favoriteMapper.deleteFavorite(code);
    }


    public void favoriteInsert(Map<String, String> stringMap) {
        favoriteMapper.favoriteInsert(stringMap);
    }

    public int favoriteCount(String userEmail) {
        return favoriteMapper.favoriteCount(userEmail);
    }

    public boolean favoriteExist(Map<String, String> stringMap) {
        return favoriteMapper.favoriteExist(stringMap);
    }
}
