package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.EupmyeondongDTO;
import animal.team.animalhospital.hospital.model.dto.FavoriteDTO;
import animal.team.animalhospital.hospital.model.dto.PersonDTO;
import animal.team.animalhospital.hospital.model.dto.PetDTO;
import animal.team.animalhospital.hospital.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/eupmyeondongAll")
    public String allEupmyeondong(Model model) {
        List<EupmyeondongDTO> eupmyeondongList = hospitalService.findAllEupmyeondong();

        model.addAttribute("eupmyeondongList", eupmyeondongList);

        return "/hospital/info/eupmyeondongAll";
    }

    @GetMapping("person/list")
    public String PersonList(Model model) {

        List<PersonDTO> personList = personService.findAllPerson();

        model.addAttribute("personList", personList);

        return "hospital/person/list";
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
