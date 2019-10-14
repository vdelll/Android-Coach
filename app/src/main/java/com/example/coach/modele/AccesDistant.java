package com.example.coach.modele;

import android.util.Log;

import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;

import org.json.JSONArray;

public class AccesDistant implements AsyncResponse {

    // adresse de la page php du serveur
    private static final String SERVERADDR = "http://192.168.1.46/coach/serveurcoach.php";

    public AccesDistant(){
        super();
    }

    /**
     * Gère le retour asynchrone du serveur .
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "**************" + output);
        String[] message = output.split("%");

        if(message.length > 1){
            if (message[0].equals("enreg")){
                Log.d(message[0], " **** " + message[1]);
            } else if (message[0].equals("dernier")) {
                Log.d(message[0], " **** " + message[1]);
            } else if (message[0].equals("Erreur !")){
                Log.d(message[0], " **** " + message[1]);
            }
        }
    }

    /**
     * Envoie une requete au serveur avec les données
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
