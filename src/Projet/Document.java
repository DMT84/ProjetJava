package Projet;

import java.util.*;

public class Document extends ArrayList<Mot> {
    private static final long serialVersionUID = 1L;
    private String titre;

    public Document(String titre) {
        this.titre = titre;
    }

    public void putMot(String mot) {
        this.add(new Mot(mot));
    }

    @Override
    public String toString() {
        StringBuilder doc = new StringBuilder("Titre: " + titre + "\nMots : ");
        for (Mot mot : this) {
            doc.append(mot.getMot()).append(" ");
        }
        return doc.toString();
    }

    public int size() {
        return super.size();
    }

    public String getTitre() {
        return titre;
    }
}
