package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StatusTicket implements Serializable {

    // @SerializedName indica qual Objeto do JSON deve ser serializado no atributo
    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}