package tw314.tw314mobile.activities;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import tw314.tw314mobile.R;

/**
 * Created by Pedro on 05/10/2016.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Carrega as Preferencias de um recurso XML
        addPreferencesFromResource(R.xml.preferences);
    }
}
