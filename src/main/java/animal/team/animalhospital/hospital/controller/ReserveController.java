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

import java.util.List;

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

    @GetMapping("/detail/{code}")
    public String findReserveDetail(@PathVariable("code") int code,
                                 Model model) {

        ReserveDTO reserve = reserveService.findReserveByCode(code);

        model.addAttribute("reserve", reserve);

        return "/hospital/reserve/detail";
    }



    @GetMapping(value="nameList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PetDTO> findPetNameList() {
        System.out.println("JavaScript 내장 함수인 fetch");
        return petService.findAllPet();
    }

    @GetMapping("/regist")
    public String registPage() {

        return "/hospital/reserve/regist";
    }


    @PostMapping("/regist")
    public String registReserve(ReserveDTO newReserve, RedirectAttributes rAttr) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String userEmail = userDetails.getUsername();

        int userCode = personService.findByPersonCode(userEmail);


        newReserve.setPersonCode(userCode);

        newReserve.setHospitalCode(7);              // 병원코드 강제주입구문(임시)

        reserveService.registNewReserve(newReserve);


//        logger.info("Locale : {}", locale);

//        rAttr.addFlashAttribute("successMessage", "신규 예약 등록에 성공하셨습니다.");
//        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registReserve", null, locale));

        return "redirect:/reserve/list";
    }

    @PostMapping("/delete/{code}")
    public String deleteReserve(@PathVariable("code") int code,
                                RedirectAttributes rAttr)   {

        reserveService.deleteReserve(code);

//        rAttr.addFlashAttribute("successMessage", "예약이 성공적으로 삭제되었습니다.");

        return "redirect:/reserve/list";

    }

    @GetMapping("/update/{code}")
    public String updatePage(@PathVariable("code") int code,
                                   Model model) {

        ReserveDTO reserve = reserveService.findReserveByCode(code);

        model.addAttribute("reserve", reserve);

        return "hospital/reserve/update";
    }


    @PostMapping("/update")
    public String updateReserve(ReserveDTO reserve, RedirectAttributes rAttr){

        reserveService.updateReserve(reserve);

        rAttr.addFlashAttribute("successMessage", "예약이 성공적으로 수정되었습니다.");


        return "redirect:/reserve/detail/" + reserve.getPersonCode();
    }






}
