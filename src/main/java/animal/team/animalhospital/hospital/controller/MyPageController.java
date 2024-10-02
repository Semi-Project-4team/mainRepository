package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.*;
import animal.team.animalhospital.hospital.model.service.FavoriteService;
import animal.team.animalhospital.hospital.model.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final MyPageService myPageService;

    @Autowired
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @Autowired
    private ResourceLoader resourceLoader;

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

        for (HospitalDTO hospitalDTO : myHospitalList) {
            hospitalDTO.setUserCode(myInfoList.get(0).getPersonCode());
        }
        System.out.println("myHospitalList = " + myHospitalList);

        model.addAttribute("myInfoList", myInfoList);
        model.addAttribute("myReserveList", myReserveList);
        model.addAttribute("myHospitalList", myHospitalList);
        model.addAttribute("myFavoriteList", myFavoriteList);
        model.addAttribute("myPetList",myPetList);

        return "hospital/myPage/list";
    }

    @GetMapping("hospitalInfo")
    public String hospitalInfoList(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        System.out.println("userEmail = " + userEmail);

        List<HospitalDTO> hospitalInfoList = myPageService.findHospitalInfo(userEmail);
        List<ReserveDTO> hospitalReserveList = myPageService.findHospitalReserve(userEmail);
        List<PersonDTO> protector = myPageService.findProtector(userEmail);

        System.out.println("hospitalInfoList = " + hospitalInfoList);
        System.out.println("hospitalReserveList = " + hospitalReserveList);
        System.out.println("protector = " + protector);

        for (PersonDTO personDTO : protector) {
            personDTO.setUserCode(hospitalInfoList.get(0).getUserCode());
        }

        model.addAttribute("hospitalInfoList", hospitalInfoList);
        model.addAttribute("hospitalReserveList", hospitalReserveList);
        model.addAttribute("protector",protector);

        return ("/hospital/myPage/hospitalInfo");
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

    @GetMapping("/petUpdate")
    public String petUpdate(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        PetDTO myPet = myPageService.findMyPetAlone(userEmail);

        System.out.println("petUpdate myPet = " + myPet);

        if(myPet == null) {
            myPageService.insertMyPetAlone(userEmail);
            myPet = myPageService.findMyPetAlone(userEmail);
        }

        model.addAttribute("myPet", myPet);

        return "/hospital/myPage/petUpdate";
    }

    @PostMapping("/petUpdate")
    public String petPostUpdate(@RequestParam MultipartFile singleProFile,
                                RedirectAttributes rAttr,
                                PetDTO newPet) throws IOException {

        System.out.println("singleProFile = " + singleProFile);
        System.out.println("newPet = " + newPet);

        Resource resource = resourceLoader.getResource("classpath:static/images/uploadedFiles");
        System.out.println("resource 경로 확인 = " + resource);

        String filePath = null;
        if(!resource.exists()) {
            // 경로가 존재하지 않을 때
            String root = "src/main/resource/static/images/uploadedFiles";

            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            // 경로가 이미 존재할 때
            filePath = resourceLoader.getResource("classpath:static/images/uploadedFiles").getFile().getAbsolutePath();
        }

        System.out.println("filePath = " + filePath);

        String originalFileName = singleProFile.getOriginalFilename();
        System.out.println("originalFileName = " + originalFileName);

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        System.out.println("extension = " + extension);

        String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
        System.out.println("savedName = " + savedName);

        try {

            singleProFile.transferTo(new File(filePath + "/" + savedName));

//            System.out.println("filePath = " + filePath);
//            System.out.println("savedName = " + savedName);
//            System.out.println("total = " + filePath + savedName);
//            System.out.println("singleProFile = " + singleProFile);

            /* 설명. 원래는 이 위치에서 DB에 INSERT하는 서비스(비즈니스 로직)을 수행해야 한다.*/
            newPet.setPetProfile("/images/uploadedFiles/" + savedName);
            myPageService.updateMyPet(newPet);

            rAttr.addFlashAttribute("message", "[Success] 단일 파일 업로드 성공!");
            rAttr.addFlashAttribute("img", "static/images/uploadedFiles" + "/" + savedName);
            // 서버측 로그 남기기
            System.out.println("[Success] 단일 파일 업로드 성공!"); // System

        } catch (IOException e) {


            // 트랜잭션 처리 도중 예외가 발생할
            new File(filePath + "/" + savedName).delete();

            e.printStackTrace();

            // 리다이렉트 후, 데이터 공유를 위한 RedirectAttributes에 값 저장.
            rAttr.addFlashAttribute("message", "[Failed] 단일 파일 업로드 실패!"); // 브라우저

            // 서버측 로그 남기기
            System.out.println("[Failed] 단일 파일 업로드 실패!"); // System
        }

        return "redirect:/myPage/myInfo";
    }

    @GetMapping("/userWithDrawal")
//    public String userWithDrawal(HttpSecurity http) throws Exception {
    public String userWithDrawal(RedirectAttributes rAttr,
                                 Model model) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        myPageService.userWithDrawal(userEmail);

//        http.logout(logout -> {
//            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
//            logout.deleteCookies("JSESSIONID");
//            logout.invalidateHttpSession(true);
//            logout.logoutSuccessUrl("/");
//        }).csrf(csrf ->
//                csrf.disable()
//        );
//        return http.build();


        rAttr.addFlashAttribute("successMessage", "(개인) 성공적으로 탈퇴했습니다.");

        return "redirect:/auth/logout";
    }

    @GetMapping("/hospitalWithDrawal")
//    public String userWithDrawal(HttpSecurity http) throws Exception {
    public String hospitalWithDrawal(RedirectAttributes rAttr,
                                 Model model) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        myPageService.hospitalWithDrawal(userEmail);

//        http.logout(logout -> {
//            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
//            logout.deleteCookies("JSESSIONID");
//            logout.invalidateHttpSession(true);
//            logout.logoutSuccessUrl("/");
//        }).csrf(csrf ->
//                csrf.disable()
//        );
//        return http.build();


        rAttr.addFlashAttribute("successMessage", "(기업) 성공적으로 탈퇴했습니다.");

        return "redirect:/auth/logout";
    }

}
