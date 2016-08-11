package com.example.voids.tw314;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText entradaCodigo = (EditText) findViewById(R.id.entradaCod);

        Button btnInserirCodigo = (Button) findViewById(R.id.btnInserirCodigo);

        btnInserirCodigo.setOnClickListener(new Button.OnClickListener()
            {
                public void onClick(View v)
                {

                }
            }
        );
        
        //Onde parei no curso de Android
        //https://www.youtube.com/watch?v=Pm2Im-_Hu5A

        //Curso de modelagem de telas para Android
        //https://www.youtube.com/watch?v=eEU3t0x_pxQ&list=PLWz5rJ2EKKc8j2B95zGMb8muZvrIy-wcF
    }
}
