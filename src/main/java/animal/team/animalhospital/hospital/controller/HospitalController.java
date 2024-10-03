package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.*;
import animal.team.animalhospital.hospital.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;
    private final PersonService personService;
    private final PetService petService;
    private final FavoriteService favoriteService;

    @Autowired
    public HospitalController(HospitalService hospitalService, PersonService personService, PetService petService, FavoriteService favoriteService) {
        this.hospitalService = hospitalService;
        this.personService = personService;
        this.petService = petService;
        this.favoriteService = favoriteService;
    }

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "sidoList", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<SidoDTO> findPetNameList() {
        System.out.println("JavaScript 내장 함수인 fetch");
        return hospitalService.findAllSido();
    }

    @GetMapping("/sidoAll")
    public String allSido(Model model) {
        List<SidoDTO> sidoList = hospitalService.findAllSido();
        model.addAttribute("sidoList", sidoList);
        return "/hospital/info/sidoAll";
    }

    @GetMapping("/info/list/{eupmyeondongCode}")
    public String getHospitalsByEupmyeondong(@PathVariable int eupmyeondongCode, Model model) {
        List<HospitalDTO> hospitalList = hospitalService.getHospitalsByEupmyeondong(eupmyeondongCode);
        model.addAttribute("hospitalList", hospitalList);



        return "/hospital/info/list";
    }

    @GetMapping("/info/list")
    public String hospitalList(Model model) {
        List<HospitalDTO> hospitalList = hospitalService.findAllHospital();
        model.addAttribute("hospitalList", hospitalList);

        List<String> pathsList = new ArrayList<>();

        for (HospitalDTO hospitalDTO : hospitalList) {
            pathsList.add(hospitalDTO.getPhoto().split(",")[0]);
        }

        System.out.println("pathsList = " + pathsList);
        model.addAttribute("thumbnailList", pathsList); // 모델에 추가
        return "/hospital/info/list"; // 뷰 이름
    }

    @GetMapping("/search")
    public String searchHospital(@RequestParam(value = "query", required = false) String query, Model model) {
        // 임시 병원 리스트 (DB 연결을 대신하는 부분)
        List<HospitalDTO> hospitalList = hospitalService.searchHospital(query);

        // 결과를 모델에 담기
        model.addAttribute("hospitalList", hospitalList);

        List<String> pathsList = new ArrayList<>();

        for (HospitalDTO hospitalDTO : hospitalList) {
            pathsList.add(hospitalDTO.getPhoto().split(",")[0]);
        }

        System.out.println("pathsList = " + pathsList);
        model.addAttribute("thumbnailList", pathsList); // 모델에 추가


        return "hospital/info/list";
    }

    @GetMapping("/search/user")
    public String searchUser(@RequestParam(value = "query", required = false) String query, Model model) {
        List<PersonDTO> personList = hospitalService.searchUser(query);
        List<HospitalDTO> hospitalList = hospitalService.searchUserHospital(query);

        if (personList != null && !personList.isEmpty()) {
            model.addAttribute("personList", personList);
        } else {
            model.addAttribute("hospitalList", hospitalList);
        }

        System.out.println("personList = " + personList);
        System.out.println("hospitalList = " + hospitalList);

        return "/hospital/person/list";
    }

    @GetMapping("/info/detail/{code}")
    public String detailHospital(@PathVariable("code") int code, Model model) {
        HospitalDTO hospital = hospitalService.findByHospitalCode(code);

        System.out.println("hospital = " + hospital);

        if (hospital.getPhoto() != null) {

            String[] pathsArray = hospital.getPhoto().split(",");

            for (String s : pathsArray) {
                System.out.println("s = " + s);
            }

            model.addAttribute("pathsArray", pathsArray); // 모델에 추가
        }


//        for (int i = 0; i < pathsArray.length; i++) {
//            pathsArray[i] = "/static" + pathsArray[i];
//        }


        // 나의 person_code, 병원 hospital_code
        List<ReviewDTO> reviewList = hospitalService.findReviewByCode1(code);
//        String hospitalPhoto = hospitalService.findPhotoByCode(code);

//        System.out.println("detailHospital code = " + code);

        System.out.println("reviewList = " + reviewList);

        model.addAttribute("hospital", hospital); // 모델에 추가
        model.addAttribute("reviewList", reviewList);
        return "/hospital/info/detail"; // 뷰 이름
    }

    @GetMapping("/info/update/{hospitalCode}")
    public String updatePage(@PathVariable("hospitalCode") int hospitalCode, Model model) {
        HospitalDTO hospital = hospitalService.findByHospitalCode(hospitalCode);
        model.addAttribute("hospital", hospital);
        model.addAttribute("hospitalCode", hospital.getHospitalCode());
        return "/hospital/info/update";
    }

    @PostMapping("/info/update")
    public String updateHospital(HospitalDTO hospital,
                                 @RequestParam("sample3_address") String address,
                                 @RequestParam("hospitalCode") int hospitalCode,
                                 @RequestParam List<MultipartFile> multiHospitalFiles,
                                 RedirectAttributes rAttr) throws IOException {
        hospitalService.updateHospital(hospital);

        System.out.println("hospitalCode = " + hospitalCode);

        System.out.println("multiFiles = " + multiHospitalFiles);

        Resource resource = resourceLoader.getResource("classpath:static/images/uploadedFiles");
        System.out.println("resource 경로 확인 = " + resource);
        String filePath = null;

        if (!resource.exists()) {
            String root = "src/main/resources/static/images/uploadedFiles";
            File file = new File(root);
            file.mkdirs();

            filePath = file.getAbsolutePath();
        } else {
            filePath = resourceLoader.getResource("classpath:static/images/uploadedFiles").getFile().getAbsolutePath();
        }

//        List<FileDTO> files = new ArrayList<>();
        List<String> savedFilesPaths = new ArrayList<>();

        String photoPaths = "";

        try {
            for (int i = 0; i < multiHospitalFiles.size(); i++) {
                System.out.println("i = " + i);

                String originalFileName = multiHospitalFiles.get(i).getOriginalFilename();
                System.out.println("originalFileName = " + originalFileName);

                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                System.out.println("extension = " + extension);

                String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
                System.out.println("savedName = " + savedName);

                multiHospitalFiles.get(i).transferTo(new File(filePath + "/" + savedName));

//                newPet.setPetProfile("/images/uploadedFiles/" + savedName);
//                myPageService.updateMyPet(newPet);
                photoPaths += "/images/uploadedFiles/" + savedName + ",";

                savedFilesPaths.add("static/images/uploadedFiles/" + savedName);
            }

            System.out.println("photoPaths = " + photoPaths);

            System.out.println("end");

            Map<String, String> stringMap = new HashMap<>();

            System.out.println("test1");

            // 지역 정규화
            Pattern pattern = Pattern.compile("([가-힣]+동)\\s(\\d+-\\d+)");
            Matcher matcher = pattern.matcher(address);

            String addressDong = "";
            String addressNum = "";

//        System.out.println("matcher = " + matcher);
//        System.out.println("pattern = " + pattern);

            if (matcher.find()) {
                addressDong = matcher.group(1); // "신길동"
                addressNum = matcher.group(2);  // "469-3"
            }

            stringMap.put("photoPaths", photoPaths);
            stringMap.put("hospitalCode", String.valueOf(hospitalCode));
            stringMap.put("eupmyeondongCode", addressDong);
            stringMap.put("detailAddress", addressDong + " " + addressNum);

            System.out.println("test2");
            hospitalService.updateHospitalPhoto(stringMap);

            System.out.println("test3");

            rAttr.addFlashAttribute("message", "[Success] 다중 파일 업로드 성공!");
            rAttr.addFlashAttribute("imgs", savedFilesPaths);
            System.out.println("다중 파일 업로드 성공.");
        } catch (Exception e) {
            e.printStackTrace();

            rAttr.addFlashAttribute("message", "[Failed] 다중 파일 업로드 실패!!");
            System.out.println("다중 파일 업로드 실패.");
        }
        return "redirect:/hospital/info/detail/" + hospital.getHospitalCode();
    }


    @GetMapping("/info/sigungu/{sidoCode}")
    public String allSigungu(@PathVariable("sidoCode") int sidoCode, Model model) {
        List<SigunguDTO> sigunguList = hospitalService.findAllSigungu(sidoCode);
        model.addAttribute("sigunguList", sigunguList);
        model.addAttribute("sidoCode", sidoCode);  // 코드 값을 뷰에 전달
        return "hospital/info/sigungu";  // 템플릿 경로는 고정
    }

    @GetMapping("/info/eupmyeondong/{sigunguCode}")
    public String allEupmyeondong(@PathVariable("sigunguCode") int sigunguCode, Model model) {
        List<EupmyeondongDTO> eupmyeondongList = hospitalService.findAllEupmyeondong(sigunguCode);
        model.addAttribute("eupmyeondongList", eupmyeondongList);
        model.addAttribute("sigunguCode", sigunguCode);  // 코드 값을 뷰에 전달
        return "hospital/info/eupmyeondong";  // 템플릿 경로는 고정
    }

    @PostMapping("/info/delete/{code}")
    public String deleteHospital(@PathVariable("code") int code) {
        hospitalService.deleteHospital(code);
        return "redirect:/hospital/info/list";
    }

    @GetMapping("/person/list")
    public String PersonList(Model model) {
        List<PersonDTO> personList = personService.findAllPerson();
        List<HospitalDTO> hospitalList = hospitalService.findAllHospital();

        model.addAttribute("hospitalList", hospitalList);
        model.addAttribute("personList", personList);
        return "/hospital/person/list";
    }

    @GetMapping("/person/detail/{personCode}")
    public String detailPerson(@PathVariable("personCode") int personCode, Model model) {

        PersonDTO person = personService.selectPersonByCode(personCode);

        model.addAttribute("person", person);

        return "hospital/person/detail";
    }

    @GetMapping("pet/list")
    public String PetList(Model model) {

        List<PetDTO> petList = petService.findAllPet();

        model.addAttribute("petList", petList);

        return "hospital/pet/list";
    }

    @GetMapping("/pet/detail/{petCode}")
    public String detailPet(@PathVariable("petCode") int petCode, Model model) {

        PetDTO pet = petService.selectPetByCode(petCode);

        model.addAttribute("pet", pet);

        return "hospital/pet/detail";
    }

    @GetMapping("favorite/list")
    public String FavoriteList(Model model) {

        List<FavoriteDTO> favoriteList = favoriteService.findAllFavorite();

        model.addAttribute("favoriteList", favoriteList);

        return "hospital/favorite/list";
    }

    @PostMapping("/favorite/insert/{hospitalCode}")
    public ModelAndView favoriteInsert(ModelAndView mv,
                                 @PathVariable("hospitalCode") int hospitalCode,
                                 @RequestParam("permitNumber") int permitNumber,
                                 RedirectAttributes rAttr) {

//        System.out.println("favoriteInsert permitNumber = " + permitNumber);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();


        int count = favoriteService.favoriteCount(userEmail);

        if(count >= 3){
//            System.out.println("count = " + count);
//            System.out.println("favoriteInsert userEmail = " + userEmail);
            mv.setViewName("redirect:/hospital/info/detail/" + hospitalCode);
            System.out.println("즐겨찾기 항목이 가득 찼습니다. (3개)");
            mv.addObject("message", "즐겨찾기 항목이 가득 찼습니다. (3개)");
            return mv;
        }

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("userEmail", userEmail);
        stringMap.put("hospitalCode", String.valueOf(hospitalCode));
        stringMap.put("permitNumber", String.valueOf(permitNumber));

        System.out.println("stringMap = " + stringMap);

        boolean isExist = favoriteService.favoriteExist(stringMap);

        if(isExist) {
            System.out.println("isExist = " + isExist);
            mv.setViewName("redirect:/hospital/info/detail/" + hospitalCode);
            System.out.println("이미 즐겨찾기 한 병원입니다.");
            mv.addObject("message", "이미 즐겨찾기 한 병원입니다.");
            return mv;
        }


        favoriteService.favoriteInsert(stringMap);

//        List<FavoriteDTO> favoriteList = favoriteService.findAllFavorite();
//
//        model.addAttribute("favoriteList", favoriteList);

//        return "redirect:/myPage/myInfo";
        mv.setViewName("redirect:/myPage/myInfo");
//        return "hospitalrefavorite/list";
        return mv;
    }

//    @GetMapping("/info/hospitalUpdat")
//    public String petUpdate(Model model) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName();
//
//        PetDTO myPet = myPageService.findMyPetAlone(userEmail);
//
//        System.out.println("petUpdate myPet = " + myPet);
//
//        if(myPet == null) {
//            myPageService.insertMyPetAlone(userEmail);
//            myPet = myPageService.findMyPetAlone(userEmail);
//        }
//
//        model.addAttribute("myPet", myPet);
//
//        return "/hospital/myPage/petUpdate";
//    }

//    @GetMapping("/info/hospitalUpdate")
//    public String hospitalUpdate() {
//        return null;
//    }

//    @PostMapping("/info/hospitalUpdate")
//    public String hospitalMultiUpdate(@RequestParam("hospitalCode") int hospitalCode,
//                                      @RequestParam List<MultipartFile> multiHospitalFiles,
//                                      RedirectAttributes rAttr) throws IOException {
//
//        System.out.println("hospitalCode = " + hospitalCode);
//
//        System.out.println("multiFiles = " + multiHospitalFiles);
//
//        Resource resource = resourceLoader.getResource("classpath:static/images/uploadedFiles");
//        System.out.println("resource 경로 확인 = " + resource);
//        String filePath = null;
//
//        if(!resource.exists()) {
//            String root = "src/main/resources/static/images/uploadedFiles";
//            File file = new File(root);
//            file.mkdirs();
//
//            filePath = file.getAbsolutePath();
//        } else {
//            filePath = resourceLoader.getResource("classpath:static/images/uploadedFiles").getFile().getAbsolutePath();
//        }
//
////        List<FileDTO> files = new ArrayList<>();
//        List<String> savedFilesPaths = new ArrayList<>();
//
//        String photoPaths = "";
//
//        try {
//            for(int i = 0; i < multiHospitalFiles.size(); i++) {
//                System.out.println("i = " + i);
//
//                String originalFileName = multiHospitalFiles.get(i).getOriginalFilename();
//                System.out.println("originalFileName = " + originalFileName);
//
//                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
//                System.out.println("extension = " + extension);
//
//                String savedName = UUID.randomUUID().toString().replace("-", "") + extension;
//                System.out.println("savedName = " + savedName);
//
//                multiHospitalFiles.get(i).transferTo(new File(filePath + "/" + savedName));
//
////                newPet.setPetProfile("/images/uploadedFiles/" + savedName);
////                myPageService.updateMyPet(newPet);
//                photoPaths += "/images/uploadedFiles/" + savedName + ",";
//
//                savedFilesPaths.add("static/images/uploadedFiles/" + savedName);
//            }
//
//            System.out.println("photoPaths = " + photoPaths);
//
//            System.out.println("end");
//
//            Map<String, String> stringMap = new HashMap<>();
//
//            System.out.println("test1");
//
//            stringMap.put("photoPaths", photoPaths);
//            stringMap.put("hospitalCode", String.valueOf(hospitalCode));
//
//            System.out.println("test2");
//            hospitalService.updateHospitalPhoto(stringMap);
//
//            System.out.println("test3");
//
//            rAttr.addFlashAttribute("message", "[Success] 다중 파일 업로드 성공!");
//            rAttr.addFlashAttribute("imgs", savedFilesPaths);
//            System.out.println("다중 파일 업로드 성공.");
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            rAttr.addFlashAttribute("message", "[Failed] 다중 파일 업로드 실패!!");
//            System.out.println("다중 파일 업로드 실패.");
//        }
//
//        return "redirect:/hospital/info/detail/" + hospitalCode;
//
//    }

}
