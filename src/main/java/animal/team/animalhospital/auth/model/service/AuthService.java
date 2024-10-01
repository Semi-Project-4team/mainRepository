package animal.team.animalhospital.auth.model.service;

import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.UserDTO;
import animal.team.animalhospital.hospital.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        System.out.println("loadUserByUserEmail : " + email);

        HospitalDTO hospitalFoundUser = userService.findByHospitalName(email);


        System.out.println("hospital before return = " + hospitalFoundUser);

        if(!Objects.isNull(hospitalFoundUser)) {
            if (hospitalFoundUser.getIsAccountDrawal().charAt(0) == 'N') {
                throw new UsernameNotFoundException("(기업) 탈퇴 혹은 정지된 계정입니다.");
            }
            return hospitalFoundUser;
        }

        ////////////////////////////////////////

        PersonDTO personFoundUser = userService.findByPersonName(email);

        System.out.println("person before return1 = " + personFoundUser);

        if (Objects.isNull(personFoundUser)) {
            throw new UsernameNotFoundException("(개인) 회원 정보가 존재하지 않습니다.");
        } else if(personFoundUser.getIsAccountDrawal().charAt(0) == 'N') {
            System.out.println("test");
            throw new UsernameNotFoundException("(개인) 탈퇴 혹은 정지된 계정입니다.");
        }

//        } else if (Objects.isNull(hospitalFoundUser)) {
//            throw new UsernameNotFoundException("(기업) 회원 정보가 존재하지 않습니다.");
//        }
        System.out.println("person before return2 = " + personFoundUser);

        return personFoundUser;
    }
}
