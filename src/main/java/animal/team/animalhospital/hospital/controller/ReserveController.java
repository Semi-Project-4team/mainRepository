package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.PetDTO;
import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import animal.team.animalhospital.hospital.model.service.PetService;
import animal.team.animalhospital.hospital.model.service.ReserveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

    private static final Logger logger = LogManager.getLogger(ReserveController.class);

    private final ReserveService reserveService;
    private final MessageSource messageSource;
    private final PetService petService;

    @Autowired
    public ReserveController(ReserveService reserveService, MessageSource messageSource, PetService petService) {
        this.reserveService = reserveService;
        this.messageSource = messageSource;
        this.petService = petService;
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


    @GetMapping("/regist")
    public String registPage() {
        return "/hospital/reserve/regist";
    }

    @GetMapping(value="nameList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<PetDTO> findPetNameList() {
        System.out.println("JavaScript 내장 함수인 fetch");
        return petService.findAllPet();
    }

    @PostMapping("/regist")
    public String registReserve(ReserveDTO newReserve, RedirectAttributes rAttr, Locale locale) {

        System.out.println("controller start");

        System.out.println("newReserve = " + newReserve);

//        newReserve.setPersonCode(2);
//        newReserve.setHospitalCode(2);

        System.out.println("newReserve = " + newReserve);

        reserveService.registNewReserve(newReserve);

        System.out.println("controller end");

//        logger.info("Locale : {}", locale);

//        rAttr.addFlashAttribute("successMessage", "신규 예약 등록에 성공하셨습니다.");
//        rAttr.addFlashAttribute("successMessage", messageSource.getMessage("registReserve", null, locale));

        return "redirect:/hospital/reserve/list";
    }






}
