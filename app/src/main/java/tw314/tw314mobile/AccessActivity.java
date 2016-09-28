package tw314.tw314mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccessActivity extends AppCompatActivity {

    private Button btnAccess;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        btnAccess = (Button) findViewById(R.id.btnAccess);
        btnAccess.setOnClickListener(btnAccessClick);
    }

    private View.OnClickListener btnAccessClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(AccessActivity.this, MainLayoutActivity.class));
        }
    };
}
