package tw314.tw314mobile.testeWS;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Pedro on 04/11/2016.
 */

public interface ApiEndpointInterface {

    @GET("ticket/{codigo_acesso}")
    Call<Ticket> getTicket(@Path("codigo_acesso") String codigoAcesso);
}
