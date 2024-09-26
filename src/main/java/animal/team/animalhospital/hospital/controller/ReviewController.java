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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @GetMapping("/detail/{code}")
    public String findReviewDetail(@PathVariable("code") int code,
                                    Model model) {

        ReviewDTO review = reviewService.findReviewByCode(code);

        model.addAttribute("review", review);

        return "/hospital/review/detail";
    }

    @GetMapping("/regist")
    public String registPage() {

        return "/hospital/review/regist";
    }


    @PostMapping("/regist")
    public String registReview(ReviewDTO newReview,RedirectAttributes rAttr) {


        System.out.println("userDetails123 = ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        System.out.println("userDetails = " + userDetails);

        String userEmail = userDetails.getUsername();

        int userCode = personService.findByPersonCode(userEmail);

        System.out.println("userCode = " + userCode);


        newReview.setPersonCode(userCode);

        newReview.setHospitalCode(9);              // 병원코드 강제주입구문(임시)

        Date currentDate = new Date();
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

    @GetMapping("update/{code}")
    public String updatePage(@PathVariable("code") int code,
                             Model model) {
        ReviewDTO review = reviewService.findReviewByCode(code);

        model.addAttribute("review", review);

        return "hospital/review/update";
    }

    @PostMapping("/update")
    public String updateReview(ReviewDTO review, RedirectAttributes rAttr) {

        reviewService.updateReview(review);

        rAttr.addFlashAttribute("successMessage", "리뷰가 성공적으로 수정되었습니다.");

        return "redirect:/review/detail/" + review.getPersonCode();

    }

}

