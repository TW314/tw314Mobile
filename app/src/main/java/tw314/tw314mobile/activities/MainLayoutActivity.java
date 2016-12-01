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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import tw314.tw314mobile.R;
import tw314.tw314mobile.enums.DialogTagEnum;
import tw314.tw314mobile.enums.ValidationEnum;
import tw314.tw314mobile.fragments.ExitDialogFragment;
import tw314.tw314mobile.fragments.GiveUpDialogFragment;
import tw314.tw314mobile.interfaces.AlertDialogInterface;
import tw314.tw314mobile.models.PeopleCounter;
import tw314.tw314mobile.models.Ticket;
import tw314.tw314mobile.services.TicketService;

public class MainLayoutActivity extends AppCompatActivity implements AlertDialogInterface {

    /**
     * Atributos do LayoutPrincipal
     *  ticketService: instancia do Service do Ticket para realizar conexao com o WebService
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

    private TicketService ticketService;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavView;
    private Intent navIntent;

    Toolbar mToolbar;

    String tag;

    String sTicket; // String do Ticket
    String sCount;
    TextView mTicketText, mEstablishment, mService, mTicketCount;

    ImageView mHourglass;

    // Declara receptor do Socket.io
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.0.105:3000/");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSocket.connect();
        mSocket.on("proximo", onNewCall);


        ticketService = new TicketService();

        /*
         * Seta os componentes com informacoes do Ticket
         * mTicketText = Senha
         * mEstablishment = Empresa
         * mService = Servico
         * mTicketCount = Quantidade de pessoas na frente
         */
        mTicketText = (TextView) findViewById(R.id.ticket);
        mEstablishment = (TextView) findViewById(R.id.establishment);
        mService = (TextView) findViewById(R.id.service);
        mTicketCount = (TextView) findViewById(R.id.ticket_count);

        sTicket = Ticket.getTicket().getRelacionamentoEmpSvc().getServico().getSigla() + Ticket.getTicket().getNumeroTicket();
        mTicketText.setText(sTicket);
        mEstablishment.setText(Ticket.getTicket().getRelacionamentoEmpSvc().getEmpresa().getRazaoSocial());
        mService.setText(Ticket.getTicket().getRelacionamentoEmpSvc().getServico().getNome());
        sCount = "" + PeopleCounter.getPeopleCounter().getPessoasNaFrente() + " pessoas";
        mTicketCount.setText(sCount);

        // Carregamento da Imagem para atualizacao do ticket
        mHourglass = (ImageView) findViewById(R.id.hourglass);

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
        mNavService.setText(Ticket.getTicket().getRelacionamentoEmpSvc().getServico().getNome());

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
                case R.id.ticket_list:
                    Toast.makeText(MainLayoutActivity.this, "Não implementado", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.give_queue_up:
                    showAlertDialog(DialogTagEnum.GIVE_UP_TAG);
                    break;
                case R.id.disable_notification:
                    Toast.makeText(MainLayoutActivity.this, "Não implementado", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.settings:
                    navIntent = new Intent(MainLayoutActivity.this, SettingsActivity.class);
                    break;
                case R.id.exit:
                    showAlertDialog(DialogTagEnum.EXIT_TAG);
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
     * Metodo que mostra os AlertDialogs dependendo da opcao selecionada no menu
     * showAlertDialog recebe uma tag para definir qual Alert mostrar, realizando a acao correspondente
     *  TAG e Resultado: GiveUpTicket -> Dialog de Desistencia do Atendimento
     *                   ExitApplication -> Dialog de saida da Aplicacao (mantendo notificacoes)
      */
    public void showAlertDialog(String tag) {
        if (tag.equalsIgnoreCase(DialogTagEnum.GIVE_UP_TAG)){
            DialogFragment dialogFragment = new GiveUpDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), tag);
        } else if (tag.equalsIgnoreCase(DialogTagEnum.EXIT_TAG)){
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
            if (ticketService.updateTicketByAccessCode(Ticket.getTicket().getCodigoAcesso()) == ValidationEnum.OK) {
                // Esvazia instancia
                Ticket.setTicket(null);
                finish();
            } else
                Toast.makeText(MainLayoutActivity.this, "Falha ao desistir da fila.", Toast.LENGTH_SHORT).show();


        } else if (dialogFragment.getTag().equalsIgnoreCase(DialogTagEnum.EXIT_TAG)){
            // TODO: Decidir como vai ser a saida do aplicativo
            finish();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {

    }

    /**
     * Metodo de resposta ao Socket.io
     *  Ativado quando o aplicativo recebe uma nova mensagem do Socket.io
     *   Valida se Empresa e Servico chamados foram o mesmo do Ticket e responde de acordo
     */
    private Emitter.Listener onNewCall = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            MainLayoutActivity.this.runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    String text = PeopleCounter.getPeopleCounter().getPessoasNaFrente() + " pessoas";
                    if (args[0].equals(Ticket.getTicket().getRelacionamentoEmpSvc().getEmpresa().getId())
                            && args[1].equals(Ticket.getTicket().getRelacionamentoEmpSvc().getServico().getId())) {
                        if (ticketService.obtainPeopleCountByAccessCode(Ticket.getTicket().getCodigoAcesso()) == ValidationEnum.OK){
                            mTicketCount.setText(text);
                        }
                    }
                }
            });
        }
    };

    /**
     * Metodo que faz o controle de cor da ampulheta
     *  Ao ser chamado, valida o numero de pessoas na frente e faz a mudanca da cor
     */
    private void hourglassSetter(int teste){
        // TODO: Definir como o metodo vai fazer a mudanca
    }

}
