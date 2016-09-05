package tw314.tw314mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Pedro on 17/08/2016.
 */
public class MainLayoutActivity extends AppCompatActivity {

    Button about;
    Button faq;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        about = (Button) findViewById(R.id.about);
        faq = (Button) findViewById(R.id.faq);

        about.setOnClickListener(transition);
        faq.setOnClickListener(transition);
    }

    private View.OnClickListener transition = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.about:
                    startActivity(new Intent(MainLayoutActivity.this, AboutActivity.class));
                    break;
                case R.id.faq:
                    startActivity(new Intent(MainLayoutActivity.this, FaqActivity.class));
                    break;
            }
        }
    };
}
