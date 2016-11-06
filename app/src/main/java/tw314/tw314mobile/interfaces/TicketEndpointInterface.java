package tw314.tw314mobile.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tw314.tw314mobile.models.Ticket;

public interface TicketEndpointInterface {

    /*
     * Interface de consumo do webservice
     * Realiza o metodo GET passando o codigo_acesso
     * Passa para o Path a string codigoAcesso
     */
    @GET("ticket/{codigo_acesso}")
    Call<Ticket> getTicket(@Path("codigo_acesso") String codigoAcesso);
}
