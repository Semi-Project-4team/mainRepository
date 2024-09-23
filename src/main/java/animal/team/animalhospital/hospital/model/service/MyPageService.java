package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.MyPageMapper;
import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyPageService {

    private final MyPageMapper myPageMapper;

    @Autowired
    public MyPageService(MyPageMapper myPageMapper) {
        this.myPageMapper = myPageMapper;}

    public List<PersonDTO> findMyInfo() {
        return myPageMapper.findMyInfo();
    }

    public List<FavoriteDTO> findMyFavorite() { return myPageMapper.findMyFavorite(); }

    public List<PetDTO> findMyPet() { return myPageMapper.findMyPet();}

    @Transactional
    public void deleteFavorite(String name) {
        myPageMapper.deleteFavorite(name);
    }

}


