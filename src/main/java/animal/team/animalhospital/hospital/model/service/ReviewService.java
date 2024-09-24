package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.ReviewMapper;
import animal.team.animalhospital.hospital.model.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewMapper reviewMapper) {

        this.reviewMapper = reviewMapper;
    }




    public List<ReviewDTO> findAllReview() {

        return reviewMapper.findAllReview();
    }


    public ReviewDTO findReviewByCode(int code) {

        return reviewMapper.findReviewByCode(code);
    }

    @Transactional
    public void registNewReview(ReviewDTO newReview) {

        reviewMapper.registNewReview(newReview);
    }



}
