package testsFonctionnels;

import jeu.Jeu;
import jeu.*;

public class TestJeu {

	public static void main(String[] args) {
		Jeu jeu = new Jeu();
		Sabot sabot = jeu.getSabot();
		Joueur lucas = new Joueur("Lucas");
		Joueur paul = new Joueur("Paul");
		Joueur pierre = new Joueur("Pierre");
				
		jeu.inscrire(lucas, paul, pierre);
//		System.out.println("Inscription faite");
//		System.out.println(jeu.getJoueurs());
//		System.out.println("----------------------------------------------------------------------------");
//		jeu.distribuerCartes();
//		System.out.println("Distribution des cartes faite");
//		System.out.println("----------------------------------------------------------------------------");
//		//sabot.afficherSabot();
//		System.out.println("Lucas :");
//		System.out.println(lucas.afficherEtatJoueur());
//		System.out.println("----------------------------------------------------------------------------");
//		System.out.println("Paul :");
//		System.out.println(paul.afficherEtatJoueur());
//		System.out.println("----------------------------------------------------------------------------");
//		System.out.println("Pierre :");
//		System.out.println(pierre.afficherEtatJoueur());
//		System.out.println("----------------------------------------------------------------------------");
		
		System.out.println("Joueurs : " + jeu.getJoueurs());

//		System.out.println(jeu.jouerTour(pierre));
//		System.out.println(" ");
//		System.out.println(jeu.jouerTour(paul));
//		System.out.println(" ");
//		System.out.println(jeu.jouerTour(lucas));
//		System.out.println(" ");
		
		System.out.println(jeu.lancer());
		

	}

}
