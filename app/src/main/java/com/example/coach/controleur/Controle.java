package com.example.coach.controleur;

import android.content.Context;
import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.vue.CalculActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

public final class Controle {

    // --- Propriétés ---
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";
    private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context contexte;
    private ArrayList<Profil> lesProfils = new ArrayList<>();

    // --- Constructeur ---
    private Controle() {
        super();
    }

    /**
     * Crée une instance de controle ou renvoie l'instance existante
     *
     * @return
     */
    public static final Controle getInstance(Context contexte) {
        if (Controle.instance == null && contexte != null) {
            Controle.contexte = contexte;
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(contexte);
            accesDistant = new AccesDistant();
            // profil = accesLocal.recupDernier();
            accesDistant.envoi("tous", new JSONArray());
            // recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**70
     * Crée un profil
     *
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe   1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        Profil unProfil = new Profil(new Date(), poids, taille, age, sexe);
        accesLocal.ajout(unProfil);
        lesProfils.add(unProfil);
        accesDistant.envoi("enreg", unProfil.convertToJSONArray());
        // Serializer.serialize(nomFic, profil, contexte);
    }

    /**
     * Retourne l'indice de masse graisseuse récupéré dans le profil
     *
     * @return
     */
    public float getImg() {
        if (lesProfils.size() > 0) {
            return lesProfils.get(lesProfils.size()-1).getImg();
        } else {
            return 0;
        }
    }

    /**
     * Retourne le message récupéré dans le profil
     *
     * @return
     */
    public String getMessage() {
        if (lesProfils.size() > 0) {
            return lesProfils.get(lesProfils.size()-1).getMessage();
        } else {
            return "";
        }
    }

    /**
     * Recupere le poids du profil
     * @return le poids
     */
    public Integer getPoids() {
        if (profil == null) {
            return null;
        } else {
            return profil.getPoids();
        }
    }

    /**
     * Recupère la taille du profil
     * @return la taille
     */
    public Integer getTaille() {
        if (profil == null) {
            return null;
        } else {
            return profil.getTaille();
        }
    }

    /**
     * Recupere l'age du profil
     * @return l'age
     */
    public Integer getAge() {
        if (profil == null) {
            return null;
        } else {
            return profil.getAge();
        }
    }

    /**
     * Recupere le sexe du profil
     * @return le sexe
     */
    public Integer getSexe() {
        if (profil == null) {
            return null;
        } else {
            return profil.getSexe();
        }
    }

    public void setProfil(Profil profil){
        Controle.profil = profil;
        // ((CalculActivity)contexte).recupProfil();
    }

    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }

    /**
     * Permet la suppression d'un profil de la base de données
     * @param profil
     */
    public void delProfil(Profil profil){
        accesDistant.envoi("del", profil.convertToJSONArray());
        lesProfils.remove(profil);
    }

/**
     * Récupère les objets sérializés
     *
     * @param contexte
     */
    /*
    private static void recupSerialize(Context contexte) {
        profil = (Profil) (Serializer.deSerialize(nomFic, contexte));
    }
    */
}
