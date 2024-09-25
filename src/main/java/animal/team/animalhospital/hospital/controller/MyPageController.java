package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.PetDTO;
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
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/myInfo")
    public String MyInfoList(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        System.out.println("userEmail = " + userEmail);

        List<PersonDTO> myInfoList = myPageService.findMyInfo(userEmail);
        List<FavoriteDTO> myFavoriteList = myPageService.findMyFavorite(userEmail);
        List<PetDTO> myPetList = myPageService.findMyPet(userEmail);

        System.out.println("myInfoList = " + myInfoList);
        System.out.println("myFavoriteList = " + myFavoriteList);
        System.out.println("myPetList = " + myPetList);

        model.addAttribute("myFavoriteList", myFavoriteList);
        model.addAttribute("myInfoList", myInfoList);
        model.addAttribute("myPetList",myPetList);

        return "hospital/myPage/list";
    }

    @PostMapping("/delete/{name}")
    public String deleteFavorite(@PathVariable("name") String name) {
        myPageService.deleteFavorite(name);

        return "redirect:/myPage/myInfo";
    }

    @GetMapping("/insert")
    public String insertPage() {return "/hospital/myPage/insert";}

    @PostMapping("/insert")
    public String insertMyPet(PetDTO newPet) {
        myPageService.insertMyPet(newPet);
        return "redirect:/myPage/myInfo";
    }

}
