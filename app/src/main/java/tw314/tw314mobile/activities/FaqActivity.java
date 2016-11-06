package tw314.tw314mobile.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tw314.tw314mobile.R;
import tw314.tw314mobile.faq.FaqItem;
import tw314.tw314mobile.faq.FaqList;

public class FaqActivity extends AppCompatActivity {

    // Atributo da lista de questoes
    private ListView listView;
    // Atributo do Adapter que popula lista;
    private MyAppAdapter myAppAdapter;
    // Atributo da Toolbar
    private Toolbar mToolbar;

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
        myAppAdapter = new MyAppAdapter(FaqList.getPostArrayList(), this);
        listView.setAdapter(myAppAdapter);
    }

    // Override para criar ActionBar com ActionOverflow
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla menu configurado
        getMenuInflater().inflate(R.menu.faq_action, menu);
        // Cria item de pesquisa do menu
        MenuItem searchItem = menu.findItem(R.id.faq_search);
        // Cria View de pesquisa
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener *** TODO: Pesquisar esse item
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        // Seta o Listener para alteracoes no texto da Caixa de Pesquisa
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // Override que controla resposta a entrada de texto
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            // Override que controla resposta a digitacao de texto
            @Override
            public boolean onQueryTextChange(String searchQuery) {
                myAppAdapter.filter(searchQuery.toString().trim());
                listView.invalidate();
                return true;
            }
        });

        // Controla expansao ou fechamento da caixa de pesquisa
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Aqui se faz controle das acoes quando colapsa/fecha o menu
                return true;  // Retorna true para fechar ActionView
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Aqui se faz controle das acoes quando expande/abre o menu
                return true;  // Retorna true para abrir ActionView
            }
        });
        return true;
    }

    // Override para controlar os itens selecionados da ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            // Se selecionado Overflow + Opcao "Perguntas frequentes"
            case R.id.faq:
                break;
            // Se selecionado Overflow + Opcao "Sobre o aplicativo"
            case R.id.about:
                startActivity(new Intent(FaqActivity.this, AboutActivity.class));
                break;
            // Se selecionado Botao de Pesquisa
            case R.id.faq_search:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Classe do Adapter da ListView
    public class MyAppAdapter extends BaseAdapter {

        // Classe que popula os itens de uma linha - Titulo e Subtitulo
        public class ViewHolder {
            TextView txtTitle, txtSubTitle;
        }
        // Lista a ser enviada para o ListView
        public List<FaqItem> parkingList;
        // Contexto passado para o adapter
        public Context context;
        ArrayList<FaqItem> arraylist;

        private MyAppAdapter(List<FaqItem> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<FaqItem>();
            arraylist.addAll(parkingList);
        }

        // Getter da parkingList
        @Override
        public int getCount() {
            return parkingList.size();
        }
        // Getter do Item da lista
        @Override
        public Object getItem(int position) {
            return position;
        }
        // Getter do Id do Item
        @Override
        public long getItemId(int position) {
            return position;
        }
        // Getter do View da linha
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // Linha
            View rowView = convertView;
            // Conteudo da linha
            ViewHolder viewHolder;

            if (rowView == null) {
                // Infla layout atual
                LayoutInflater inflater = getLayoutInflater();
                // Preenche a linha
                rowView = inflater.inflate(R.layout.faq_list_item, null);
                // Configura componentes da linha
                viewHolder = new ViewHolder();
                viewHolder.txtTitle = (TextView) rowView.findViewById(R.id.item_title);
                viewHolder.txtSubTitle = (TextView) rowView.findViewById(R.id.item_subtitle);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // Seta componentes da lista
            viewHolder.txtTitle.setText(parkingList.get(position).getPostTitle() + "");
            viewHolder.txtSubTitle.setText(parkingList.get(position).getPostSubTitle() + "");
            return rowView;
        }

        // Filtro de Pesquisa
        public void filter(String charText) {

            // Pega o texto digitado
            charText = charText.toLowerCase(Locale.getDefault());

            // Limpa a lista que preenche a ListView
            parkingList.clear();
            if (charText.length() == 0) {
                // Se nao digitou nada
                parkingList.addAll(arraylist);
            } else {
                // Texto preenchido
                // Percorre os itens da lista e valida os caracteres com o padrao local (Locale)
                for (FaqItem postDetail : arraylist) {
                    if (charText.length() != 0 && postDetail.getPostTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                        // Se o TITULO contem o texto digitado
                        parkingList.add(postDetail);
                    } else if (charText.length() != 0 && postDetail.getPostSubTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                        // Se o SUBTITULO contem o texto digitado
                        parkingList.add(postDetail);
                    }
                }
            }
            // Notifica aos observadores (do Android) que houveram alteracoes na lista e recarrega a tela
            notifyDataSetChanged();
        }
    }

}
