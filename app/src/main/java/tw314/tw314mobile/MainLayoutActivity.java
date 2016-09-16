package tw314.tw314mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Pedro on 17/08/2016.
 */
public class MainLayoutActivity extends ActionBarActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.faq:
                startActivity(new Intent(MainLayoutActivity.this, FaqActivity.class));
                break;

            case R.id.about:
                startActivity(new Intent(MainLayoutActivity.this, AboutActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
