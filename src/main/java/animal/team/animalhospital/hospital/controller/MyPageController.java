package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.*;
import animal.team.animalhospital.hospital.model.service.FavoriteService;
import animal.team.animalhospital.hospital.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        List<ReserveDTO> myReserveList = myPageService.findMyReserve(userEmail);
        List<FavoriteDTO> myFavoriteList = myPageService.findMyFavorite(userEmail);
        List<PetDTO> myPetList = myPageService.findMyPet(userEmail);
        List<HospitalDTO> myHospitalList = myPageService.findMyHospital(userEmail);

        System.out.println("myInfoList = " + myInfoList);
        System.out.println("myReserveList = " + myReserveList);
        System.out.println("myFavoriteList = " + myFavoriteList);
        System.out.println("myPetList = " + myPetList);
        System.out.println("myHospitalList = " + myHospitalList);

        model.addAttribute("myInfoList", myInfoList);
        model.addAttribute("myReserveList", myReserveList);
        model.addAttribute("myHospitalList", myHospitalList);
        model.addAttribute("myFavoriteList", myFavoriteList);
        model.addAttribute("myPetList",myPetList);

        return "hospital/myPage/list";
    }

    @GetMapping("/update")
    public String updatePage(Model model) {
        // 현재 사용자 이메일을 가져옵니다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // 사용자 정보를 조회하여 모델에 추가합니다.
        PersonDTO updateMyInfo = myPageService.selectMyInfoByEmail(userEmail);
        model.addAttribute("updateMyInfo", updateMyInfo); // 정보 추가

        return "hospital/myPage/update"; // update.html 페이지로 이동
    }

    @PostMapping("/update")
    public String updateMyInfo(@ModelAttribute PersonDTO person) {
        System.out.println("person = " + person);
        myPageService.updateMyInfo(person);
        return "redirect:/myPage/myInfo";// 수정 후 myInfo 페이지로 리다이렉트
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


    @GetMapping("/updatePet")
    public String updatePetPage(Model model) {
        // 현재 인증된 사용자 정보 가져오기 (예: SecurityContextHolder 사용)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 인증된 사용자로부터 사용자의 코드를 추출하는 로직
        String userEmail = authentication.getName(); // 기본적으로 사용자명 또는 이메일을 얻음

        // 사용자명(이메일)을 통해 personCode를 조회하는 서비스 호출 (별도 서비스에서 처리)
        int personCode = myPageService.findPersonCodeByUserEmail(userEmail);
        System.out.println("personCode = " + personCode);
        // 사용자 코드를 사용하여 반려동물 정보를 가져옴
        PetDTO updateMyPet = myPageService.selectMyPetByPetPersonCode(personCode);

        // 반려동물 정보를 모델에 추가하여 뷰로 전달
        model.addAttribute("updateMyPet", updateMyPet);
        System.out.println("updateMyPet = " + updateMyPet);
        return "hospital/myPage/updatePet";
    }


}
