package tw314.tw314mobile.layout;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tw314.tw314mobile.R;
import tw314.tw314mobile.testeWS.Ticket;
import tw314.tw314mobile.testeWS.TicketHttp;

public class AccessActivity extends AppCompatActivity {

    // Objeto para utilizar classe Async
    private TicketTask mTicketTask;
    // Objeto Ticket
    private Ticket mTicket;
    // Atributo do botao de acesso
    private Button btnAccess;
    // Atributo da caixa de texto do codigo
    private EditText txtAccess;
    // Atributo que recebe o codigo do txtAccess
    private String codigoAcesso;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        // Instanciando objeto EditText
        txtAccess = (EditText) findViewById(R.id.access_code);
        codigoAcesso = txtAccess.getText().toString();

        // Instanciando objeto Botao
        btnAccess = (Button) findViewById(R.id.access_button);

        // Adiciona evento de click ao btnAccess
        btnAccess.setOnClickListener(btnAccessClick);
    }

    // Atributo que controla resposta ao clique do btnAccess
    private View.OnClickListener btnAccessClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mTicketTask == null){
                if (TicketHttp.hasConnection(AccessActivity.this)){
                    searchTicket();
                } else {
                    Toast.makeText(AccessActivity.this, "Sem conexão de internet", Toast.LENGTH_SHORT).show();
                }
            } else if (mTicketTask.getStatus() == AsyncTask.Status.RUNNING) {
                // Exibir progress bar
            }
            startActivity(new Intent(AccessActivity.this, MainLayoutActivity.class));
        }
    };

    // Metodo que inicia Thread Async
    public void searchTicket(){
        if (mTicketTask == null || mTicketTask.getStatus() != AsyncTask.Status.RUNNING){
            mTicketTask = new TicketTask();
            mTicketTask.execute();
        }
    }

    // Classe Assincrona que executa Thread de consumo do WebService em Background
    public class TicketTask extends AsyncTask<Void, String, Ticket>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            // Exibir Progress Bar
        }

        @Override
        protected Ticket doInBackground(Void... string) {
            // Faz a chamada do JSON para receber os parametros
            return TicketHttp.getTicketJson(codigoAcesso);
        }

        @Override
        protected void onPostExecute(Ticket ticket){
            super.onPostExecute(ticket);
            if (ticket != null){
                mTicket = new Ticket(ticket);
                // Chama ActivityMain e passa o objeto Ticket com Intent;
                Toast.makeText(AccessActivity.this, ticket.getSiglaServico(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
