package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coach.R;

public class HistoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        creerRetourHisto();
    }

    /**
     * Crée les boutons
     */
    private void creerRetourHisto(){
        ecouteRetourHisto((ImageButton)findViewById(R.id.btnRetourDeHisto), MainActivity.class);
    }

    /**
     * Met une écoute sur la bonne imageet ouvre la bonne Activity sur le clic de l'image
     * @param imgBtn
     * @param classe
     */
    private void ecouteRetourHisto(ImageButton imgBtn, final Class classe){
        imgBtn.setOnClickListener( new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
