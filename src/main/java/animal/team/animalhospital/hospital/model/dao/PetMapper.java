package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.PetDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {

    List<PetDTO> findAllPet();

    void insertPet(PetDTO pet);

    PetDTO selectPetByCode(int code);

    void updatePet(PetDTO pet);

    void deletePet(int code);

    List<PetDTO> findPetsByPersonCode(int userCode);
}
