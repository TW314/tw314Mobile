package tw314.tw314mobile.services;

import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tw314.tw314mobile.activities.AccessActivity;
import tw314.tw314mobile.connectionHandler.ConnectionHandler;
import tw314.tw314mobile.enums.StatusTicketEnum;
import tw314.tw314mobile.enums.ValidationEnum;
import tw314.tw314mobile.interfaces.TicketInterface;
import tw314.tw314mobile.models.PeopleCounterReceiver;
import tw314.tw314mobile.models.Ticket;

public class TicketService {

    private Ticket mTicket;
    private PeopleCounterReceiver mPeopleCounter;

    // Cria um objeto de servico que implementa a interface de consumo
    private TicketInterface ticketInterface = ConnectionHandler.obtainConnection().create(TicketInterface.class);

    // Objetos de chamada do TicketEndpoint
    private Call<PeopleCounterReceiver> peopleCountCall;
    private Call<Ticket> ticketCall;

    /**
     *
     * @param accessCode = recebe codigo de entrada do AccessActivity
     * @return
     *
     * Chama o Ticket pelo codigo de acesso e valida se pode acessar o APP
     */
    public int obtainTicketByAccessCode(final String accessCode) {
        // Chamada do WS com metodo GET da interface

        ticketCall = ticketInterface.getTicket(accessCode);
        peopleCountCall = ticketInterface.getCountOfPeopleBeforeMe(accessCode);

        // Cria Thread para execucao do getTicket
        ticketCall.enqueue(new Callback<Ticket>() {

            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                mTicket = response.body();

                // Valida se o ticket pode acessar o APP e chama nova Thread para pegar o numero de pessoas na frente
                if (mTicket.getStatusTicketId() == StatusTicketEnum.AGUARDANDO_ATENDIMENTO) {
                    peopleCountCall.enqueue(new Callback<PeopleCounterReceiver>() {
                        @Override
                        public void onResponse(Call<PeopleCounterReceiver> call, Response<PeopleCounterReceiver> response) {
                            mPeopleCounter = response.body();
                        }

                        @Override
                        public void onFailure(Call<PeopleCounterReceiver> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {

            }
        });

        if (peopleCountCall.isExecuted()){
            // Seta instancia do objeto durante toda a aplicacao - para uso em todas as atividades
            Ticket.setInstance(mTicket);
            PeopleCounterReceiver.setPeopleCounterReceiver(mPeopleCounter);
            return ValidationEnum.OK;
        }
        else if (!peopleCountCall.isExecuted() && ticketCall.isExecuted())
            return ValidationEnum.ACCESS_FAIL;
        else
            return ValidationEnum.FAIL;


    }


    /**
     * Metodos de acesso ao Servido do Ticket
     * updateTicketByAccessCode recebe o codigo de acesso, atualiza o status do Ticket
     *  e chama o Servico, cancelando a espera do atendimento
     */
    public int updateTicketByAccessCode(String accessCode){
        // Chama o servico e faz atualizacao no WebService
        TicketInterface ticketInterface = ConnectionHandler.obtainConnection().create(TicketInterface.class);
        Ticket.getInstance().setStatusTicketId(StatusTicketEnum.CANCELADO);

        Call<ResponseBody> call = ticketInterface.updateTicket(accessCode, Ticket.getInstance());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        if (call.isExecuted())
            return ValidationEnum.OK;
        else
            return ValidationEnum.FAIL;
    }

}
