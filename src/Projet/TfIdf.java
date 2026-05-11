package Projet;

import java.util.*;

public class TfIdf {
    private HashMap<Document, double[]> tf;
    private double[] idf;

    public TfIdf() {
        this.tf = new HashMap<>();
        this.idf = null;
    }

    public void vocabulaire(Corpus corpus) {
        Vocabulary vocabulaire = Vocabulary.getInstance();
        for (Document document : corpus) {
            for (Mot mot : document) {
                vocabulaire.addMot(mot.getMot());
            }
        }

        int tailleVocabulaire = vocabulaire.taille();
        this.idf = new double[tailleVocabulaire];
    }

    public void processCorpus(Corpus corpus) {
        Vocabulary vocabulaire = Vocabulary.getInstance();
        int tailleVocabulaire = vocabulaire.taille();

        this.tf = new HashMap<>();
        this.idf = new double[tailleVocabulaire];

        for (Document document : corpus) {
            double[] tfVector = new double[tailleVocabulaire];
            HashMap<String, Integer> compteurMots = new HashMap<>();

            for (Mot mot : document) {
                String motStr = mot.getMot();
                compteurMots.put(motStr, compteurMots.getOrDefault(motStr, 0) + 1);
            }

            int totalMots = document.size();
            for (Map.Entry<String, Integer> entry : compteurMots.entrySet()) {
                int idMot = vocabulaire.getIdentifiant(entry.getKey());
                if (idMot != -1) {
                    tfVector[idMot] = (double) entry.getValue() / totalMots;
                }
            }

            tf.put(document, tfVector);
        }

        int totalDocuments = corpus.size();
        int[] documentCounts = new int[tailleVocabulaire];

        for (Document document : corpus) {
            HashSet<Integer> motsVus = new HashSet<>();

            for (Mot mot : document) {
                int idMot = vocabulaire.getIdentifiant(mot.getMot());
                if (idMot != -1 && !motsVus.contains(idMot)) {
                    motsVus.add(idMot);
                    documentCounts[idMot]++;
                }
            }
        }

        for (int i = 0; i < tailleVocabulaire; i++) {
            if (documentCounts[i] > 0) {
                idf[i] = Math.log((double) totalDocuments / documentCounts[i]);
            } else {
                idf[i] = 0.0;
            }
        }
    }

    public double[] getIdf() {
        return idf;
    }

    public HashMap<Document, double[]> getTf() {
        return tf;
    }
}
