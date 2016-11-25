package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

public class Empresa {

    @SerializedName("id")
    private Integer id;
    @SerializedName("razao_social")
    private String razaoSocial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

}
