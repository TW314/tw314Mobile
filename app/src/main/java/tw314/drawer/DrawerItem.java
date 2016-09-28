package tw314.drawer;

/**
 * Created by Pedro on 27/09/2016.
 */
public class DrawerItem {

    // Atributos da lista
    String ItemName;
    int imgResID;

    // Atributos de cabecalho
    String title;

    public DrawerItem(String title) {
        this(null, 0);
        this.title = title;
    }

    public DrawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



}
