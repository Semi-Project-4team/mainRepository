package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.service.FavoriteService;
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
    public MyPageController(MyPageService myPageService, FavoriteService favoriteService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/myInfo")
    public String MyInfoList(Model model){
        List<PersonDTO> myInfoList = myPageService.findMyInfo();
        List<FavoriteDTO> myFavoriteList = myPageService.findMyFavorite();

        model.addAttribute("myFavoriteList",myFavoriteList);
        model.addAttribute("myInfoList",myInfoList);

        return "hospital/myPage/list";
    }

    @GetMapping("/myFavorite")
    public String MyFavoriteList(Model model){
        List<FavoriteDTO> myFavoriteList = myPageService.findMyFavorite();

        model.addAttribute("myFavoriteList",myFavoriteList);

        return "hospital/myPage/list";
    }

    @GetMapping("/myPet")
    public String MyPetList(Model model){
        List<FavoriteDTO> myFavoriteList = myPageService.findMyFavorite();

        model.addAttribute("myFavoriteList",myFavoriteList);

        return "hospital/myPage/list";
    }
}
