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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        Map<String, Object> params = new HashMap<>();
        params.put("personCode", code);
        params.put("hospitalCode", hospitalCode);

        ReserveDTO reserve = reserveService.findReserveByCode1(params);

        model.addAttribute("reserve", reserve);

        return "/hospital/reserve/detail";
    }



    @GetMapping(value="nameList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PetDTO> findPetNameList() {
        System.out.println("JavaScript 내장 함수인 fetch");
        return petService.findAllPet();
    }


    @GetMapping("/regist/{code}")
    public String registPage(@PathVariable("code") int code, Model model) {

        System.out.println("code = " + code);


        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setHospitalCode(code);

//        model.addAttribute("code", code);

//        ReserveDTO reserve = reserveService.registNewReserve(reserveDTO);
//        reserveService.registNewReserve(reserveDTO);
        model.addAttribute("hospital", code);
        model.addAttribute("reserve", reserveService);
        return "/hospital/reserve/regist";
    }


    @PostMapping("/registform/{code}")
    public String registReserve(ReserveDTO newReserve,
                                @PathVariable("code") int hospitalCode) {

        System.out.println("hospitalCode123 = " + hospitalCode);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String userEmail = userDetails.getUsername();

        System.out.println("userEmail = " + userEmail);
        System.out.println("hospitalCode = " + hospitalCode);

        int userCode = personService.findByPersonCode(userEmail);

        System.out.println("userCode = " + userCode);


        newReserve.setPersonCode(userCode);

        newReserve.setHospitalCode(hospitalCode);              // 병원코드 강제주입구문(임시)

        System.out.println("newReserve = " + newReserve);

        reserveService.registNewReserve(newReserve);



        return "redirect:/reserve/list";
    }

    @PostMapping("/delete/{code}")
    public String deleteReserve(@PathVariable("code") int code,
                                RedirectAttributes rAttr)   {


        reserveService.deleteReserve(code);

//        rAttr.addFlashAttribute("successMessage", "예약이 성공적으로 삭제되었습니다.");

        return "redirect:/reserve/list";

    }


    @GetMapping("/update/{code}/{hospitalCode}")
    public String updatePage(@PathVariable("code") int code,
                            @PathVariable("hospitalCode") int hospitalCode,
                            Model model) {



        Map<String, Object> params = new HashMap<>();
        params.put("personCode", code);
        params.put("hospitalCode", hospitalCode);


        ReserveDTO reserve = reserveService.findReserveByCode1(params);
        model.addAttribute("reserve", reserve);

        return "hospital/reserve/update"; // 뷰의 이름
    }

    @PostMapping("/update")
    public String updateReserve(@ModelAttribute ReserveDTO reserve) {


        reserveService.updateReserve1(reserve);

        return "redirect:/reserve/detail/" + reserve.getPersonCode() + "/" + reserve.getHospitalCode();
    }









}