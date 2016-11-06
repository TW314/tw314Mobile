package tw314.tw314mobile.layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tw314.tw314mobile.R;
import tw314.tw314mobile.testeWS.ApiClient;
import tw314.tw314mobile.testeWS.ApiEndpointInterface;
import tw314.tw314mobile.testeWS.Ticket;

public class AccessActivity extends AppCompatActivity {


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

        // Instanciando objeto Botao
        btnAccess = (Button) findViewById(R.id.access_button);

        // Adiciona evento de click ao btnAccess
        btnAccess.setOnClickListener(btnAccessClick);
    }

    // Atributo que controla resposta ao clique do btnAccess
    private View.OnClickListener btnAccessClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            codigoAcesso = txtAccess.getText().toString();
            Toast.makeText(AccessActivity.this, "Carregando dados da senha", Toast.LENGTH_SHORT).show();
            addData(codigoAcesso);
        }
    };

    private void addData(String codigoAcesso){
        ApiEndpointInterface apiService = ApiClient.getTicket().create(ApiEndpointInterface.class);

        Call<Ticket> call = apiService.getTicket(codigoAcesso);
        Log.i("TAG", "CodigoAcesso: " + codigoAcesso);
        call.enqueue(new Callback<Ticket>(){
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                mTicket = response.body();
                Log.i("TAG", "Pegou objeto");
                Log.i("TAG", mTicket.getRelacionamentoEmpSvc().getServico().getSigla() + mTicket.getNumeroTicket());
                Intent intent = new Intent(AccessActivity.this, MainLayoutActivity.class);
                intent.putExtra("ticket", mTicket);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                Log.i("TAG", "Não pegou objeto");
            }
        });
    }
}
