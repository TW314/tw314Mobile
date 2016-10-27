package tw314.tw314mobile.testeWS;

public class Ticket {
    private int numeroTicket;
    private int statusTicket;
    private String statusTicketDesc;
    private RelacionamentoStatusAtivacaoEnum.STATUS_ATIVACAO statusAtivacaoRel;
    private int servicoId;
    private String nomeServico;
    private String siglaServico;
    private int empresaId;
    private String nomeEmpresa;

    public Ticket(Ticket ticket){
        this.numeroTicket = ticket.getNumeroTicket();
        this.statusTicket = ticket.getStatusTicket();
        this.statusTicketDesc = ticket.getStatusTicketDesc();
        this.statusAtivacaoRel = ticket.getStatusAtivacaoRel();
        this.servicoId = ticket.getServicoId();
        this.nomeServico = ticket.getNomeServico();
        this.siglaServico = ticket.getSiglaServico();
        this.empresaId = ticket.getEmpresaId();
        this.nomeEmpresa = ticket.getNomeEmpresa();
    }

    public Ticket (int numeroTicket, int statusTicket, String nomeEmpresa, String nomeServico, String siglaServico){
        this.numeroTicket = numeroTicket;
        this.statusTicket = statusTicket;
        this.nomeEmpresa = nomeEmpresa;
        this.nomeServico = nomeServico;
        this.siglaServico = siglaServico;
    }

    public int getNumeroTicket() {
        return numeroTicket;
    }

    public void setNumeroTicket(int numeroTicket) {
        this.numeroTicket = numeroTicket;
    }

    public int getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(int statusTicket) {
        this.statusTicket = statusTicket;
    }

    public String getStatusTicketDesc() {
        return statusTicketDesc;
    }

    public void setStatusTicketDesc(String statusTicketDesc) {
        this.statusTicketDesc = statusTicketDesc;
    }

    public RelacionamentoStatusAtivacaoEnum.STATUS_ATIVACAO getStatusAtivacaoRel() {
        return statusAtivacaoRel;
    }

    public void setStatusAtivacaoRel(RelacionamentoStatusAtivacaoEnum.STATUS_ATIVACAO statusAtivacaoRel) {
        this.statusAtivacaoRel = statusAtivacaoRel;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getSiglaServico() {
        return siglaServico;
    }

    public void setSiglaServico(String siglaServico) {
        this.siglaServico = siglaServico;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
}
