package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/myInfo")
    public String MyInfoList(Model model){
        List<PersonDTO> myInfoList = myPageService.findMyInfo();

        model.addAttribute("myInfoList",myInfoList);

        return "hospital/myPage/list";
    }
}
