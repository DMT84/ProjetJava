package Projet;

public class TailleMot {
    public int calculer(Corpus corpus) {
        int totalMots = 0;
        for (Document document : corpus.getDocuments()) {
            totalMots += document.size();
        }

        return totalMots;
    }
}
