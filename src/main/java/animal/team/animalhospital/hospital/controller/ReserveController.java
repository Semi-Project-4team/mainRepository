package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.ReserveDTO;
import animal.team.animalhospital.hospital.model.service.ReserveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reserve")
public class ReserveController {

    private static final Logger logger = LogManager.getLogger(ReserveController.class);

    private final ReserveService reserveService;

    @Autowired
    public ReserveController(ReserveService reserveService, MessageSource messageSource) {
        this.reserveService = reserveService;
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


}
