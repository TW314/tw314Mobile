package tw314.tw314mobile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tw314.tw314mobile.R;
import tw314.tw314mobile.connectionHandler.ConnectionHandler;
import tw314.tw314mobile.enums.StatusTicketEnum;
import tw314.tw314mobile.interfaces.TicketInterface;
import tw314.tw314mobile.models.PeopleCounter;
import tw314.tw314mobile.models.Ticket;
import tw314.tw314mobile.services.TicketService;

public class AccessActivity extends AppCompatActivity {

    // Objeto Ticket
    private Ticket mTicket;
    private TicketService ticketService;
    private TicketInterface ticketInterface = ConnectionHandler.obtainConnection().create(TicketInterface.class);
    // Atributo do botao de acesso
    private Button mAccessButton;
    // Atributo da caixa de texto do codigo
    private EditText mAccessText;
    // Atributo que recebe o codigo do txtAccess
    private String mAccessCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        ticketService = new TicketService();

        // Instanciando objeto EditText
        mAccessText = (EditText) findViewById(R.id.access_code);

        // Instanciando objeto Botao
        mAccessButton = (Button) findViewById(R.id.access_button);

        // Adiciona evento de click ao btnAccess
        mAccessButton.setOnClickListener(btnAccessClick);
    }

    // Atributo que controla resposta ao clique do btnAccess
    private View.OnClickListener btnAccessClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mAccessCode = mAccessText.getText().toString();
            Toast.makeText(AccessActivity.this, "Aguarde... Carregando dados da senha...", Toast.LENGTH_SHORT).show();
            obtainTicketByAccessCode(mAccessCode);
        }
    };

    public void obtainTicketByAccessCode(final String accessCode){
        Call<Ticket> ticketCall = ticketInterface.getTicket(accessCode);

        ticketCall.enqueue(new Callback<Ticket>() {

            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                mTicket = response.body();
                // Valida se o ticket pode acessar o APP
                if ((mTicket.getStatusTicketId() == StatusTicketEnum.AGUARDANDO_ATENDIMENTO) || (mTicket.getStatusTicketId() == StatusTicketEnum.EM_ATENDIMENTO)) {
                    Ticket.setTicket(mTicket);
                    obtainPeopleCountByAccessCode(mAccessCode);
                } else
                    Toast.makeText(AccessActivity.this, "Senha indisponível para acesso.\n" +
                            "Verifique o código e tente novamente.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                Toast.makeText(AccessActivity.this, "Falha ao carregar dados da senha.\n" +
                        "Espere uns minutos e tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void obtainPeopleCountByAccessCode(String accessCode){

        Call<PeopleCounter> peopleCountCall = ticketInterface.getCountOfPeopleBeforeMe(accessCode);
        peopleCountCall.enqueue(new Callback<PeopleCounter>() {
            @Override
            public void onResponse(Call<PeopleCounter> call, Response<PeopleCounter> response) {
                PeopleCounter.setPeopleCounter(response.body());
                if (Ticket.getTicket() != null && PeopleCounter.getPeopleCounter() != null)
                    startActivity(new Intent(AccessActivity.this, MainLayoutActivity.class));
            }

            @Override
            public void onFailure(Call<PeopleCounter> call, Throwable t) {
                Toast.makeText(AccessActivity.this, "Falha ao carregar dados da senha.\n" +
                        "Espere uns minutos e tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
