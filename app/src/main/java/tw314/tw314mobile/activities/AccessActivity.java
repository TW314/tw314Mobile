package tw314.tw314mobile.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tw314.tw314mobile.R;
import tw314.tw314mobile.enums.ValidationEnum;
import tw314.tw314mobile.services.TicketService;

public class AccessActivity extends AppCompatActivity {

    // Objeto Ticket
    private TicketService ticketService;
    // Atributo do botao de acesso
    private Button mAccessButton;
    // Atributo da caixa de texto do codigo
    private EditText mAccessText;
    // Atributo que recebe o codigo do txtAccess
    private String mAccessCode;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

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
            if (ticketService.obtainTicketByAccessCode(mAccessCode) == ValidationEnum.OK) {
                startActivity(new Intent(AccessActivity.this, MainLayoutActivity.class));
            } else if (ticketService.obtainTicketByAccessCode(mAccessCode) == ValidationEnum.FAIL) {
                Toast.makeText(AccessActivity.this, "Não foi possível carregar os dados da senha.\n" +
                        "Tente novamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AccessActivity.this, "Senha indísponível para acesso.\n" +
                        "Verifique o código e tente novamente", Toast.LENGTH_SHORT).show();
            }
        }
    };


}
