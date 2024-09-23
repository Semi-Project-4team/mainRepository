package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.MyPageMapper;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

