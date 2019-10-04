package com.example.coach.modele;

public class Profil {

    // --- Constantes ---
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // grosse si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    // --- Propriétés ---
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img; // Calcul de l'img
    private String message; // Message à retourner

    // --- Constructeur ---
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        calculIMG();
        resultIMG();
    }

    // --- Getters ---
    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    // --- Méthodes ---
    /**
     * Valorise la propriété IMG en lui affectant le calcul
     */
    private void calculIMG(){
        float tailleMetre = (float)taille/100; // Taille de CM à M
        img = (float)(1.2*((float)poids/Math.pow(tailleMetre, 2))) + (0.23f * (float)age) - (10.83f * (float)sexe) - 5.4f;
    }

    /**
     * Valorise la propriété message selon le calcul
     */
    private void resultIMG(){
        if (sexe == 0){
            if (img < minFemme){
                message = "trop faible";
            } else if (img < maxFemme){
                message = "normal";
            } else {
                message = "trop élevé";
            }
        } else if (sexe == 1){
            if (img < minHomme){
                message = "trop faible";
            } else if (img < maxHomme){
                message = "normal";
            } else {
                message = "trop élevé";
            }
        }
    }
}
