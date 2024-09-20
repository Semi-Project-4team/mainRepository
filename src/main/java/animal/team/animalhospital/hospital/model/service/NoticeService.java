package animal.team.animalhospital.hospital.model.service;

import animal.team.animalhospital.hospital.model.dao.NoticeMapper;
import animal.team.animalhospital.hospital.model.dto.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeService(NoticeMapper noticeMapper) { this.noticeMapper = noticeMapper; }

    public List<NoticeDTO> findAllNotice() {
        return noticeMapper.findAllNotice();
    }

    @Transactional
    public void insertNotice(NoticeDTO newNotice) {
        noticeMapper.insertNotice(newNotice);
    }

    public NoticeDTO selectNoticeByCode(int code) {
        return noticeMapper.selectNoticeByCode(code);
    }

    @Transactional
    public void updateNotice(NoticeDTO notice) {
        noticeMapper.updateNotice(notice);
    }

    @Transactional
    public void deleteNotice(int code) {
        noticeMapper.deleteNotice(code);
    }
}

