//classe main() qui permet de lancer le programme 

import org.jgrapht.graph.*;
import java.util.ArrayList;
import java.util.Arrays;

////////////////////////////////////////////////////////////////////////////
public class Main {
	
	public static void main(String[] args) {
		System.out.println("Bonjour  :) \n ");
// le graphe g correspond à la question 2 du projet
		System.out.println("Question 2 : ");
		
//création du graphe:
		Graphe<NoeudSysteme, Edge> g = new Graphe <>(Edge.class);
		
//creation des donnees qui vont etre placees dans un graphe
		Donnees donnee1 = new Donnees(1,40);
		Donnees donnee2 = new Donnees(2,25);
		Donnees donnee3 = new Donnees(3,25);
		
		
//creation d'une liste qui va contenir ces dernieres 		
		ArrayList<Donnees> listeDonnees = new ArrayList<Donnees>();
		listeDonnees.add(donnee1);
		listeDonnees.add(donnee2);
		listeDonnees.add(donnee3);
		g.addDonneesAPlacer(listeDonnees);
		
		
//creation des noeud systemes qui vont contenir des donnees
		NoeudSysteme num1 = new NoeudSysteme(1,50);
		NoeudSysteme num2 = new NoeudSysteme(2,40);
		NoeudSysteme num3 = new NoeudSysteme(3,40);
		
		
//ajout des noeuds dans le graphe	
		g.addNoeudGraphe(num1);
		g.addNoeudGraphe(num2);
		g.addNoeudGraphe(num3);
		
//lier les noeuds entre eux:
		//le noeud numero1 et numero2
		num1.ajouterNoeudAccessible(num2);
		num2.ajouterNoeudAccessible(num1);
		
		//le noeud numero2 et numero3
		num2.ajouterNoeudAccessible(num3);
		num3.ajouterNoeudAccessible(num2);
		
//ajout des noeuds numero1 numero2 et numero3
//ou addVertex est une fonction de la librairie Jgrapht
		g.addVertex(num1);
		g.addVertex(num2);
		g.addVertex(num3);
		
//ajout des arcs en mentionnant en paramatre les 2 noeuds qui vont etre relier par ce dernier
		DefaultWeightedEdge edge1_2 = (DefaultWeightedEdge)g.addEdge(num1, num2);
		DefaultWeightedEdge edge2_3 = (DefaultWeightedEdge)g.addEdge(num2, num3);
		
//modifier le poids des arcs à 1 par exemple
		g.setEdgeWeight(edge1_2, 1);
		g.setEdgeWeight(edge2_3, 1);
		
		
//incrementation des identifiants des donnees dans une liste 
		ArrayList<Integer> listeDesDonnees = new ArrayList<Integer>();
		listeDesDonnees.add(donnee1.getIDdonnee());
		listeDesDonnees.add(donnee2.getIDdonnee());
		listeDesDonnees.add(donnee3.getIDdonnee());
		
//creation d'un user que l'on va ajouter au graphe 		
		Utilisateurs utilisateur1 = new Utilisateurs(1,listeDesDonnees,1);
		g.addVertex(utilisateur1);
		DefaultWeightedEdge edge4 = (DefaultWeightedEdge)g.addEdge(utilisateur1, num1);
		g.setEdgeWeight(edge4, 2);
		
		g.putDonneeDansLeGraphe(utilisateur1);
		
		System.out.println("");
		g.affichageNoeudGraphe();
		
		
		System.out.println("");
		System.out.println("graphe:");
		System.out.println(g);

////////////////////////////////////////////////////////////////////////////
		
		//QUESTION3 //
		System.out.println("\n");
		System.out.println("Question 3 : ");
				
		Graphe<NoeudSysteme, Edge> g2 = new Graphe <>(Edge.class);
				
//creation des donnees que l'on souhaite placer dans le graphe
		Donnees donnee11 = new Donnees(1,40);
		ArrayList<Donnees> listeDonnee = new ArrayList<Donnees>();
		listeDonnee.add(donnee11);
		g2.addDonneesAPlacer(listeDonnee);
		System.out.println("\n on cherche a placer la donnee : "+donnee11+"    entre les deux utilisateurs dans le graphe !");

//creation des noeud systemes qui vont contenir des donnees		
		NoeudSysteme noeud1 = new NoeudSysteme(1,50);
		NoeudSysteme noeud2 = new NoeudSysteme(2,40);
		NoeudSysteme noeud3 = new NoeudSysteme(3,40);
		NoeudSysteme noeud4 = new NoeudSysteme(4,40);
				
//ajout des noeuds dans le graphe
		g2.addNoeudGraphe(noeud1);
		g2.addNoeudGraphe(noeud2);
		g2.addNoeudGraphe(noeud3);
		g2.addNoeudGraphe(noeud4);

//lier les noeuds entre eux:
		//le noeud numero1 et numero2
		noeud1.ajouterNoeudAccessible(noeud2);
	    noeud2.ajouterNoeudAccessible(noeud1);
						
	    //le noeud numero2 et numero3
	    noeud2.ajouterNoeudAccessible(noeud3);
	    noeud3.ajouterNoeudAccessible(noeud2);
				
	   //le noeud numero3 et numero4
		noeud3.ajouterNoeudAccessible(noeud4);
		noeud4.ajouterNoeudAccessible(noeud3);
				
//ajout des noeuds au sein du graphe
		g2.addVertex(noeud1);
		g2.addVertex(noeud2);
		g2.addVertex(noeud3);
		g2.addVertex(noeud4);
				
//ajout des arcs + liaisons
		DefaultWeightedEdge edgen1n2 = (DefaultWeightedEdge)g2.addEdge(noeud1, noeud2);
		DefaultWeightedEdge edgen2n3 = (DefaultWeightedEdge)g2.addEdge(noeud2, noeud3);
		DefaultWeightedEdge edgen3n4 = (DefaultWeightedEdge)g2.addEdge(noeud3, noeud4);
				
//indication du poids :1
		g2.setEdgeWeight(edgen1n2, 1);
		g2.setEdgeWeight(edgen2n3, 1);
		g2.setEdgeWeight(edgen3n4, 1);

//creation d'une liste qui va contenir les data qui vont etre placees
		ArrayList<Integer> listeDesDonnees2 = new ArrayList<Integer>();
		listeDesDonnees2.add(donnee11.getIDdonnee());
				
//creation des 2 utilisateurs
		Utilisateurs utilisateur11 = new Utilisateurs(1,listeDesDonnees2,2);
		Utilisateurs utilisateur22 = new Utilisateurs(2,listeDesDonnees2,4);       		
				
//appel de la methode qui va placer les 2 utilisateurs finaux dans le graphe avec les data		
		System.out.println("voila la position de la data 1:");
		g2.putDonneeDes2Utils(utilisateur11,utilisateur22,donnee11);			
		g2.affichageNoeudGraphe();
								
	}
////////////////////////////////////////////////////////////////////////////
	
//QUESTION4 //
	//System.out.println("\n");
	//System.out.println("Question 4 : ");	

}

