package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.MyPageMapper;
import animal.team.animalhospital.hospital.model.dto.*;
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

    public List<PersonDTO> findMyInfo(String userEmail) {
        return myPageMapper.findMyInfo(userEmail);
    }

    public List<ReserveDTO> findMyReserve(String userEmail) {
        return myPageMapper.findMyReserve(userEmail);
    }

    public List<HospitalDTO> findMyHospital(String userEmail) {
        return myPageMapper.findMyHospital(userEmail);
    }

    public List<FavoriteDTO> findMyFavorite(String userEmail) {
        return myPageMapper.findMyFavorite(userEmail);
    }

    public List<PetDTO> findMyPet(String userEmail) {
        return myPageMapper.findMyPet(userEmail);
    }

    @Transactional
    public void deleteFavorite(String name) {
        myPageMapper.deleteFavorite(name);
    }

    public void insertMyPet(PetDTO newPet) {
        myPageMapper.insertMyPet(newPet);
    }


    public PersonDTO selectMyInfoByEmail(String userEmail) {return myPageMapper.selectMyInfoByEmail(userEmail);
    }

    @Transactional
    public void updateMyPet(PetDTO petDTO) {
        System.out.println("updateMyPet petDTO = " + petDTO);
        myPageMapper.updateMyPet(petDTO);
    }


    public PetDTO findMyPetAlone(String userEmail) {
        return myPageMapper.findMyPetAlone(userEmail);
    }

    @Transactional
    public void updateMyInfo(PersonDTO person) {
        myPageMapper.updateMyInfo(person);
    }
}