package com.example.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    // --- Propriétés ---
    private String nomBase = "bdCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd; // accès à la base locale

    // --- Constructeur ---
    public AccesLocal(Context contexte) {
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, versionBase);
    }

    /**
     * Ajoute un profil dans la base de données SQLite
     * @param profil
     */
    public void ajout(Profil profil) {
        bd = accesBD.getWritableDatabase(); // Accès en écriture sur la base de données

        String req = "INSERT INTO profil (datemesure, poids, taille, age, sexe) ";
        req += "VALUES ";
        req += "(\"" + profil.getDateMesure() + "\","
                + profil.getPoids() + ", "
                + profil.getTaille() + ", "
                + profil.getAge() + ", "
                + profil.getSexe() + ")";

        bd.execSQL(req);
    }

    /**
     * Recupération du dernier profil
     * @return le dernier profil
     */
    public Profil recupDernier(){
        Profil profil = null;

        bd = accesBD.getReadableDatabase(); // Accès en lecture à la BDD

        String req = "SELECT datemesure, poids, taille, age, sexe FROM profil"; // récupération des profils

        Cursor curseur = bd.rawQuery(req, null); // Initialisation du curseur
        curseur.moveToLast(); // Positionnement sur la dernière ligne

        if(!curseur.isAfterLast()){
            Date datemesure = new Date();
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(poids, taille, age, sexe, datemesure);
        }

        curseur.close();

        return profil;
    }
}
