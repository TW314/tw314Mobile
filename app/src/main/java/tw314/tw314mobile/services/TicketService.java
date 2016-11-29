package tw314.tw314mobile.services;

import android.content.Intent;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tw314.tw314mobile.activities.AccessActivity;
import tw314.tw314mobile.activities.MainLayoutActivity;
import tw314.tw314mobile.connectionHandler.ConnectionHandler;
import tw314.tw314mobile.enums.StatusTicketEnum;
import tw314.tw314mobile.enums.ValidationEnum;
import tw314.tw314mobile.interfaces.TicketInterface;
import tw314.tw314mobile.models.PeopleCounter;
import tw314.tw314mobile.models.Ticket;

public class TicketService {

    private TicketInterface ticketInterface = ConnectionHandler.obtainConnection().create(TicketInterface.class);
    /**
     * Recebe o codigo de acesso, atualiza o status do Ticket
     *  e chama o Servico, cancelando a espera do atendimento
     */
    public int updateTicketByAccessCode(String accessCode){
        // Chama o servico e faz atualizacao no WebService
        Ticket.getTicket().setStatusTicketId(StatusTicketEnum.CANCELADO);

        Call<ResponseBody> ticketCall = ticketInterface.updateTicket(accessCode, Ticket.getTicket());
        ticketCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        if (ticketCall.isExecuted())
            return ValidationEnum.OK;
        else
            return ValidationEnum.FAIL;
    }
}
