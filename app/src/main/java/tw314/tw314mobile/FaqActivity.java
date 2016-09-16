package tw314.tw314mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Pedro on 04/09/2016.
 */
public class FaqActivity extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private List<String> listQuestions = Arrays.asList("Como adicionar uma nova senha?", "O que significam as cores da ampulheta?",
            "Como configurar as notificações do aplicativo?", "Como saber quando serei chamado?",
            "Como funciona o aplicativo?");

    ListView listView;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listQuestions));
    }

    private List<String> listQuestions() {
        return Arrays.asList("Como adicionar uma nova senha?", "O que significam as cores da ampulheta?",
                "Como configurar as notificações do aplicativo?", "Como saber quando serei chamado?",
                "Como funciona o aplicativo?");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.faq_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.faq:
                startActivity(new Intent(FaqActivity.this, FaqActivity.class));
                break;

            case R.id.about:
                startActivity(new Intent(FaqActivity.this, AboutActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query = query.toLowerCase();
        TextView view = (TextView) findViewById(R.id.resultView);

        for (String question: listQuestions()){
            if (question.contains(query)){
                String result = getString(R.string.results_found, query);
                view.setText(result);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
