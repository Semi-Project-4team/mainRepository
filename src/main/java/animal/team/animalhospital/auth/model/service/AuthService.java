package animal.team.animalhospital.auth.model.service;

import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
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
    public UserDetails loadUserByUsername(String hospitalEmail)
            throws UsernameNotFoundException {

        System.out.println("loadUserByUsername : " + hospitalEmail);

        HospitalDTO foundUser = userService.findByHospitalName(hospitalEmail);

        System.out.println("foundUser before return = " + foundUser);

        if (Objects.isNull(foundUser)) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        System.out.println("foundUser after return = " + foundUser);

        return foundUser;
    }
}
