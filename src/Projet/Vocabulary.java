package Projet;

import java.io.*;
import java.util.HashSet;

public class Vocabulary {
    private static Vocabulary instance;
    private HashSet<String> mots;
    private HashSet<String> stopWords;

    private Vocabulary() {
        mots = new HashSet<>();
        stopWords = new HashSet<>();
    }

    public static Vocabulary getInstance() {
        if (instance == null) {
            instance = new Vocabulary();
        }
        return instance;
    }

    public void addMot(String mot) {
        if (!stopWords.contains(mot)) { 
            mots.add(mot);
        }
    }

    public int taille() {
        return mots.size();
    }

    public int getIdentifiant(String mot) {
        return mots.contains(mot) ? mot.hashCode() : -1;
    }

    public void chargerStopWords(String cheminFichier) {
        try (BufferedReader br = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                stopWords.add(ligne.trim().toLowerCase());
            }
            System.out.println("Stop words chargés depuis " + cheminFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier de stop words : " + e.getMessage());
        }
    }
}
