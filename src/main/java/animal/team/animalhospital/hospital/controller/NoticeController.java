package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.NoticeDTO;
import animal.team.animalhospital.hospital.model.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/insert")
    public String insertPage() {
        return "/hospital/notice/insert";
    }

    @PostMapping("/insert")
    public String insertNotice(NoticeDTO newNotice) {
        noticeService.insertNotice(newNotice);
        return "redirect:/notice/list";
    }

    @GetMapping("/update/{code}")
    public String updatePage(@PathVariable("code") int code, Model model) {
        NoticeDTO notice = noticeService.selectNoticeByCode(code);
        model.addAttribute("notice", notice);
        return "hospital/notice/update";
    }

    @PostMapping("/update")
    public String updateNotice(NoticeDTO notice) {
        noticeService.updateNotice(notice);
        return "redirect:/notice/detail/" + notice.getCode();
    }

    @PostMapping("/delete/{code}")
    public String deleteNotice(@PathVariable("code") int code) {
        noticeService.deleteNotice(code);
        return "redirect:/notice/list";
    }
}
