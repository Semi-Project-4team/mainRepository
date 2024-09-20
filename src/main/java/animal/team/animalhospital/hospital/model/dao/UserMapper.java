package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.SignupDTO;
import animal.team.animalhospital.hospital.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int regist(SignupDTO newUserInfo);

    UserDTO findByUsername(String username);
}
