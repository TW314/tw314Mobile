package tw314.tw314mobile.testeWS;

import com.google.gson.annotations.SerializedName;

public class Servico {

    @SerializedName("id")
    private int id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("sigla")
    private String sigla;

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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}