package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {

    Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        init();
    }

    /**
     * Initialisation
     */
    private void init(){
        creerRetourHisto();

        this.controle = Controle.getInstance(this);

        creerListe();
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

    /**
     * Crée la liste d'objets de type profil
     */
    private void creerListe(){
        ArrayList<Profil> lesProfils;
        lesProfils = controle.getLesProfils();
        Collections.sort(lesProfils, Collections.<Profil>reverseOrder());

        if (lesProfils != null){
            ListView listView = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(HistoActivity.this, lesProfils);
            listView.setAdapter(adapter);
        }
    }
}
