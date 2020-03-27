//cette classe permet de representer les noeuds pouvant recevoir des donnees

import java.util.ArrayList;

//un user est defini par un identifiant unique
//+ un autre identifiant de son noeud systeme (qui va recevoir les données)
//+ liste des idenditifiants des données

public class Utilisateurs {
	private int idUtilisateur;
	private ArrayList<Integer> listeIdDonnees = new ArrayList<Integer>();
	private int noeudAccessible;

//constructeur :
	public Utilisateurs(int idUtili, ArrayList<Integer> listeIdDonnees, int noeudAccessible) {
		this.idUtilisateur = idUtili;
		this.listeIdDonnees = listeIdDonnees;
		this.noeudAccessible = noeudAccessible;
	}

//getters:
	
//methode qui permet d'avoir l'identifiant de l'utilisateur  :
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
//methode qui permet de voir la liste des id des donnees  :
	public ArrayList<Integer> getListeIdDonnees() {
		return listeIdDonnees;
	}
//methode qui permet d'obtenir l'id du noeud accessible  :
	public int getNoeudAccessible() {
		return noeudAccessible;
	}

	
//setters:

//methode qui permet de modifier la liste des id des donnees  :
	public void setListeIdDonnees(ArrayList<Integer> listeIdDon) {
		this.listeIdDonnees = listeIdDon;
	}
//methode qui modifie le id du noeud systeme qui accessible  :
	public void setNoeudAccessible(int noeudAccessible) {
		this.noeudAccessible = noeudAccessible;
	}	
 	
}
