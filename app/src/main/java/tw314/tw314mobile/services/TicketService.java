package tw314.tw314mobile.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import tw314.tw314mobile.models.PeopleCounterReceiver;
import tw314.tw314mobile.models.Ticket;

public interface TicketService {

    /*
     * Interface de consumo do webservice
     * Realiza o metodo GET passando o codigo_acesso
     * Passa para o Path a string codigoAcesso
     */
    @GET("ticket/{accessCode}")
    Call<Ticket> getTicket(@Path("accessCode") String accessCode);

    // Pega numero de pessoas na fila a frente do Ticket
    @GET("ticket/pessoas/{accessCode")
    Call<PeopleCounterReceiver> getCountOfPeopleBeforeMe(@Path("accessCode") String accessCode);

    // Atualiza o Ticket de acordo com o Codigo de Acesso
    @PUT("ticket/{accessCode}")
    Call<ResponseBody> updateTicket(@Path("accessCode") String accessCode, @Body Ticket ticket);

}
