package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;			// Cartes melangees
	private Set<Joueur> joueurs;
	private Iterator<Joueur> iter;
	private final int NBCARTES = 6;

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		Carte[] cartesMelangees = listeCartes.toArray(new Carte[0]);
		sabot = new Sabot<>(cartesMelangees);
		joueurs = new LinkedHashSet<>();
		iter = joueurs.iterator();
	}
	
	public void inscrire(Joueur... joueur) {
		Collections.addAll(this.joueurs, joueur);
	}
	
	// distribue 6 cartes à tous les joueurs
	public void distribuerCartes() {
		for (Joueur joueur : joueurs) {
			for (int i = 0; i < NBCARTES; i++) {
				joueur.prendreCarte(sabot);
			}
		}
	}
	
	public StringBuilder jouerTour(Joueur joueur) {
		Set<Joueur> participants = new LinkedHashSet<>(joueurs);
//		participants.remove(joueur);
				
		StringBuilder str = new StringBuilder();
		Carte carte = joueur.prendreCarte(sabot);
		str.append("Le joueur " + joueur.toString() + " a pioche " + carte.toString() + "\n");
		str.append("Il a dans sa main : " + joueur.getMain());
		
		
		Coup coup = joueur.choisirCoup(participants);
		str.append("\n" + joueur.toString() + " " + coup);
		return str;
	}
	
	public Joueur donnerJoueurSuivant() {
		if (!iter.hasNext()) {
			iter = joueurs.iterator();
		}
		return iter.next();
	}
	
	public String lancer() {
		boolean fin = false;
		Joueur gagnant = null;
		distribuerCartes();
		while (!fin || !sabot.estVide()) {
			if (sabot.estVide()) break;
			Joueur joueur = donnerJoueurSuivant();
			System.out.println("C'est au tour de : " + joueur.toString());
			
			System.out.println(jouerTour(joueur));
			if (joueur.donnerKmParcourus() >= 1000) {
				fin = true;
				gagnant = joueur;
				break;
			}
			System.out.println("");
			System.out.println(joueur.afficherEtatJoueur());
			System.out.println("");
		}
		if (sabot.estVide()) System.out.println("Le sabot est vide.");
		System.out.println("");
		
		gagnant = calculerGagnant();
		System.out.println("\nClassement décroissant : " + classement().toString());
		return "Le gagnant est : " + gagnant + " avec " + gagnant.donnerKmParcourus() + " km.";
	}
	
	private Joueur calculerGagnant() {
		Joueur gagnant = null;
		int maxkm = 0;
		for (Joueur joueur : joueurs) {
			if (joueur.donnerKmParcourus() > maxkm) {
				maxkm = joueur.donnerKmParcourus();
				gagnant = joueur;
			}
			
		}
		
		return gagnant;
	}
	
	public Set<Joueur> classement() {
		Set<Joueur> classement = new TreeSet<>(new JoueurComparator());
		for (Joueur joueur : joueurs)
			classement.add(joueur);
		return classement;
	}
	
	private class JoueurComparator implements Comparator<Joueur> {

		@Override
		public int compare(Joueur j1, Joueur j2) {
			int comparaison = j1.donnerKmParcourus() - j2.donnerKmParcourus();
			if (comparaison != 0)
				return comparaison;
			return j1.toString().compareTo(j2.toString());
		}
		
	}
	
	
	/* ---------- GETTERS ---------- */

	public Sabot getSabot() {
		return sabot;
	}

	public Set<Joueur> getJoueurs() {
		return joueurs;
	}

	
	
	

}
