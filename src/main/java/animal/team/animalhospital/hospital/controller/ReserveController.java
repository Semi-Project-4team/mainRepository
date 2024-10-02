package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.PetDTO;
import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import animal.team.animalhospital.hospital.model.service.PersonService;
import animal.team.animalhospital.hospital.model.service.PetService;
import animal.team.animalhospital.hospital.model.service.ReserveService;
import animal.team.animalhospital.hospital.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

    private static final Logger logger = LogManager.getLogger(ReserveController.class);

    private final ReserveService reserveService;
    private final MessageSource messageSource;
    private final PetService petService;
    private final UserService userService;
    private final PersonService personService;

    @Autowired
    public ReserveController(ReserveService reserveService, MessageSource messageSource, PetService petService, UserService userService, PersonService personService) {
        this.reserveService = reserveService;
        this.messageSource = messageSource;
        this.petService = petService;
        this.userService = userService;
        this.personService = personService;
    }



    @GetMapping("/list")
    public String findReserveList(Model model) {

        List<ReserveDTO> reserveList = reserveService.findAllReserve();

        model.addAttribute("reserveList", reserveList);

        return "/hospital/reserve/list";


    }

    @GetMapping("/detail/{code}/{hospitalCode}")
    public String findReserveDetail(@PathVariable("code") int code,
                                 @PathVariable("hospitalCode") int hospitalCode,
                                 Model model) {

        Map<String, Integer> params = new HashMap<>();
        params.put("personCode", hospitalCode);
        params.put("hospitalCode", code);

        System.out.println("params = " + params);

        ReserveDTO reserve = reserveService.findReserveByCode1(params);

        System.out.println("reserve = " + reserve);

        model.addAttribute("reserve", reserve);

        System.out.println("test");

        return "/hospital/reserve/detail";
    }



    @GetMapping(value="nameList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PetDTO> findPetNameList() {
        return petService.findAllPet();
    }


    @GetMapping("/regist/{code}")
    public String registPage(@PathVariable("code") int code, Model model) {

        System.out.println("code = " + code);


        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setHospitalCode(code);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername(); // 이메일 추출

        int userCode = personService.findByPersonCode(userEmail);

        // personCode에 해당하는 pet 목록 조회
        List<PetDTO> petList = petService.findPetsByPersonCode(userCode);


        model.addAttribute("hospital", code);
        model.addAttribute("reserve", reserveService);
        model.addAttribute("petList", petList);          // personCode에 해당하는 반려동물 목록

        return "/hospital/reserve/regist";
    }


    @PostMapping("/registform/{code}")
    public ModelAndView registReserve(ReserveDTO newReserve,
                                      @PathVariable("code") int hospitalCode) {

        ModelAndView mv = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userEmail = userDetails.getUsername();

        int userCode = personService.findByPersonCode(userEmail);

        newReserve.setPersonCode(userCode);
        newReserve.setHospitalCode(hospitalCode);

        // 중복 예약 체크
        boolean isDuplicate = reserveService.isDuplicateReserve(newReserve);

        if (isDuplicate) {
            return resultMV(mv, null, "[registReserve] 이미 해당 병원에 예약이 존재합니다.");
        }

        // 중복이 없으면 예약 진행
        Integer result = reserveService.registNewReserve(newReserve);


        System.out.println("result = " + result);

        return resultMV(mv, result, "예약이 완료되었습니다.");
    }



    @PostMapping("/delete/{code}/{hospitalCode}")
    public String deleteReserve(@PathVariable("code") int code,
                                @PathVariable("hospitalCode") int hospitalCode,
                                Model model)   {

        Map<String, Object> params = new HashMap<>();
        params.put("personCode", code);
        params.put("hospitalCode", hospitalCode);

        reserveService.deleteReserve1(params);

        return "redirect:/myPage/myInfo";

    }


    @GetMapping("/update/{code}/{hospitalCode}")
    public String updatePage(@PathVariable("code") int code,
                            @PathVariable("hospitalCode") int hospitalCode,
                            Model model) {



        Map<String, Integer> params = new HashMap<>();
        params.put("personCode", code);
        params.put("hospitalCode", hospitalCode);


        ReserveDTO reserve = reserveService.findReserveByCode1(params);
        model.addAttribute("reserve", reserve);

        return "hospital/reserve/update"; // 뷰의 이름
    }

    @PostMapping("/update")
    public String updateReserve(@ModelAttribute ReserveDTO reserve) {


        reserveService.updateReserve1(reserve);

        return "redirect:/reserve/detail/" + reserve.getHospitalCode() + "/" + reserve.getPersonCode();
    }

    private ModelAndView resultMV(ModelAndView mv, Integer result, String message) {
//        String message = null;

        if (result == null) {
            message= "이미 예약한 병원입니다.";

            mv.setViewName("/hospital/reserve/regist");
        } else if (result == 0) {
            message= "[resultMV] 예약에 실패했습니다. 다시 시도해주세요.";

            mv.setViewName("/hospital/reserve/regist");

        } else if (result >= 1){
            message = "[resultMV] 예약이 성공적으로 완료되었습니다.";

            mv.setViewName("redirect:/myPage/myInfo");

        }
        System.out.println("message = " + message);


        System.out.println("최종result = " + result);

        mv.addObject("message", message);

        return mv;
    }

}