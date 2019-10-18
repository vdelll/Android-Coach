package com.example.coach.modele;

import android.util.Log;

import com.example.coach.outils.MesOutils;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profil implements Serializable, Comparable {

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
    private Date dateMesure;

    // --- Constructeur ---
    public Profil(Date dateMesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure = dateMesure;
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

    public Date getDateMesure(){
        return dateMesure;
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

    /**
     * Conversion du profil au format JSON array
     * @return
     */
    public JSONArray convertToJSONArray(){
        List list = new ArrayList();
        list.add(MesOutils.convertDateToString(dateMesure));
        list.add(poids);
        list.add(taille);
        list.add(age);
        list.add(sexe);
        Log.d("laListe=", "*************************************"+ list.toString());

        return new JSONArray(list);
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     *
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     *
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o) {
        return dateMesure.compareTo(((Profil)o).getDateMesure());
    }
}
