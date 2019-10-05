package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import java.util.Date;

public final class Controle {

    // --- Propriétés ---
    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveprofil";

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
        if (instance == null) {
            Controle.instance = new Controle();
            // recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     * Crée un profil
     *
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe   1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context contexte) {
        profil = new Profil(poids, taille, age, sexe, new Date());
        // Serializer.serialize(nomFic, profil, contexte);
    }

    /**
     * Retourne l'indice de masse graisseuse récupéré dans le profil
     *
     * @return
     */
    public float getImg() {
        return profil.getImg();
    }

    /**
     * Retourne le message récupéré dans le profil
     *
     * @return
     */
    public String getMessage() {
        return profil.getMessage();
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

    /**
     * Récupère les objets sérializés
     *
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        profil = (Profil) (Serializer.deSerialize(nomFic, contexte));
    }
}
