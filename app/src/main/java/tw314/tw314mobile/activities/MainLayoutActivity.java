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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tw314.tw314mobile.R;
import tw314.tw314mobile.connectionHandler.ConnectionHandler;
import tw314.tw314mobile.enums.DialogTagEnum;
import tw314.tw314mobile.enums.StatusTicketEnum;
import tw314.tw314mobile.fragments.ExitDialogFragment;
import tw314.tw314mobile.fragments.GiveUpDialogFragment;
import tw314.tw314mobile.interfaces.AlertDialogInterface;
import tw314.tw314mobile.models.PeopleCounterReceiver;
import tw314.tw314mobile.models.Ticket;
import tw314.tw314mobile.services.TicketService;

public class MainLayoutActivity extends AppCompatActivity implements AlertDialogInterface {

    /**
     * Atributos do LayoutPrincipal
     *  mTicket: recebe instancia do Ticket
     *
     *  mDrawerLayout: layout do menu lateral
     *  mDrawerToggle: controla abrir e fechar do menu lateral
     *  mNavView: responde acoes do menu lateral
     *  navIntent: recebe a chamada das activities em acoes do menu lateral
     *
     *  mToolbar: toolbar da aplicacao
     *
     *  tag: Atributo de tag dos AlertDialogs das opcoes de Desistir e Sair
     *
     *  sTicket: String que cria o ticket completo na tela
     *  mTicketText, mEstablishment, mService: recebem dados do Ticket para mostrar na tela
     */

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavView;
    private Intent navIntent;

    Toolbar mToolbar;

    String tag;

    String sTicket; // String do Ticket
    TextView mTicketText, mEstablishment, mService;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Seta os componentes com informacoes do Ticket
         * mTicketText = Senha
         * mEstablishment = Empresa
         * mService = Servico
         */
        mTicketText = (TextView) findViewById(R.id.ticket);
        mEstablishment = (TextView) findViewById(R.id.establishment);
        mService = (TextView) findViewById(R.id.service);

        sTicket = Ticket.getInstance().getRelacionamentoEmpSvc().getServico().getSigla() + Ticket.getInstance().getNumeroTicket();
        mTicketText.setText(sTicket);
        mEstablishment.setText(Ticket.getInstance().getRelacionamentoEmpSvc().getEmpresa().getRazaoSocial());
        mService.setText(Ticket.getInstance().getRelacionamentoEmpSvc().getServico().getNome());

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

    /**
     * Metodo que gerencia a ActionBar
     * onCreateOprionsMenu cria a ActionBar com os botoes setados na menu.main_action
     *
     * onOptionsItemSelected controla o clique das opcoes do menu
      */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView mNavTicket, mNavService;
        // Seta os valores na NavigationView para display
        mNavTicket = (TextView) findViewById(R.id.nav_ticket);
        mNavService = (TextView) findViewById(R.id.nav_service);
        mNavTicket.setText(sTicket);
        mNavService.setText(Ticket.getInstance().getRelacionamentoEmpSvc().getServico().getNome());

        /**
         * Validacao de controle da Opcao Selecionada
         * FAQ: Se selecionado Overflow + Opcao "Perguntas frequentes" -> Chama a tela de FAQ
         * Sobre: Se selecionado Overflow + Opcao "Sobre o aplicativo" -> Chama a tela Sobre
         * mDrawerToggle: Se selecionado NavDrawer -> Abre Menu Lateral e chama método de gerencia de cliques
         */
        if (item.getItemId() == R.id.faq)
            startActivity(new Intent(MainLayoutActivity.this, FaqActivity.class));

        else if (item.getItemId() == R.id.about)
            startActivity(new Intent(MainLayoutActivity.this, AboutActivity.class));

        else if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo/Propriedade de resposta aos cliques do NavDrawer
     * Define qual foi a opcao selecionada pelo usuario e realiza resposta
     *  Adicionar Ticket: Nao implementado
     *  Lista de Tickets: Nao implementado
     *  Desistir da Fila: Atualiza status do Ticket e sai da aplicacao
     *  Desativar Notificacoes: Desativa todas as notificacoes da aplicacao em relacao a fila, exceto a chamada
     *  Configuracoes: Chama tela de configuracao da aplicacao
     *  Sair: Sai da aplicacao sem desativar o Ticket ou as notificacoes
      */
    private NavigationView.OnNavigationItemSelectedListener mNavDrawerClick = new NavigationView.OnNavigationItemSelectedListener(){
        // Opcoes do NavDrawer
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch(item.getItemId()){
                case R.id.add_ticket:
                    // TODO: Adicionar Intent para AddTicketActivity
                case R.id.ticket_list:
                    // TODO: Adicionar Intent para TicketListActivity
                    Toast.makeText(MainLayoutActivity.this, "Não disponível", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.give_queue_up:
                    tag = DialogTagEnum.GIVE_UP_TAG;
                    showAlertDialog(tag);
                    break;
                case R.id.disable_notification:
                    // TODO: Adicionar acoes para desativar notificacoes
                    Toast.makeText(MainLayoutActivity.this, "Desativar Notificacoes selecionado", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.settings:
                    navIntent = new Intent(MainLayoutActivity.this, SettingsActivity.class);
                    break;
                case R.id.exit:
                    tag = DialogTagEnum.EXIT_TAG;
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

    /**
     * Overrides padrao do NavDrawer
      */
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

    /**
     * Metodo que recebe a contagem de pessoas na frente do Ticket do usuario
     * Chama metodo que faz acesso ao Servico
     */
    private void getCountOfPeople(String accessCode){
        getCountOfPeopleByAccessCode(accessCode);
    }

    /**
     * Metodo que mostra os AlertDialogs dependendo da opcao selecionada no menu
     * showAlertDialog recebe uma tag para definir qual Alert mostrar, realizando a acao correspondente
     *  TAG e Resultado: GiveUpTicket -> Dialog de Desistencia do Atendimento
     *                   ExitApplication -> Dialog de saida da Aplicacao (mantendo notificacoes)
      */
    public void showAlertDialog(String tag) {
        if (tag.equalsIgnoreCase(DialogTagEnum.GIVE_UP_TAG)){

            // Instancia o objeto Dialog e chama passando TAG para comparacao
            DialogFragment dialogFragment = new GiveUpDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), tag);
        } else if (tag.equalsIgnoreCase(DialogTagEnum.EXIT_TAG)){

            // Instancia o objeto Dialog e chama passando TAG para comparacao
            DialogFragment dialogFragment = new ExitDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), tag);
        }
    }

    /**
     * Metodos que identificam o clique AlertDialog
     * onDialogPositiveClick pega o clique positivo e
     *  realiza acoes correspondentes dependendo da tag recebida:
     *      GiveUp -> chama metodo que acessa o Servico para desistir da fila
     *      Exit -> sai da aplicacao
     *
     * onDialogNegativeClick somente fecha o AlertDialog
      */
    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        if (dialogFragment.getTag().equalsIgnoreCase(DialogTagEnum.GIVE_UP_TAG)){
            updateTicketByAccessCode(Ticket.getInstance().getCodigoAcesso());
        } else if (dialogFragment.getTag().equalsIgnoreCase(DialogTagEnum.EXIT_TAG)){
            // TODO: Decidir como vai ser a saida do aplicativo
            navIntent = new Intent(MainLayoutActivity.this, AccessActivity.class);
            startActivity(navIntent);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {

    }

    /**
     * Metodos de acesso ao Servido do Ticket
     * updateTicketByAccessCode recebe o codigo de acesso, atualiza o status do Ticket
     *  e chama o Servico, cancelando a espera do atendimento
     *
     * getCountOfPeopleBeforeMe recebe o codigo de acesso, chama o Servico e pega
     *  a contagem de Tickets na fila antes do Ticket autenticado no aplicativo
      */
    private void updateTicketByAccessCode(String accessCode){
        // Chama o servico e faz atualizacao no WebService
        TicketService ticketService = ConnectionHandler.obtainConnection().create(TicketService.class);
        Ticket.getInstance().setStatusTicketId(StatusTicketEnum.CANCELADO);

        Call<ResponseBody> call = ticketService.updateTicket(accessCode, Ticket.getInstance());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Esvazia instancia
                Ticket.setInstance(null);
                // Finaliza a aplicacao
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainLayoutActivity.this, "Falha ao atualizar Ticket. " +
                        "Por favor, aguarde um instante e então tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCountOfPeopleByAccessCode(String accessCode) {
        // Chama o servico e faz atualizacao no WebService
        TicketService ticketService = ConnectionHandler.obtainConnection().create(TicketService.class);
        Call<PeopleCounterReceiver> call = ticketService.getCountOfPeopleBeforeMe(accessCode);

        call.enqueue(new Callback<PeopleCounterReceiver>(){
            @Override
            public void onResponse(Call<PeopleCounterReceiver> call, Response<PeopleCounterReceiver> response) {
                PeopleCounterReceiver.setPeopleCounterReceiver(response.body());

            }

            @Override
            public void onFailure(Call<PeopleCounterReceiver> call, Throwable t) {

            }
        });
    }

}
