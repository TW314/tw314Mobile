package tw314.tw314mobile.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @FormUrlEncoded
    @POST("ticket/{codigo_acesso}")
    Call<ResponseBody> updateTicket(@Path("codigo_acesso") String codigoAcesso, @Body Ticket ticket);
}
