package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Carte carte;
	private Joueur joueurCourant;
	private Joueur joueurCible;
	
	public Coup(Carte carte, Joueur joueurCourant, Joueur joueurCible) {
		this.carte = carte;
		this.joueurCourant = joueurCourant;
		this.joueurCible = joueurCible;
	}

	public Carte getCarte() {
		return carte;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		if (carte instanceof Attaque || carte instanceof DebutLimite) return joueurCible != null;
		else return true;
	}
	
	@Override
	public String toString() {
		if (getJoueurCible() == null)
			return "defausse la carte " + carte.toString();
		return "depose la carte " + carte.toString() + " dans la zone de jeu de " + getJoueurCible(); 
	}
	
	

}
