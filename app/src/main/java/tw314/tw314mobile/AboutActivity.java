package tw314.tw314mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Pedro on 04/09/2016.
 */
public class AboutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
                startActivity(new Intent(AboutActivity.this, FaqActivity.class));
                break;

            case R.id.about:
                startActivity(new Intent(AboutActivity.this, AboutActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
