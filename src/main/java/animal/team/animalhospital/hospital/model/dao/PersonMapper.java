package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonMapper {

    List<PersonDTO> findAllPerson();

    void insertPerson(PersonDTO person);

    PersonDTO selectPersonByCode(int code);

    void updatePerson(PersonDTO person);

    void deletePerson(int code);

    int personSignup(PersonDTO newUserInfo);

    PersonDTO findByPersonName(String personEmail);


    int findByPersonCode(String userEmail);

    PersonDTO personFindEmail(PersonDTO newUserInfo);

    String findByPersonEmail(String email);

    int updatePasswordPerson(PersonDTO newUserInfo);

    int isPersonSignCheck(String email);
}
