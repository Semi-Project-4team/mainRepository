package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.service.FavoriteService;
import animal.team.animalhospital.hospital.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        System.out.println("userEmail = " + userEmail);

        List<PersonDTO> myInfoList = myPageService.findMyInfo(userEmail);
        List<FavoriteDTO> myFavoriteList = myPageService.findMyFavorite(userEmail);

        System.out.println("myInfoList = " + myInfoList);
        System.out.println("myFavoriteList = " + myFavoriteList);

        model.addAttribute("myFavoriteList",myFavoriteList);
        model.addAttribute("myInfoList",myInfoList);

        return "hospital/myPage/list";
    }

    @PostMapping("/delete/{name}")
    public String deleteFavorite(@PathVariable("name") String name) {
        myPageService.deleteFavorite(name);

        return "redirect:/myPage/myInfo";
    }

//    @GetMapping("/myPet")
//    public String MyPetList(Model model){
//        List<FavoriteDTO> myFavoriteList = myPageService.findMyFavorite();
//
//        model.addAttribute("myFavoriteList",myFavoriteList);
//
//        return "hospital/myPage/list";
//    }
}
