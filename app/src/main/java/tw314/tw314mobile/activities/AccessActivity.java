package tw314.tw314mobile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tw314.tw314mobile.R;
import tw314.tw314mobile.connectionHandler.ConnectionHandler;
import tw314.tw314mobile.interfaces.TicketEndpointInterface;
import tw314.tw314mobile.models.Ticket;

public class AccessActivity extends AppCompatActivity {

    // Objeto Ticket
    private Ticket mTicket;
    // Atributo do botao de acesso
    private Button btnAccess;
    // Atributo da caixa de texto do codigo
    private EditText txtAccess;
    // Atributo que recebe o codigo do txtAccess
    private String accessCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        // Instanciando objeto EditText
        txtAccess = (EditText) findViewById(R.id.access_code);

        // Instanciando objeto Botao
        btnAccess = (Button) findViewById(R.id.access_button);

        // Adiciona evento de click ao btnAccess
        btnAccess.setOnClickListener(btnAccessClick);
    }

    // Atributo que controla resposta ao clique do btnAccess
    private View.OnClickListener btnAccessClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            accessCode = txtAccess.getText().toString();
            Toast.makeText(AccessActivity.this, "Carregando dados da senha", Toast.LENGTH_SHORT).show();
            obtainTicketByAccessCode(accessCode);
        }
    };

    // Metodo de chamada do Servico de consumo
    private void obtainTicketByAccessCode(String accessCode){

        // Cria um objeto de servico que implementa a interface de consumo
        TicketEndpointInterface ticketService = ConnectionHandler.obtainTicketByHTTPGet().create(TicketEndpointInterface.class);

        // Chamada do WS com metodo GET da interface
        Call<Ticket> call = ticketService.getTicket(accessCode);
        // Tarefa que recebe response do WS
        call.enqueue(new Callback<Ticket>(){
            // Metodo de response -> Apenas e chamado se chegou ao WS e fez retorno
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                // Objeto Ticket recebe corpo do response
                mTicket = response.body();

                // Seta instancia do objeto durante toda a aplicacao - para uso em todas as atividades
                Ticket.setInstance(mTicket);

                // Chama MainLayoutActivity
                startActivity(new Intent(AccessActivity.this, MainLayoutActivity.class));
            }

            // Metodo de response -> Apenas e chamado se chegou ao WS e falhou
            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                // TODO: Achar um jeito de voltar resposta pro usuário
                Log.i("TW314", "Não pegou objeto");
            }
        });
    }
}