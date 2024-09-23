package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.SignupDTO;
import animal.team.animalhospital.hospital.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signupHospital")
    public void hospitalSignup() {}

    @PostMapping("/signupHospital")
    public ModelAndView hospitalSignup(ModelAndView mv,
                               @ModelAttribute HospitalDTO newUserInfo) {
        System.out.println(newUserInfo.toString());
        System.out.println(newUserInfo.getInformationCollection());
        Integer result = userService.hospitalSignup(newUserInfo);

        return resultMV(mv, result, "hospital");
    }

    @GetMapping("/signupPerson")
    public void personSignup() {}

    @PostMapping("/signupPerson")
    public ModelAndView personSignup(ModelAndView mv,
                                       @ModelAttribute PersonDTO newUserInfo) {
        System.out.println(newUserInfo.toString());
        System.out.println(newUserInfo.getInformationCollection());
        Integer result = userService.personSignup(newUserInfo);

        return resultMV(mv, result, "person");
    }

    @GetMapping("/joinMembership")
    public String joinTheMembership() {
        return "/user/joinMembership";
    }

    private ModelAndView resultMV(ModelAndView mv, Integer result, String user) {
        String message = null;

        if (result == null) {
            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
            System.out.println(message);

            mv.setViewName("signup_" + user);
        } else if (result == 0) {
            message = "회원가입에 실패했습니다. 다시 시도해주세요.";
            System.out.println(message);

            mv.setViewName("signup_" + user);
        } else if (result >= 1) {
            message = "회원가입이 성공적으로 완료되었습니다.";
            System.out.println(message);

            mv.setViewName("/common/main");
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
            System.out.println(message);

            mv.setViewName("signup_" + user);
        }

        return mv;
    }

}
