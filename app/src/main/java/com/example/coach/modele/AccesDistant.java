package com.example.coach.modele;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AccesDistant implements AsyncResponse {

    // adresse de la page php du serveur
    private static final String SERVERADDR = "http://192.168.1.27/coach/serveurcoach.php";
    private Controle controle;

    public AccesDistant(){

        this.controle = Controle.getInstance(null);
    }

    /**
     * Gère le retour asynchrone du serveur .
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "***************************" + output);
        String[] message = output.split("%");

        if(message.length > 1){
            if (message[0].equals("enreg")){
                Log.d(message[0], " *************************** " + message[1]);
            } else if (message[0].equals("dernier")) {
                Log.d(message[0], " *************************** " + message[1]);
                try {
                    JSONObject info = new JSONObject(message[1]);
                    Date datemesure = MesOutils.convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd hh:mm:ss");
                    Integer poids = info.getInt("poids");
                    Integer taille = info.getInt("taille");
                    Integer age = info.getInt("age");
                    Integer sexe = info.getInt("sexe");
                    controle.setProfil(new Profil(datemesure, poids, taille, age, sexe));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (message[0].equals("Erreur !")){
                Log.d(message[0], " *************************** " + message[1]);
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
        Log.d("donneJSON", "************************************"+lesDonneesJSON.toString());
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
