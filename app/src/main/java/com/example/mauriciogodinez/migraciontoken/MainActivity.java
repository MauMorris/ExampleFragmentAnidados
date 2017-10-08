package com.example.mauriciogodinez.migraciontoken;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton boton = (AppCompatButton) findViewById(R.id.boton_actividad);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanza_alert();
            }
        });
    }

    private void lanza_alert() {
        DialogFragment dialog = new TokenDialogFragment();
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "TokenDialogFragment");
    }
}