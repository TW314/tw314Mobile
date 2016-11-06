package tw314.tw314mobile.testeWS;

import com.google.gson.annotations.SerializedName;

public class StatusTicket {

    @SerializedName("id")
    private int id;
    @SerializedName("nome")
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}