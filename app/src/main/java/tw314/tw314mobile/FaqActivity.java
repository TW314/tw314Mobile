package tw314.tw314mobile;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Pedro on 04/09/2016.
 */
public class FaqActivity extends ListActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listQuestions()));
    }

    private List<String> listQuestions() {
        return Arrays.asList("Como adicionar uma nova senha?", "O que significam as cores da ampulheta?",
                "Como configurar as notificações do aplicativo?", "Como saber quando serei chamado?",
                "Como funciona o aplicativo?");
    }
}
