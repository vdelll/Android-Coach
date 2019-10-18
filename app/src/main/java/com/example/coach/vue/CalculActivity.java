package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class CalculActivity extends AppCompatActivity {

    // --- Propriétés ---
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalcul;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    /**
     * Initialise les variables de la classe avec les variables graphiques
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalcul = (Button) findViewById(R.id.btnCalcul);

        controle = Controle.getInstance(this);

        ecouteCalcul();
        creerRetour();

        // recupProfil();
    }

    /**
     * Ecouteur sur le bouton Calculer
     * Récupère les informations saisies par l'utilisateur
     */
    private void ecouteCalcul() {
        btnCalcul.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;

                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {
                }

                Integer sexe = 0;

                if (rdHomme.isChecked()) {
                    sexe = 1;
                }

                if (poids == 0) {
                    Toast.makeText(CalculActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                } else if (taille == 0) {
                    Toast.makeText(CalculActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                } else if (age == 0) {
                    Toast.makeText(CalculActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Affichage du smiley en fonction de l'img
     * Affichage du message correspondant au résultat du calcul et au message associé
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe) {
        controle.creerProfil(poids, taille, age, sexe);
        float img = controle.getImg();
        String message = controle.getMessage();

        if (message.contentEquals("trop faible")) {
            imgSmiley.setImageResource(R.drawable.maigre);
            lblIMG.setTextColor(Color.RED);
        } else if (message.contentEquals("normal")) {
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        } else {
            imgSmiley.setImageResource(R.drawable.graisse);
            lblIMG.setTextColor(Color.RED);
        }

        lblIMG.setText(String.format("%.01f", img) + " : IMG " + message);
    }

    /**
     * Récupère le profil sérializé pour remettre les informations dans l'application
     */
    public void recupProfil() {
        if (controle.getTaille() != null) {
            txtTaille.setText(controle.getTaille().toString());
        }
        if (controle.getAge() != null) {
            txtAge.setText(controle.getAge().toString());
        }
        if (controle.getPoids() != null) {
            txtPoids.setText(controle.getPoids().toString());
        }
        if (controle.getSexe() != null) {
            if (controle.getSexe() == 0) {
                rdFemme.setChecked(true);
            } else {
                rdHomme.setChecked(true);
            }
        }
        btnCalcul.performClick();
    }

    /**
     * Crée les boutons
     */
    private void creerRetour(){
        ecouteRetour((ImageButton)findViewById(R.id.btnRetourDeCalcul), MainActivity.class);
    }

    /**
     * Met une écoute sur la bonne imageet ouvre la bonne Activity sur le clic de l'image
     * @param imgBtn
     * @param classe
     */
    private void ecouteRetour(ImageButton imgBtn, final Class classe){
        imgBtn.setOnClickListener( new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CalculActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
