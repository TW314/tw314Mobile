package tw314.tw314mobile.faq;

/**
 * Created by Pedro on 06/10/2016.
 */

public class FaqItem {

    // Atibutos de cada item da lista
    private String postTitle;
    private String postSubTitle;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostSubTitle() {
        return postSubTitle;
    }

    public void setPostSubTitle(String postSubTitle) {
        this.postSubTitle = postSubTitle;
    }

    public FaqItem(String postTitle, String postSubTitle) {
        this.postTitle = postTitle;
        this.postSubTitle = postSubTitle;
    }

}
