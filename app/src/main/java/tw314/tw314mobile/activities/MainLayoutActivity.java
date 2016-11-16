package tw314.tw314mobile.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import tw314.tw314mobile.R;
import tw314.tw314mobile.fragments.ExitDialogFragment;
import tw314.tw314mobile.fragments.GiveUpDialogFragment;
import tw314.tw314mobile.interfaces.AlertDialogInterface;
import tw314.tw314mobile.models.Ticket;

public class MainLayoutActivity extends AppCompatActivity implements AlertDialogInterface {

    // Objeto que recebe Ticket vindo
    Ticket mTicket;
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
    // Atributo de TAG dos AlertDialogs
    String tag;
    // Componentes que recebem texto
    String sTicket; // String do Ticket
    TextView mTicketText, mEstablishment, mService; // Layout Principal
    TextView mNavTicket, mNavService; // NavigationView

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pega instancia do Ticket
        mTicket = Ticket.getInstance();

        // Seta os componentes
        // Layout Principal
        mTicketText = (TextView) findViewById(R.id.ticket);
        mEstablishment = (TextView) findViewById(R.id.establishment);
        mService = (TextView) findViewById(R.id.service);

        // NavigationView
        mNavTicket = (TextView) findViewById(R.id.nav_ticket);
        mNavService = (TextView) findViewById(R.id.nav_service);

        // Seta texto dos componentes
        // Senha
        sTicket = mTicket.getRelacionamentoEmpSvc().getServico().getSigla() + mTicket.getNumeroTicket();
        mTicketText.setText(sTicket);
        mNavTicket.setText(sTicket);
        // Empresa
        mEstablishment.setText(mTicket.getRelacionamentoEmpSvc().getEmpresa().getRazaoSocial());
        // Servico
        mService.setText(mTicket.getRelacionamentoEmpSvc().getServico().getNome());
        mNavService.setText(mTicket.getRelacionamentoEmpSvc().getServico().getNome());

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
                    // break;
                // Ver Lista de Senhas
                case R.id.ticket_list:
                    // TODO: Adicionar Intent para TicketListActivity
                    // navIntent = new Intent(MainLayoutActivity.this, TicketListActivity);
                    Toast.makeText(MainLayoutActivity.this, "Não disponível", Toast.LENGTH_SHORT).show();
                    break;
                // Desistir da Fila
                case R.id.give_queue_up:
                    tag = "GiveUp";
                    showAlertDialog(tag);
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
                    break;
                case R.id.exit:
                    tag = "Exit";
                    showAlertDialog(tag);
                    break;
                default:
                    break;
            }

            // Compara se Intent nao esta vazia para nao estourar excecao
            if (navIntent != null)
                startActivity(navIntent);

            // Return aleatorio para conclusao do metodo
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

    // Metodo que mostra os AlertDialogs dependendo da opcao selecionada no menu
    public void showAlertDialog(String tag) {
        // Compara a string TAG para chamar o Dialgo especifico
        // GiveUp -> Dialog sobre a Desistencia da fila
        // Exit -> Dialog sobre a Saida do aplicativo
        if (tag.equalsIgnoreCase("GiveUp")){
            // AlertDialog de Desistencia
            // Instancia o objeto Dialog e chama passando TAG para comparacao
            DialogFragment dialogFragment = new GiveUpDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), tag);
        } else if (tag.equalsIgnoreCase("Exit")){
            // AlertDialog de Saida
            // Instancia o objeto Dialog e chama passando TAG para comparacao
            DialogFragment dialogFragment = new ExitDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), tag);
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        if (dialogFragment.getTag().equalsIgnoreCase("GiveUp")){
            // Atualiza status para "Cancelado"
            // TODO: Adicionar acoes para desistir da fila

            // Esvazia instancia
            Ticket.setInstance(null);
            // Chama tela de acesso
            navIntent = new Intent(MainLayoutActivity.this, AccessActivity.class);
            startActivity(navIntent);
        } else if (dialogFragment.getTag().equalsIgnoreCase("Exit")){
            // TODO: Decidir como vai ser a saida do aplicativo
            navIntent = new Intent(MainLayoutActivity.this, AccessActivity.class);
            startActivity(navIntent);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {

    }

}
