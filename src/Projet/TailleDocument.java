package Projet;


public class TailleDocument {
    public int calculer(Corpus corpus) {
        if (corpus == null || corpus.getDocuments() == null) {
            return 0;
        }
        return corpus.getDocuments().size();
    }
}

