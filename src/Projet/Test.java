package Projet;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        String cheminFichierCorpus = "booksummaries.txt";
        String cheminFichierStopWords = "stopWords.txt"; 

        Vocabulary vocabulaire = Vocabulary.getInstance();
        vocabulaire.chargerStopWords(cheminFichierStopWords);

        DataSets dataSet = DataSets.WIKIPEDIA; 
        Corpus corpus = new Corpus(cheminFichierCorpus, dataSet);

        System.out.println("Contenu du corpus :");
        System.out.println(corpus.toString());

        TailleDocument tailleDocument = new TailleDocument();
        TailleMot tailleMot = new TailleMot();
        int nbDocuments = corpus.taille(tailleDocument);
        System.out.println("Nombre de documents dans le corpus : " + nbDocuments);
        int nbMots = corpus.taille(tailleMot);
        System.out.println("Nombre de mots dans le corpus : " + nbMots);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre requête : ");
        String query = scanner.nextLine();

        System.out.println("Entrez le nombre maximum de résultats à afficher : ");
        int maxResults = scanner.nextInt();

        TfIdf tfidf = new TfIdf();
        corpus.getFeatures(tfidf);

        System.out.println("Traitement de la requête : " + query);
        processQuery(query, maxResults, tfidf);
    }

    public static void processQuery(String query, int maxResults, TfIdf tfidf) {
        double[] features = features(query);
        HashMap<Document, Double> scoresDocuments = evaluate(features, tfidf);

        List<Map.Entry<Document, Double>> sortedScores = new ArrayList<>(scoresDocuments.entrySet());
        sortedScores.sort((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

        System.out.println("Affichage des meilleurs " + maxResults + " résultats :");
        for (int i = 0; i < Math.min(maxResults, sortedScores.size()); i++) {
            Document doc = sortedScores.get(i).getKey();
            double score = sortedScores.get(i).getValue();
            System.out.println("Score: " + score + " | Document: " + doc.getTitre());
        }
    }

    public static double[] features(String query) {
        String[] mots = query.split(" ");
        double[] features = new double[mots.length];
        for (int i = 0; i < mots.length; i++) {
            features[i] = 1.0;
        }
        return features;
    }

    public static HashMap<Document, Double> evaluate(double[] queryFeatures, TfIdf tfidf) {
        HashMap<Document, Double> scoresDocuments = new HashMap<>();
        for (Map.Entry<Document, double[]> entry : tfidf.getTf().entrySet()) {
            Document doc = entry.getKey();
            double[] docVector = entry.getValue();
            double score = 0.0;
            for (int i = 0; i < queryFeatures.length; i++) {
                score += queryFeatures[i] * docVector[i];
            }
            scoresDocuments.put(doc, score);
        }
        return scoresDocuments;
    }
}
