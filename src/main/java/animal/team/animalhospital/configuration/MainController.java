package animal.team.animalhospital.configuration;

import animal.team.animalhospital.hospital.model.dto.NoticeDTO;
import animal.team.animalhospital.hospital.model.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    private final NoticeService noticeService;

    public MainController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping(value = {"/", "/main"})
    public String Main(Model model) {

        List<NoticeDTO> noticeList = noticeService.findAllNotice();
        model.addAttribute("noticeList", noticeList);
        return "/common/main";
    }
}
