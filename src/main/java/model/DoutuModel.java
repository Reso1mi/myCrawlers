package model;


public class DoutuModel {
    private Long id;
    private String topic;
    private String imgUrl;
    private String title;

    //数据库id自增
    public DoutuModel(String topic, String imgUrl, String title) {
        this.topic = topic;
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
