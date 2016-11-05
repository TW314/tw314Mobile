package tw314.tw314mobile.testeWS;

import com.google.gson.annotations.SerializedName;

public class Empresa {

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