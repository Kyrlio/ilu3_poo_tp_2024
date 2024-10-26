package jeu;

import java.util.HashSet;
import java.util.Set;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main;
	
	protected Joueur(String nom) {
		this.nom = nom;
		this.zoneDeJeu = new ZoneDeJeu();
		this.main = new MainJoueur();
	}

	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur) {
			Joueur joueur = (Joueur) obj;
			return this.toString().equals(joueur.toString());
		}
		return false;
	}

	public MainJoueur getMain() {
		return main;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) return null;
		Carte carte = sabot.piocher();
		donner(carte);
		return carte;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer(Carte carte) {
		zoneDeJeu.deposer(carte);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsPossibles = new HashSet<>();
		for (Joueur participant : participants) {
			for (Carte carte : main) {
				Coup coup = new Coup(carte, this, participant);
				if (coup.estValide()) coupsPossibles.add(coup);
			}
		}
		return coupsPossibles;
	}
	
	public Set<Coup> coupsDefausse(){
		Set<Coup> coupsDefausse = new HashSet<>();
		for (Carte carte : main) {
			Coup coup = new Coup(carte, this, null);
			if (coup.estValide()) coupsDefausse.add(coup);
		}
		return coupsDefausse;
	}
	

}
