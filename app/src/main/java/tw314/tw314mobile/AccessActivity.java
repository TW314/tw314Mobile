package tw314.tw314mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccessActivity extends AppCompatActivity {

    // Atributo do botao de acesso
    private Button btnAccess;
    // Atributo da caixa de texto do codigo
    private EditText txtAccess;

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
            startActivity(new Intent(AccessActivity.this, MainLayoutActivity.class));
        }
    };
}
