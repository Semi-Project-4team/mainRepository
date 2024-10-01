package animal.team.animalhospital.hospital.controller;

import animal.team.animalhospital.hospital.model.dto.ReviewDTO;
import animal.team.animalhospital.hospital.model.service.PersonService;
import animal.team.animalhospital.hospital.model.service.ReviewService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private static final Logger logger = LogManager.getLogger(ReserveController.class);

    private final ReviewService reviewService;
    private final PersonService personService;

    @Autowired
    private ResourceLoader resourceLoader;

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
    public String registReview(@RequestParam("singleReviewFile") MultipartFile singleReviewFile,
                                RedirectAttributes rAttr,
                                ReviewDTO newReview,
                               @PathVariable("code") int hospitalCode) throws IOException {

        System.out.println("singleReviewFile = " + singleReviewFile);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

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

        String originalFileName = singleReviewFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        String savedName = UUID.randomUUID().toString().replace("-", "") + extension;

        try {

            singleReviewFile.transferTo(new File(filePath + "/" + savedName));

            String userEmail = userDetails.getUsername();

            int userCode = personService.findByPersonCode(userEmail);

            System.out.println("userCode = " + userCode);


            newReview.setPersonCode(userCode);

            newReview.setHospitalCode(hospitalCode);
//        newReview.setHospitalCode(9);              // 병원코드 강제주입구문(임시)

            LocalDate currentDate = LocalDate.now();
            newReview.setReviewWriteDate(currentDate);
            newReview.setReviewModifyDate(currentDate);
            newReview.setReviewPhoto("/images/uploadedFiles/" + savedName);

            reviewService.registNewReview(newReview);

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
        return "redirect:/review/list";
    }

    @PostMapping("/delete/{code}/{hospitalCode}")
    public String deleteReview(@PathVariable("code") int code,
                               @PathVariable("hospitalCode") int hospitalCode,
                               Model model)    {

        Map<String, Object> params = new HashMap<>();
        params.put("personCode", code);
        params.put("hospitalCode", hospitalCode);

        reviewService.deleteReview1(params);

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

