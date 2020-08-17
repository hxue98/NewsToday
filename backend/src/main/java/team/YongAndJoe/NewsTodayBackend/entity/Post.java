package team.YongAndJoe.NewsTodayBackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Post {

    private long id;

    private long userId;
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String text;
    private List<String> imageUrls;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imagesUrl) {
        this.imageUrls = imagesUrl;
    }
}
