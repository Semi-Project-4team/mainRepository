package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.HospitalDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.SignupDTO;
import animal.team.animalhospital.hospital.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

            mv.setViewName("/auth/login");
        } else {
            message = "알 수 없는 오류가 발생했습니다. 다시 시도해보시거나 관리자에게 문의해주세요.";
            System.out.println(message);

            mv.setViewName("signup_" + user);
        }

        return mv;
    }

    @GetMapping("/joinFindId")
    public String joinFindId() {
        return "/user/joinFindId";
    }

    @GetMapping("/findIdHospital")
    public void hospitalFindId() {}

    @PostMapping("/findIdHospital")
    public String hospitalFindEmail(Model mv,
                                 @ModelAttribute HospitalDTO newUserInfo) {
//        System.out.println(newUserInfo.toString());
//        System.out.println(newUserInfo.getInformationCollection());
        HospitalDTO resultList = userService.hospitalFindEmail(newUserInfo);

        mv.addAttribute("resultList", resultList);

        return "/user/findIdList";

    }

    @GetMapping("/findIdPerson")
    public void personFindEmail() {}

    @PostMapping("/findIdPerson")
    public String personFindEmail(Model mv,
                                 @ModelAttribute PersonDTO newUserInfo) {
//        System.out.println(newUserInfo.toString());
//        System.out.println(newUserInfo.getInformationCollection());
        PersonDTO resultList = userService.personFindEmail(newUserInfo);

        mv.addAttribute("resultList", resultList);

        return "/user/findIdList";

    }

    @GetMapping("/joinFindPassword")
    public String joinFindPassword() {
        return "/user/joinFindPassword";
    }



    @GetMapping("/findPasswordHospital")
    public String findPasswordHospital() {
        return "/user/findPasswordHospital";
    }

    @GetMapping("/findPasswordPerson")
    public String findPasswordPerson() {
        return "/user/findPasswordPerson";
    }

    @GetMapping("/findPasswordHospitalForm")
    public String findPasswordHospitalForm(@RequestParam("email") String email, Model mv) {
        // 입력된 email db에 있는 지 확인

        boolean isResult = userService.findByHospitalEmail(email);

        System.out.println("isResult = " + isResult);

        if(!isResult) {
            mv.addAttribute("error", "검색된 아이디가 없습니다.");
            return "/auth/error";
        }

        mv.addAttribute("email", email);

        return "/user/resetHospitalPassword";
    }

    @GetMapping("/findPasswordPersonForm")
    public String findPasswordPersonForm(@RequestParam("email") String email, Model mv) {
        // 입력된 email db에 있는 지 확인

        boolean isResult = userService.findByPersonEmail(email);

        System.out.println("isResult = " + isResult);

        if(!isResult) {
            mv.addAttribute("error", "검색된 아이디가 없습니다.");
            return "/auth/error";
        }

        mv.addAttribute("email", email);

        return "/user/resetPersonPassword";
    }

    @PostMapping("/updatePasswordHospital")
    public String updatePasswordHospital(Model mv,
                                         @RequestParam("email") String email,  // 이메일 값 받기
                                         @RequestParam("newPassword") String newPassword,
                                         @RequestParam("confirmPassword") String confirmPassword) {

        if (isPasswordEquals(mv, email, newPassword, confirmPassword))
            return "/user/resetHospitalPassword";

        HospitalDTO newUserInfo = new HospitalDTO();
        newUserInfo.setEmail(email);
        newUserInfo.setPassword(newPassword);


        userService.updatePasswordHospital(newUserInfo);


        return "/auth/login";

    }

    @PostMapping("/updatePasswordPerson")
    public String updatePasswordPerson(Model mv,
                                         @RequestParam("email") String email,  // 이메일 값 받기
                                         @RequestParam("newPassword") String newPassword,
                                         @RequestParam("confirmPassword") String confirmPassword) {

        if (isPasswordEquals(mv, email, newPassword, confirmPassword))
            return "/user/resetPersonPassword";

        PersonDTO newUserInfo = new PersonDTO();
        newUserInfo.setEmail(email);
        newUserInfo.setPassword(newPassword);


        userService.updatePasswordPerson(newUserInfo);

        return "/auth/login";

    }

    private boolean isPasswordEquals(Model mv,
                                     @RequestParam("email") String email,
                                     @RequestParam("newPassword") String newPassword,
                                     @RequestParam("confirmPassword") String confirmPassword) {
        if(!newPassword.equals(confirmPassword)) {
            mv.addAttribute("error", "패스워드가 같지 않습니다.");
            mv.addAttribute("email", email);
            return true;
        }

        System.out.println("email = " + email);
        System.out.println("newPassword = " + newPassword);
        System.out.println("confirmPassword = " + confirmPassword);
        return false;
    }

}
