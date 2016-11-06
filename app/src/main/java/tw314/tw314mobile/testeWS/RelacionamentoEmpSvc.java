package tw314.tw314mobile.testeWS;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RelacionamentoEmpSvc implements Serializable {

    @SerializedName("status_ativacao")
    private String statusAtivacao;
    @SerializedName("servico")
    private Servico servico;
    @SerializedName("empresa")
    private Empresa empresa;

    public String getStatusAtivacao() {
        return statusAtivacao;
    }

    public void setStatusAtivacao(String statusAtivacao) {
        this.statusAtivacao = statusAtivacao;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}