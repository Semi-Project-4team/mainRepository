package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.HospitalMapper;
import animal.team.animalhospital.hospital.model.dao.PersonMapper;
import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonMapper personMapper;
    private final HospitalMapper hospitalMapper;

    @Autowired
    public PersonService(PersonMapper personMapper, HospitalMapper hospitalMapper) {
        this.personMapper = personMapper;
        this.hospitalMapper = hospitalMapper; }

    public List<PersonDTO> findAllPerson() { return personMapper.findAllPerson(); }

    public List<HospitalDTO> findAllHospital() { return hospitalMapper.findAllHospital(); }

    @Transactional
    public void insertPerson(PersonDTO newPerson) {
        personMapper.insertPerson(newPerson);
    }

    public PersonDTO selectPersonByCode(int code) {
        return personMapper.selectPersonByCode(code);
    }

    @Transactional
    public void updatePerson(PersonDTO person) {
        personMapper.updatePerson(person);
    }

    @Transactional
    public void deletePerson(int code) {
        personMapper.deletePerson(code);
    }


    public int findByPersonCode(String userEmail) {
        System.out.println("findByPersonCode : " + userEmail);

        int foundPersonCode = personMapper.findByPersonCode(userEmail);

        System.out.println("findByPersonCode = " + foundPersonCode);

        return foundPersonCode;
    }

}
