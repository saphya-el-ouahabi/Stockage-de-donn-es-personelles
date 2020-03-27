//cette classe permet de representer les donnees stockees dans les noeuds

//une donnée est définie par un identifiant + une taille   :
public class Donnees {
	private int IDdonnee; 
	private int taille;
	
	
//constructeur 	:
	public Donnees(int iDdonnee, int taille) {
		this.IDdonnee = iDdonnee;
		this.taille = taille;
	}

//methode qui permet de creer une représentation "string" d'un objet, ici les donnees   : 
	public String toString() {
		return "donnee : "+this.IDdonnee + " taille : "+this.getTaille();
	}

	
//getters: 
	
//methode qui permet d'obtenir l'identifiant d'une donnee   :
	public int getIDdonnee() {
		return IDdonnee;
	}
//methode qui permet d'obtenir la taille d'une donnee   :
	public int getTaille() {
		return taille;
	}

	
// setters: 
	
//methode qui permet de modifier l'identifiant d'une donnee   :
	public void setIDdonnee(int idDon) {
		this.IDdonnee = idDon;
	}
//methode qui permet de modifier la taille d'une donnee    :
	public void setTaille(int taille) {
		this.taille = taille;
	}		
}
