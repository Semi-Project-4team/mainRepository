package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewDTO> findAllReview();

    ReviewDTO findReviewByCode(int code);
}
