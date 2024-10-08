package animal.team.animalhospital.hospital.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class NoticeDTO {

    private int code;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int views;
    private String text;
    private String status;

    public NoticeDTO() {
    }

    public NoticeDTO(int code, String name, LocalDate date, int views, String text, String status) {

        this.code = code;
        this.name = name;
        this.date = date;
        this.views = views;
        this.text = text;
        this.status = status;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", views=" + views +
                ", text='" + text + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
