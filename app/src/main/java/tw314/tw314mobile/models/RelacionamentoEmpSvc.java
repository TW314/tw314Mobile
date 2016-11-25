package tw314.tw314mobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelacionamentoEmpSvc {

    @SerializedName("empresaId")
    private Integer empresaId;
    @SerializedName("servicoId")
    private Integer servicoId;
    @SerializedName("servico")
    private Servico servico;
    @SerializedName("empresa")
    private Empresa empresa;

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Integer getServicoId() {
        return servicoId;
    }

    public void setServicoId(Integer servicoId) {
        this.servicoId = servicoId;
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