package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coach.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creerMenu();
    }

    /**
     * Crée les boutons
     */
    private void creerMenu(){
        ecouteMenu((ImageButton)findViewById(R.id.btnMonImg), CalculActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnMonHistorique), HistoActivity.class);
    }

    /**
     * Met une écoute sur la bonne imageet ouvre la bonne Activity sur le clic de l'image
     * @param imgBtn
     * @param classe
     */
    private void ecouteMenu(ImageButton imgBtn, final Class classe){
        imgBtn.setOnClickListener( new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
