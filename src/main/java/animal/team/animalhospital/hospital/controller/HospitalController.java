package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.*;
import animal.team.animalhospital.hospital.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

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

    @GetMapping(value="sidoList", produces = "application/json; charset=UTF-8")
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

    @GetMapping("/info/list")
    public String hospitalList(Model model) {
        List<HospitalDTO> hospitalList = hospitalService.findAllHospital();
        model.addAttribute("hospitalList", hospitalList); // 모델에 추가
        return "/hospital/info/list"; // 뷰 이름
    }

    @GetMapping("/info/detail/{code}")
    public String detailHospital(@PathVariable("code") int code, Model model) {
        HospitalDTO hospital = hospitalService.findByHospitalCode(code);
        model.addAttribute("hospital", hospital); // 모델에 추가
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
    public String updateHospital(HospitalDTO hospital) {

        HospitalDTO hospitalDTO = hospitalService.findByHospitalCode(hospital.getHospitalCode());

        hospitalDTO.setName(hospital.getName());
        hospitalDTO.setStartTime(hospital.getStartTime());
        hospitalDTO.setEndTime(hospital.getEndTime());
        hospitalDTO.setLunchStartTime(hospital.getLunchStartTime());
        hospitalDTO.setLunchEndTime(hospital.getLunchEndTime());
        hospitalDTO.setDetailAddress(hospital.getDetailAddress());
        hospitalDTO.setIntroText(hospital.getIntroText());
        hospitalDTO.setPhoto(hospital.getPhoto());

        hospitalService.updateHospital(hospitalDTO);
        return "redirect:/hospital/info/detail/" + hospitalDTO.getHospitalCode();
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
}
