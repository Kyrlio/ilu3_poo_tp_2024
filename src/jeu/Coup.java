package jeu;

import cartes.Attaque;
import cartes.Borne;
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
		boolean cond1 = !(carte instanceof Attaque || carte instanceof DebutLimite && joueurCible == joueurCourant);
		boolean cond2 = true;
		if (carte instanceof Borne borne) {
			cond2 = joueurCourant.estDepotAutorise(borne);
			if (joueurCible != joueurCourant) {
				cond2 = false;
			}
		}
		return cond1 && cond2;
	}
	
	@Override
	public String toString() {
		if (getJoueurCible() == null)
			return "defausse la carte " + carte.toString();
		return "depose la carte " + carte.toString() + " dans la zone de jeu de " + getJoueurCible(); 
	}
	
	

}
