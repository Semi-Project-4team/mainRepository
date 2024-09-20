package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.NoticeDTO;
import animal.team.animalhospital.hospital.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/list")
    public String NoticeList(Model model) {

        List<NoticeDTO> noticeList = noticeService.findAllNotice();

        model.addAttribute("noticeList", noticeList);

        return "/hospital/notice/list";
    }

    @GetMapping("/detail/{code}")
    public String detailNotice(@PathVariable("code") int code, Model model) {

        NoticeDTO notice = noticeService.selectNoticeByCode(code);

        model.addAttribute("notice", notice);

        return "/hospital/notice/detail";
    }

}
