package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.ReviewMapper;
import animal.team.animalhospital.hospital.model.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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


    @Transactional
    public void deleteReview(int code) {

            reviewMapper.deleteReview(code);
    }

    @Transactional
    public void updateReview(ReviewDTO review) {
        reviewMapper.updateReview(review);
    }


    public ReviewDTO findReviewByCode1(Map<String, Object> params) {
        return reviewMapper.findReviewByCode1(params);
    }

    public void updateReview1(ReviewDTO review) {
        reviewMapper.updateReview1(review);
    }
}
