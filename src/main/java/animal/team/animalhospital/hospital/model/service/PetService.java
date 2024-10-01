package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.PetMapper;
import animal.team.animalhospital.hospital.model.dto.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {

    private final PetMapper petMapper;

    @Autowired
    public PetService(PetMapper petMapper) { this.petMapper = petMapper; }

    public List<PetDTO> findAllPet() { return petMapper.findAllPet(); }

    @Transactional
    public void insertPet(PetDTO newPet) {
        petMapper.insertPet(newPet);
    }

    public PetDTO selectPetByCode(int code) {
        return petMapper.selectPetByCode(code);
    }

    @Transactional
    public void updatePet(PetDTO pet) {
        petMapper.updatePet(pet);
    }

    @Transactional
    public void deletePet(int code) {
        petMapper.deletePet(code);
    }

    public List<PetDTO> findPetsByPersonCode(int userCode) {
        return petMapper.findPetsByPersonCode(userCode);
    }
}
