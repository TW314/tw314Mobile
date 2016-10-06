package tw314.tw314mobile.layout;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import tw314.tw314mobile.R;

/**
 * Created by Pedro on 04/09/2016.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Inicializa configuracoes do aplicativo
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

}
