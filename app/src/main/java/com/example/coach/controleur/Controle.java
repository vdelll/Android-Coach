package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {

    // --- Propriétés ---
    private static Controle instance = null;
    private static Profil profil;

    // --- Constructeur ---
    private Controle() {
        super();
    }

    /**
     * Crée une instance de controle ou renvoie l'instance existante
     * @return
     */
    public static final Controle getInstance() {
        if (instance == null) {
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Crée un profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(poids, taille, age, sexe);
    }

    /**
     * Retourne l'indice de masse graisseuse récupéré dans le profil
     * @return
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * Retourne le message récupéré dans le profil
     * @return
     */
    public String getMessage(){
        return profil.getMessage();
    }
}
