//cette classe permet de modéliser le systeme en créant le graphe grace à Jgrapht

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.alg.*;
import org.jgrapht.graph.SimpleWeightedGraph;

//un graph herite de la classe SimpleWeightedGraph issue de la librairie Jgrapht
// et il est defini par une liste de donnees qui doit etre placees
// + une liste de noeuds systeme

public class Graphe<Noeud,Edge> extends SimpleWeightedGraph {
	private ArrayList<Donnees> listeDonneesAPlacer = new ArrayList<Donnees>();
	private ArrayList<NoeudSysteme> listeNoeuds = new ArrayList<NoeudSysteme>();

//appel de la classe mère pour le constructeur :	
	public Graphe(Class arg0) {
		super(arg0);
	}
	
//methode qui va calculer le chemin le plus court entre 2 noeuds systemes	:
	public List cheminLePlusCourt(NoeudSysteme num1,NoeudSysteme num2) {
		DijkstraShortestPath<NoeudSysteme,Edge> dij = new DijkstraShortestPath<NoeudSysteme,Edge> (this, num1, num2);
		
		System.out.println(dij.findPathBetween(this, num1, num2)); 
		return dij.findPathBetween(this, num1, num2);
	}
		
//methode qui va incrementer des donnees au graphe	 :
	public void addDonneesAPlacer(ArrayList<Donnees> donnee) {
		for(int i = 0;i<donnee.size();i++) {
			this.listeDonneesAPlacer.add(donnee.get(i));
		}
	}

//methode qui permet d'obtenir identifiant d'un noeud en parcourant la liste des noeuds 
//et retourne un message d'alerte si l'id n'existe pas  : 
	public NoeudSysteme getNoeudParSonId(int idNoeud) {
		for(int i = 0;i<this.listeNoeuds.size();i++) {
			if (this.listeNoeuds.get(i).getIdNoeud() == idNoeud){
				return this.listeNoeuds.get(i);
			}}
		System.out.println("L'id du noeud souhaite n'existe pas dans le graphe");
		return null;
	}
	
//methode qui permet d'obtenir la liste de données intermediaires (sauvees) : 	
	public ArrayList<Donnees> getListeCopie(ArrayList<Donnees> don){
		ArrayList<Donnees> intermediaire = new ArrayList<Donnees>();
		for(Donnees donnee : don) {
			intermediaire.add(donnee);
		}
		System.out.println("la liste intermediaire:");
		System.out.println(intermediaire);
		return intermediaire;
	}
	
//methode qui va ajouter un noeud systme au graphe  :	
	public void addNoeudGraphe(NoeudSysteme noeud) {
		this.listeNoeuds.add(noeud);
	}
		
	
//methode qui permet de placer au bon endroit les donnees dans le graphe  :
	public void putDonneeDansLeGraphe(Utilisateurs util) {
		
		//initialisation des listes des donnees a placer + des donnees intermediaires : 
		ArrayList<Donnees> aPlacer = new ArrayList<Donnees>();
		ArrayList<Donnees> intermediaire = new ArrayList<Donnees>();

			aPlacer=this.listeDonneesAPlacer;
			intermediaire = this.getListeCopie(aPlacer);
			
			//recuperation du noeud systeme qui est joint a l'user :
			NoeudSysteme nUtil = this.getNoeudParSonId(util.getNoeudAccessible());
			
			//boucle qui va parcourir les elements de la liste des donnees a placer
			//si la taille de la donnee est < ou = a celle du noeud 
			//alors on incremente la donnee dans ce dernier + on l'enleve de la liste a placer
			for (int j = 0;j<aPlacer.size();j++) {
				if (aPlacer.get(j).getTaille() <= nUtil.getCapacite()) {
					nUtil.ajouterDonnee(aPlacer.get(j));
					intermediaire.remove(aPlacer.get(j));
					
			//sinon on parcourt la liste des noeuds systeme accesibles
			//et on va essayer de trouver le chemin le + rapide entre le noeud courant et les autres noeuds systeme
			//en parcourant leur liste :  
					//-  si le poids de l'arc est < à 500 + si la capacite du noeud est > ou = à la taille de la donnee
			       //-  alors le 500 va devenir le poids de l'arc qui separe le noeud courant et un noeud de la liste des noeuds systeme dont on a acces	
				}else {
					ArrayList<NoeudSysteme> nAccessibles = nUtil.getNoeudAccessibles();
					double min = 500; //fixation d'un poids d'arc par defaut assez grand pour qu'il puisse englober tous les autres
					NoeudSysteme nSuivant = null; //initialisation du noeud suivant
					for(NoeudSysteme n : nAccessibles) {
						if (this.getEdgeWeight(this.getEdge(nUtil,n))< min & n.getCapacite()>= aPlacer.get(j).getTaille()) {
							min = this.getEdgeWeight(this.getEdge(nUtil,n));
							nSuivant = n; //le noeud suivant devient le noeud de la liste
					}	}
			//apres cela, ssi un noeud suivant existe: - on ajoute la donnee dans le noeud suivant 
			//                                         - et on enleve la donnee qui a ete ajoute de la liste copie
			// 
					if(nSuivant != null) {
						nSuivant.ajouterDonnee(aPlacer.get(j));
						intermediaire.remove(aPlacer.get(j));	
						
			//sinon (si le noeud n'existe pas) 
			//on enleve la donnee de la liste copie
					}else {
						this.putDonneesDesNoeudsAcces(nUtil, aPlacer.get(j));
						intermediaire.remove(aPlacer.get(j));
				}	}
		//si la taille de la donnee intermediaire est > à 0,
		//alors afficher la liste intermediaire messages d'alertes suivants en console :
		}if(intermediaire.size() > 0 ) {
			System.out.println(intermediaire);
			System.out.println("Desolee mais il n'y a pas assez de capacite de stockage pour situer la donnee!");
			
		//si la taille de la donnee intermediaire est < ou = à 0, alors alerter l'user que ses data ont ete place :
		}else {
			System.out.println("les donnees de l'user "+util.getIdUtilisateur()+" sont placees !");
	}	}
		
//methode qui permet d'obtenir les noeuds suivants (autre chemin),
//on va parcourir les noeuds accesibles des noeuds accessibles: 
	public ArrayList<NoeudSysteme> getNoeudAccesDesAcces(NoeudSysteme n) {
		ArrayList<NoeudSysteme> noeuds = new ArrayList<NoeudSysteme>(); //initialisation de la liste des noeuds systeme 
		for (NoeudSysteme noeud : n.getNoeudAccessibles()) {
			NoeudSysteme noeud2 = noeud;
			for(NoeudSysteme noeudIntermed : noeud2.getNoeudAccessibles()) {
				noeuds.add(noeudIntermed);
		}}
		return noeuds;
	}
	
	
//methode qui permet de placer les donnees des noeuds accessibles:	
//on va parcourir les noeuds accessibles et si la capacite du noeud de la liste est > ou = a la taille de data alors on l'ajoute
	public void putDonneesDesNoeudsAcces(NoeudSysteme n,Donnees don) {
		ArrayList<NoeudSysteme> arrNoeud = this.getNoeudAccesDesAcces(n); //initialisation de la liste des noeuds systeme
		for(NoeudSysteme noeud : arrNoeud) {
			if(noeud.getCapacite() >= don.getTaille()) {
				noeud.ajouterDonnee(don);
			}}}	
	
//methode qui va permettre de placer les donnees des 2 utilisateurs dans le graphe  :
//en cherchant dans un 1er tps le chemin le plus rapide entre les 2 noeuds accessibles des utilisateurs
//et dans 2nd tps verifier si il y a un nombre pair d'arc entre les 2 noeuds
	public void putDonneeDes2Utils(Utilisateurs util1,Utilisateurs util2,Donnees don) {
		ArrayList<Edge> arr = (ArrayList) this.cheminLePlusCourt(this.getNoeudParSonId(util1.getNoeudAccessible()),this.getNoeudParSonId(util2.getNoeudAccessible()));
		int indice = arr.size()/2; //recuperation du noeud qui se trouve au centre des 2 noeuds accessibles
		String noeud = arr.get(indice).toString().charAt(1)+""; //charAt(1) == position 1
		int idNoeud = Integer.parseInt(noeud);
		if(this.getNoeudParSonId(idNoeud).getCapacite() >= don.getTaille()) {
			this.getNoeudParSonId(idNoeud).ajouterDonnee(don);
		}else {
			noeud = arr.get(indice).toString().charAt(5)+""; //charAt(5) == position 5
			idNoeud = Integer.parseInt(noeud);
			if(this.getNoeudParSonId(idNoeud).getCapacite() >= don.getTaille()) {
				this.getNoeudParSonId(idNoeud).ajouterDonnee(don);
		}}}
	
//methode qui permet d'afficher le graphe 
	public void affichageNoeudGraphe() {
		for(NoeudSysteme n : this.listeNoeuds) {
			System.out.println("Le noeud : " + n.getIdNoeud() + ", sa capacite : "+n.getCapacite() + " et les donnees placees : "+n.getListeDonneesStockees());
		}}
	
}
