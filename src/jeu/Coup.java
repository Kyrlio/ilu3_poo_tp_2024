package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Carte carte;
	private Joueur joueurCourant;
	private Joueur joueurCible;
	private boolean gotoSabot;
	
	public Coup(Carte carte, Joueur joueurCourant, Joueur joueurCible) {
		this.carte = carte;
		this.joueurCourant = joueurCourant;
		if (joueurCible == null) gotoSabot = true;
		else this.joueurCible = joueurCible;
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
		return (carte instanceof Attaque || carte instanceof DebutLimite);
	}
	
	
	
	

}
