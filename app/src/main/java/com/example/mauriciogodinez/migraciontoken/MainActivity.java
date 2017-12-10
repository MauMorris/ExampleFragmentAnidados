package com.example.mauriciogodinez.migraciontoken;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.mauriciogodinez.migraciontoken.ui.TokenDialogFragment;

public class MainActivity extends AppCompatActivity {
    AppCompatButton mToken;

    DialogFragment dFragmentToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToken = findViewById(R.id.boton_actividad);

        mToken.setOnClickListener(fragmentTokenListener);
    }

    View.OnClickListener fragmentTokenListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dFragmentToken = new TokenDialogFragment();

            dFragmentToken.setCancelable(false);
            dFragmentToken.show(getSupportFragmentManager(), "TokenDialogFragment");
        }
    };
}