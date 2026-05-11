package Projet;

import java.util.*;

public class Bm25 {
	private HashMap<Document, double[]> tf;
	private double[] idf;
    private double k1;
    private double b;
    private double avgDl;

    public Bm25(double k1, double b) {
        this.k1 = k1;
        this.b = b;
    }

    public void processCorpus(Corpus corpus) {
        System.out.println("Traitement du Corpus pour BM25");

        this.calculerBm25(corpus);
    }

    public void calculerBm25(Corpus corpus) {
        System.out.println("Calcul des scores BM25 pour chaque document");

        Vocabulary vocabulaire = Vocabulary.getInstance();
        int tailleVocabulaire = vocabulaire.taille();

        this.tf = new HashMap<>();
        this.idf = new double[tailleVocabulaire];

        double totalLength = 0;
        for (Document document : corpus.getDocuments()) {
            totalLength += document.size();
        }
        this.avgDl = totalLength / corpus.size();

        for (Document document : corpus.getDocuments()) {
            double[] tfVector = new double[tailleVocabulaire];
            HashMap<String, Integer> compteurMots = new HashMap<>();

            for (Mot mot : document) {
                String motStr = mot.getMot();
                compteurMots.put(motStr, compteurMots.getOrDefault(motStr, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : compteurMots.entrySet()) {
                int idMot = vocabulaire.getIdentifiant(entry.getKey());
                if (idMot != -1) {
                    tfVector[idMot] = entry.getValue();
                }
            }

            tf.put(document, tfVector);
        }

        int totalDocuments = corpus.size();
        int[] documentCounts = new int[tailleVocabulaire];

        for (Document document : corpus.getDocuments()) {
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
                idf[i] = Math.log(1 + (double) (totalDocuments - documentCounts[i] + 0.5) / (documentCounts[i] + 0.5));
            } else {
                idf[i] = 0.0; 
            }
        }

        for (Document document : corpus.getDocuments()) {
            double[] bm25Vector = new double[tailleVocabulaire];
            double[] tfVector = tf.get(document);

            double docLength = document.size();
            for (int i = 0; i < tailleVocabulaire; i++) {
                if (tfVector[i] > 0) {
                    double numerator = tfVector[i] * (k1 + 1);
                    double denominator = tfVector[i] + k1 * (1 - b + b * (docLength / avgDl));
                    bm25Vector[i] = idf[i] * (numerator / denominator);
                } else {
                    bm25Vector[i] = 0.0;
                }
            }

            tf.put(document, bm25Vector);
        }
    }

}
