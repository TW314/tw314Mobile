package tw314.tw314mobile.testeWS;

import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("numero_ticket")
    private int numeroTicket;
    @SerializedName("status_ticket")
    private StatusTicket statusTicket;
    @SerializedName("relacionamento_emp_svc")
    private RelacionamentoEmpSvc relacionamentoEmpSvc;

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