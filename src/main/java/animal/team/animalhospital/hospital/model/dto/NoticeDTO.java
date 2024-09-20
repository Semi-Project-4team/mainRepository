package animal.team.animalhospital.hospital.model.dto;

import java.util.Date;

public class NoticeDTO {

    private int code;
    private String name;
    private Date date;
    private int views;
    private String text;

    public NoticeDTO() {
    }

    public NoticeDTO(int code, String name, Date date, int views, String text) {

        this.code = code;
        this.name = name;
        this.date = date;
        this.views = views;
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "noticeDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", text='" + text + '\'' +
                '}';
    }
}
