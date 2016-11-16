package tw314.tw314mobile.faq;

/**
 * Created by Pedro on 06/10/2016.
 */

public class FaqItem {

    // Atibutos de cada item da lista
    private String itemTitle;
    private String itemSubTitle;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemSubTitle() {
        return itemSubTitle;
    }

    public void setItemSubTitle(String itemSubTitle) {
        this.itemSubTitle = itemSubTitle;
    }

    public FaqItem(String itemTitle, String itemSubTitle) {
        this.itemTitle = itemTitle;
        this.itemSubTitle = itemSubTitle;
    }

}
