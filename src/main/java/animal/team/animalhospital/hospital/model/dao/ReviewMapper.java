package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {

    
    List<ReviewDTO> findAllReview();

    ReviewDTO findReviewByCode(int code);

    void registNewReview(ReviewDTO newReview);

    void deleteReview(int code);

    void updateReview(ReviewDTO review);
}
