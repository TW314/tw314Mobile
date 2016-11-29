package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("codigo_acesso")
    private String codigoAcesso;
    @SerializedName("numero_ticket")
    private Integer numeroTicket;
    @SerializedName("statusTicketId")
    private int statusTicketId;
    @SerializedName("relacionamento_emp_svc")
    private RelacionamentoEmpSvc relacionamentoEmpSvc;

    public static Ticket ticket;

    public static void setTicket(Ticket ticket){
        Ticket.ticket = ticket;
    }

    public static Ticket getTicket(){
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

    public int getStatusTicketId() {
        return statusTicketId;
    }

    public void setStatusTicketId(int statusTicketId) {
        this.statusTicketId = statusTicketId;
    }

    public RelacionamentoEmpSvc getRelacionamentoEmpSvc() {
        return relacionamentoEmpSvc;
    }

    public void setRelacionamentoEmpSvc(RelacionamentoEmpSvc relacionamentoEmpSvc) {
        this.relacionamentoEmpSvc = relacionamentoEmpSvc;
    }

}