package tw314.tw314mobile;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainLayoutActivity extends ActionBarActivity {

    //Atributo do NavDrawerLayout
    private DrawerLayout mDrawerLayout;
    //Lista de itens do menu
    private ListView mDrawerList;
    //Atributo que coordena abrir e fechar do NavDrawer
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa compontenes do NavDrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*
        mDrawerList = (ListView) findViewById(R.id.nav_drawer);

        // Inicializa adapter do menu passando Lista Estatica
        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, DrawerList.getDataList());
        mDrawerList.setAdapter(adapter);

        // Seta ItemClickListener para identificar cliques na lista
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        */

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

        // Seta resposta do DrawerLayout ao Toggle
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        // Seta icone de lista e efeito de transicao de icones para true
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    // Metodo que controla ações do NavDrawer
    public void SelectItem(int position){

        // TODO: Usar Intent para instanciar no Switch qual Activity deve ser chamada, fazer chamada no fim do método
        // Intent intent = null;

        switch(position){
            // Ticket
            case 2:
                // TODO: Adicionar chamada da Activity de Adicionar Senha
                Toast.makeText(MainLayoutActivity.this, "Adicionar Senha selecionado", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                // TODO: Adicionar chamada da Activity de Visualizar Lista de Senhas
                Toast.makeText(MainLayoutActivity.this, "Visualizar Lista de Senhas selecionado", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                // TODO: Adicionar função de Desistir da Fila
                Toast.makeText(MainLayoutActivity.this, "Desistir da Fila selecionado", Toast.LENGTH_SHORT).show();
                break;

            // Configuracoes
            case 6:
                // TODO: Adicionar configuração de Desativar Notificações
                Toast.makeText(MainLayoutActivity.this, "Desativar Notificações selecionado", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                // TODO: Adicionar chamada da Activity de Configurar Notificações
                Toast.makeText(MainLayoutActivity.this, "Configurar Notificações selecionado", Toast.LENGTH_SHORT).show();
                break;

            // Outros
            case 9:
                // TODO: Adicionar funçao de fechar APP
                Toast.makeText(MainLayoutActivity.this, "Sair selecionado", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        mDrawerList.setItemChecked(position, false);
        mDrawerLayout.closeDrawer(mDrawerList);
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
