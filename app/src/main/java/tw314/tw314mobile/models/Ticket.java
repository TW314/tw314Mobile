package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("codigo_acesso")
    private String codigoAcesso;
    @SerializedName("numero_ticket")
    private Integer numeroTicket;
    @SerializedName("status_ticket")
    private StatusTicket statusTicket;
    @SerializedName("relacionamento_emp_svc")
    private RelacionamentoEmpSvc relacionamentoEmpSvc;

    public static Ticket ticket;

    public static void setInstance(Ticket ticket){
        Ticket.ticket = ticket;
    }

    public static Ticket getInstance(){
        return ticket;
    }

    public String getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(String codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }

    public Integer getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(Integer numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public StatusTicket getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(StatusTicket statusTicket) {
        this.statusTicket = statusTicket;
    }

    public RelacionamentoEmpSvc getRelacionamentoEmpSvc() {
        return relacionamentoEmpSvc;
    }

    public void setRelacionamentoEmpSvc(RelacionamentoEmpSvc relacionamentoEmpSvc) {
        this.relacionamentoEmpSvc = relacionamentoEmpSvc;
    }

}