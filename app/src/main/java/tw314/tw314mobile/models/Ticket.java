package tw314.tw314mobile.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket implements Serializable{

    // @SerializedName indica qual Objeto do JSON deve ser serializado no atributo
    @SerializedName("numero_ticket")
    private int numeroTicket;
    @SerializedName("status_ticket")
    private StatusTicket statusTicket;
    @SerializedName("relacionamento_emp_svc")
    private RelacionamentoEmpSvc relacionamentoEmpSvc;

    // Instancia estatica do Ticket - para uso em toda a aplicacao
    private static Ticket instance;

    // Seta a instancia - chamado no acesso, passando o JSON
    public static void setInstance(Ticket ticket){
        Ticket.instance = ticket;
    }

    public static Ticket getInstance(){
        return Ticket.instance;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
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