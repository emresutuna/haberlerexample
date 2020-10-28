package example.emre.com.haberlerexample.Models;

public class News {
    private String category;
    private String title;
    private String spot;
    private boolean redirects;
    private boolean isAdvertorial;
    private String publishDate;
    private long id;
    private String imageUrl;
    private String videoUrl;
    private String webUrl;
    private long commentCount;
    private String imageSize;
    private Body[] body;
    private Object[] related;
    private Emoji emoji;
    private Object[] topComments;
    public String getCategory() { return category; }
    public void setCategory(String value) { this.category = value; }
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }
    public String getSpot() { return spot; }
    public void setSpot(String value) { this.spot = value; }
    public boolean getRedirects() { return redirects; }
    public void setRedirects(boolean value) { this.redirects = value; }
    public boolean getIsAdvertorial() { return isAdvertorial; }
    public void setIsAdvertorial(boolean value) { this.isAdvertorial = value; }
    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String value) { this.publishDate = value; }
    public long getID() { return id; }
    public void setID(long value) { this.id = value; }
    public String getImageURL() { return imageUrl; }
    public void setImageURL(String value) { this.imageUrl = value; }

    public String getVideoURL() { return videoUrl; }
    public void setVideoURL(String value) { this.videoUrl = value; }

    public String getWebURL() { return webUrl; }
    public void setWebURL(String value) { this.webUrl = value; }

    public long getCommentCount() { return commentCount; }
    public void setCommentCount(long value) { this.commentCount = value; }

    public String getImageSize() { return imageSize; }
    public void setImageSize(String value) { this.imageSize = value; }

    public Body[] getBody() { return body; }
    public void setBody(Body[] value) { this.body = value; }

    public Object[] getRelated() { return related; }
    public void setRelated(Object[] value) { this.related = value; }

    public Emoji getEmoji() { return emoji; }
    public void setEmoji(Emoji value) { this.emoji = value; }

    public Object[] getTopComments() { return topComments; }
    public void setTopComments(Object[] value) { this.topComments = value; }
}