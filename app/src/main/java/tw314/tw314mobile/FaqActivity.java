package tw314.tw314mobile;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.Arrays;
import java.util.List;

public class FaqActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    // Atributo da lista de questoes
    private ListView listView;
    // Atributo da Toolbar
    Toolbar mToolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        // Seta a ActionBar como sendo o layout action_bar.xml
        mToolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(mToolbar);

        // Seta icone de lista e efeito de transicao de icones para true
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // Inicializa configuracoes do aplicativo
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // Instancia ListView
        listView = (ListView) findViewById(R.id.listView);

        // Cria Adapter para popular ListView
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listQuestions()));
    }

    // Lista de Questoes do FAQ
    // TODO: Passar a List para outra class em outro package
    private List<String> listQuestions() {
        return Arrays.asList("Como adicionar uma nova senha?", "O que significam as cores da ampulheta?",
                "Como configurar as notificações do aplicativo?", "Como saber quando serei chamado?",
                "Como funciona o aplicativo?");
    }

    // Override para criar ActionBar com ActionOverflow
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.faq_action, menu);
        return true;
    }

    // Override para controlar os itens selecionados da ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            // Se selecionado Overflow + Opcao "Perguntas frequentes"
            case R.id.faq:
                break;
            // Se selecionado Overflow + Opcao "Sobre o aplicativo"
            case R.id.about:
                startActivity(new Intent(FaqActivity.this, AboutActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // Override que controla resposta a entrada de texto
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    // Override que controla resposta a digitacao de texto
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
