package tw314.tw314mobile.layout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import tw314.tw314mobile.R;

public class MainLayoutActivity extends AppCompatActivity {

    // Atributo do NavDrawerLayout
    private DrawerLayout mDrawerLayout;
    // Atributo que coordena abrir e fechar do NavDrawer
    private ActionBarDrawerToggle mDrawerToggle;
    // Atributo que responde acoes do menu
    private NavigationView mNavView;
    // Atributo que controla chamada das Activities no NavDrawer
    private Intent navIntent;
    // Atributo da Toolbar
    Toolbar mToolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Seta a ActionBar como sendo o layout action_bar.xml
        mToolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(mToolbar);

        // Inicializa configuracoes do aplicativo
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // Inicializa compontenes do NavDrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavView = (NavigationView) findViewById(R.id.navigation_menu);

        // Inicializa resposta aos cliques dos itens do NavDrawer
        mNavView.setNavigationItemSelectedListener(mNavDrawerClick);

        // Inicializa Toggle (abertura e fechamento do Drawer)
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close){
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                // getSupportActionBar().setTitle(title);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
                // getActionBar().setTitle(R.string.app_name);
                supportInvalidateOptionsMenu();
            }
        };

        // Seta resposta do DrawerLayout ao Toggle (Abrir e Fechar)
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // Seta icone de lista e efeito de transicao de icones para true
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    // Override para criar ActionBar com ActionOverflow
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_action, menu);
        return true;
    }

    // Override para controlar os itens selecionados da ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Se selecionado Overflow + Opcao "Perguntas frequentes"
        if (item.getItemId() == R.id.faq){
            // Chama Activity FAQ
            startActivity(new Intent(MainLayoutActivity.this, FaqActivity.class));
        }
        // Se selecionado Overflow + Opcao "Sobre o aplicativo"
        else if (item.getItemId() == R.id.about){
            // Chama Activity Sobre
            startActivity(new Intent(MainLayoutActivity.this, AboutActivity.class));
        }
        // Se selecionado NavDrawer
        else if (mDrawerToggle.onOptionsItemSelected(item)){
            // Abre ou Fecha NavDrawer
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Metodo/Propriedade de resposta aos cliques do NavDrawer
    private NavigationView.OnNavigationItemSelectedListener mNavDrawerClick = new NavigationView.OnNavigationItemSelectedListener(){
        // Opcoes do NavDrawer
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch(item.getItemId()){
                // Adicionar Senha
                case R.id.add_ticket:
                    // TODO: Adicionar Intent para AddTicketActivity
                    // navIntent = new Intent(MainLayoutActivity.this, AddTicketActivity);
                    Toast.makeText(MainLayoutActivity.this, "Adicionar Senha selecionado", Toast.LENGTH_SHORT).show();
                    break;
                // Ver Lista de Senhas
                case R.id.ticket_list:
                    // TODO: Adicionar Intent para TicketListActivity
                    // navIntent = new Intent(MainLayoutActivity.this, TicketListActivity);
                    Toast.makeText(MainLayoutActivity.this, "Visualizar Senhas selecionado", Toast.LENGTH_SHORT).show();
                    break;
                // Desistir da Fila
                case R.id.give_queue_up:
                    // TODO: Adicionar acoes para desistir da fila
                    Toast.makeText(MainLayoutActivity.this, "Desistir da Fila selecionado", Toast.LENGTH_SHORT).show();
                    break;
                // Desativar Notificacoes
                case R.id.disable_notification:
                    // TODO: Adicionar acoes para desativar notificacoes
                    Toast.makeText(MainLayoutActivity.this, "Desativar Notificacoes selecionado", Toast.LENGTH_SHORT).show();
                    break;
                // Configuracoes
                case R.id.settings:
                    // TODO: Adicionar Intent para SettingsActivity
                    navIntent = new Intent(MainLayoutActivity.this, SettingsActivity.class);
                    startActivity(navIntent);
                    break;
                case R.id.exit:
                    // TODO: Adiconar acao para sair do app
                    Toast.makeText(MainLayoutActivity.this, "Sair selecionado", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

            // TODO: Adicionar chamada para Activity definida no Switch
            // startActivity(navIntent);
            return false;
        }
    };

    // Overrides padrao do NavDrawer
    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        // Sincroniza o estado do Toggle caso ocorra um onRestoreInstanceState
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        // Passa todas as mudancas de configuracao para o Drawer
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
