package Projet;

import java.io.*;
import java.util.*;

public class Corpus extends Vector<Document> {
    private static final long serialVersionUID = 1L;
    private String titre;
    private List<Document> documents = new ArrayList<>();

    public Corpus(String cheminFichier, DataSets dataSet) {
        this.titre = dataSet.toString();
        lireFichier(cheminFichier);
    }

    private void lireFichier(String cheminFichier) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = bufferedReader.readLine()) != null) {
                Document doc = new Document(ligne);
                String[] mots = ligne.split("\\s+");
                for (String mot : mots) {
                    doc.putMot(mot);
                }
                documents.add(doc); 

                if (documents.size() % 1000 == 0) {
                    System.out.println("Chargé " + documents.size() + " documents.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }

    public List<Document> getDocuments() {
        return documents; 
    }

    public String getTitre() {
        return titre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Titre du Corpus: ").append(titre).append("\n");
        sb.append("Documents dans le corpus:\n");
        for (Document doc : documents) {
            sb.append("- ").append(doc.toString()).append("\n");
        }
        return sb.toString();
    }

    public int taille(TailleMot calculateur) {
        return calculateur.calculer(this);
    }

    public int taille(TailleDocument calculateur) {
        return calculateur.calculer(this);
    }

    public Object getFeatures(Object model) {
        if (model instanceof TfIdf) {
            ((TfIdf) model).processCorpus(this);
            return model;
        } else if (model instanceof Bm25) {
            ((Bm25) model).processCorpus(this);
            return model;
        }
        return null;
    }
}
