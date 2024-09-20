package animal.team.animalhospital.hospital.model.dao;

import animal.team.animalhospital.hospital.model.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeDTO> findAllNotice();

    NoticeDTO selectNoticeByCode(int code);

    void updateNotice(NoticeDTO notice);

    void deleteNotice(int code);

    void insertNotice(NoticeDTO newNotice);
}
