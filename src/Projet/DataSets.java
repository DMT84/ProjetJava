package Projet;

public enum DataSets {
	WIKIPEDIA("nom"),
	corpus("nom");
	private final String nom;
	private DataSets(String nom) {
        this.nom = nom;
    }
	public String getNom() {
        return nom;
    }
	public String toString() {
        return nom;
    }
}
