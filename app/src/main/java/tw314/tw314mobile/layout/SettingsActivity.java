package tw314.tw314mobile.layout;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;

import tw314.tw314mobile.R;

/**
 * Created by Pedro on 05/10/2016.
 */

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Inicializa configuracoes do aplicativo
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // Mostra o Fragment como conteudo principal da tela
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
