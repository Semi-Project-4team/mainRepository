package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dao.ReviewMapper;
import animal.team.animalhospital.hospital.model.dto.ReviewDTO;
import animal.team.animalhospital.hospital.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private static final Logger logger = LogManager.getLogger(ReserveController.class);

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService, MessageSource messageSource) {
        this.reviewService = reviewService;
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


    @PostMapping("/list/regist")
    public String registReview(ReviewDTO newReview,RedirectAttributes rAttr) {

        reviewService.registNewReview(newReview);

        return "redirect:/hospital/review/list";

    }

}

