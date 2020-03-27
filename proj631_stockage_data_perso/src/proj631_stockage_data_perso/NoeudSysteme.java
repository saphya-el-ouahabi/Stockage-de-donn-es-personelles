//cette classe permet de representer les noeuds pouvant recevoir des donnees

import java.util.ArrayList;

//un noeud systeme est defini par un identifiant + capacite 
//ainsi qu'une liste de donnees stockees localement
//et une liste de noeuds accessibles

public class NoeudSysteme implements Noeud {
	private int idNoeudSysteme;
	private int capaciteNoeudSysteme;
	
	private ArrayList<Integer> listeDonneesStockees = new ArrayList<Integer>(); 
	private ArrayList<NoeudSysteme> noeudAccessibles = new ArrayList<NoeudSysteme>();

//constructeur :
	public NoeudSysteme(int idNoeudSyst, int capacite) {
		this.idNoeudSysteme = idNoeudSyst;
		this.capaciteNoeudSysteme = capacite;
	}
	
//methode qui permet de creer une représentation "string" d'un objet, ici l'id d'un noeud : 	
	public String toString() {
		return this.idNoeudSysteme+"";
	}
	
	
//getters :
	
//methode qui retourne identifiant d'un noeud systeme  :
	public int getIdNoeud() {
		return this.idNoeudSysteme;
	}
	
//methode qui nous retourne la capacite d'un noeud  :
	public int getCapacite() {
		return this.capaciteNoeudSysteme;
	}	
	
//methode qui permet d'obtenir le contenu de la liste des id des données  :
	public ArrayList<Integer> getListeDonneesStockees() {
		return listeDonneesStockees;
	}

//methode qui retourne les noeuds qui sont accessibles  :
	public ArrayList<NoeudSysteme> getNoeudAccessibles() {
		return noeudAccessibles;
	}

	
//add:
	
//methode qui permet d'ajouter l'id d'un noeud systeme	:
	public void ajouterNoeudAccessible(NoeudSysteme noeud) {
		this.noeudAccessibles.add(noeud);
	}
//methode qui permet d'ajouter une donnee  :	
	public void ajouterDonnee(Donnees donnee) {
		this.listeDonneesStockees.add(donnee.getIDdonnee());
		this.capaciteNoeudSysteme = this.capaciteNoeudSysteme - donnee.getTaille() ;
	}

}
