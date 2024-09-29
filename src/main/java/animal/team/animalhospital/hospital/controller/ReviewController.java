package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.ReviewDTO;
import animal.team.animalhospital.hospital.model.service.PersonService;
import animal.team.animalhospital.hospital.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private static final Logger logger = LogManager.getLogger(ReserveController.class);

    private final ReviewService reviewService;
    private final PersonService personService;


    @Autowired
    public ReviewController(ReviewService reviewService, MessageSource messageSource,PersonService personService) {
        this.reviewService = reviewService;
        this.personService = personService;

    }

    @GetMapping("/list")
    public String findReviewList(Model model) {

        List<ReviewDTO> reviewList = reviewService.findAllReview();

        model.addAttribute("reviewList", reviewList);

        return "/hospital/review/list";
    }

    @GetMapping("/detail/{code}/{hospitalCode}")
    public String findReviewDetail(@PathVariable("code") int code,
                                    @PathVariable("hospitalCode") int hospitalCode,
                                    Model model) {

        Map<String, Object> params = new HashMap<>();
        params.put("personCode",code);
        params.put("hospitalCode",hospitalCode);

        ReviewDTO review = reviewService.findReviewByCode1(params);

        model.addAttribute("review", review);

        return "/hospital/review/detail";
    }

    @GetMapping("/regist/{code}")
    public String registPage(@PathVariable("code") int code, Model model) {

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setHospitalCode(code);

        model.addAttribute("hospital", code);
        model.addAttribute("review", reviewService);

        return "/hospital/review/regist";
    }


    @PostMapping("/registform/{code}")
    public String registReview(ReviewDTO newReview,
                               @PathVariable("code") int hospitalCode) {


        System.out.println("userDetails123 = ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println("userDetails = " + userDetails);

        String userEmail = userDetails.getUsername();

        int userCode = personService.findByPersonCode(userEmail);

        System.out.println("userCode = " + userCode);


        newReview.setPersonCode(userCode);

        newReview.setHospitalCode(hospitalCode);
//        newReview.setHospitalCode(9);              // 병원코드 강제주입구문(임시)

        LocalDate currentDate = LocalDate.now();
        newReview.setReviewWriteDate(currentDate);
        newReview.setReviewModifyDate(currentDate);

        reviewService.registNewReview(newReview);

        return "redirect:/review/list";

    }

    @PostMapping("/delete/{code}")
    public String deleteReview(@PathVariable("code") int code,
                               RedirectAttributes rAttr)    {

        reviewService.deleteReview(code);
//        rAttr.addFlashAttribute("successMessage", "리뷰가 성공적으로 삭제되었습니다.");

        return "redirect:/review/list";

    }

    @GetMapping("/update/{code}/{hospitalCode}")
    public String updatePage(@PathVariable("code") int code,
                             @PathVariable("hospitalCode") int hospitalCode,
                             Model model) {

        Map<String, Object> params = new HashMap<>();
        params.put("personCode", code);
        params.put("hospitalCode",hospitalCode);


        ReviewDTO review = reviewService.findReviewByCode1(params);
        model.addAttribute("review", review);

        return "hospital/review/update";
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute ReviewDTO review) {

        reviewService.updateReview1(review);


        return "redirect:/review/detail/" + review.getPersonCode() + "/" + review.getHospitalCode();

    }

}

