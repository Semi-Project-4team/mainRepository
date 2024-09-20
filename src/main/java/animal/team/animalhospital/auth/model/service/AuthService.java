package animal.team.animalhospital.auth.model.service;

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
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserDTO foundUser = userService.findByUsername(username);

        if (Objects.isNull(foundUser)) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        return foundUser;
    }
}
