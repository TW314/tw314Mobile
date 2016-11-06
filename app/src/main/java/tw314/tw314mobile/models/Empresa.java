package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Empresa implements Serializable {

    // @SerializedName indica qual Objeto do JSON deve ser serializado no atributo
    @SerializedName("id")
    private int id;
    @SerializedName("razao_social")
    private String razaoSocial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

}