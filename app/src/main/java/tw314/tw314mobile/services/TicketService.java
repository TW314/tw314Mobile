package tw314.tw314mobile.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import tw314.tw314mobile.models.Ticket;

public interface TicketService {

    /*
     * Interface de consumo do webservice
     * Realiza o metodo GET passando o codigo_acesso
     * Passa para o Path a string codigoAcesso
     */
    @GET("ticket/{codigo_acesso}")
    Call<Ticket> getTicket(@Path("codigo_acesso") String codigoAcesso);

    @Multipart
    @PUT("ticket/{codigo_acesso}")
    Call<Ticket> updateTicket(@Path("codigo_acesso") String codigoAcesso, @Part("statusTicketId") int statusTicketId);
}
