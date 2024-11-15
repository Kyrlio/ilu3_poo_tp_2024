package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import cartes.Borne;
import cartes.Carte;
import cartes.Parade;
import cartes.Type;

public class Joueur implements Comparable<Joueur>{
	private String nom;
	private ZoneDeJeu zoneDeJeu;
	private MainJoueur main;
	
	public Joueur(String nom) {
		this.nom = nom;
		this.zoneDeJeu = new ZoneDeJeu();
		this.main = new MainJoueur();
	}

	// Donne une carte dans la main du joueur
	private void donner(Carte carte) {
		main.prendre(carte);
	}
	
	// première carte du sabot goto main du joueur
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
	
	// renvoie l'ens des coups valides
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
			coupsDefausse.add(coup);
		}
		return coupsDefausse;
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	private Coup randomCoupChoisi(Set<Coup> coup) {
		if (coup.isEmpty()) {
			throw new IllegalArgumentException("La liste des coups est vide !");
		}
		Iterator<Coup> iter = coup.iterator();
		Random random = new Random();
		Coup next = null;
		int n = random.nextInt(coup.size());
		for (int i = 0; i <= n; i++) {
			next = iter.next();
		}
		
		if (next.getJoueurCible() != null) next.getJoueurCible().deposer(next.getCarte());
		retirerDeLaMain(next.getCarte());
		return next;
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsPossibles = coupsPossibles(participants);
		
		if (coupsPossibles.isEmpty()) {
			return randomCoupChoisi(coupsDefausse());
		}
		else {
			return randomCoupChoisi(coupsPossibles);
		}
	}
	
	public String afficherEtatJoueur() {
		boolean limiteVitesse = false;
		if (zoneDeJeu.donnerLimitationVitesse() == 50) limiteVitesse = true;
		return 	"Bottes : " + zoneDeJeu.afficherBottes() + "\n"
				+ "Limitation de vitesse : " + limiteVitesse + "\n"
				+ "Sommet pile bataille : " + zoneDeJeu.getFirstBataille() + "\n"
				+ "Main : " + main.toString() + "\n"
				+ "Km parcourus : " + donnerKmParcourus();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur) {
			Joueur joueur = (Joueur) obj;
			return this.toString().equals(joueur.toString());
		}
		return false;
	}
	
	@Override
	public int compareTo(Joueur joueurToCompare) {
		int comparaison = this.donnerKmParcourus() - joueurToCompare.donnerKmParcourus();
		if (comparaison != 0) return comparaison;
		return nom.compareTo(joueurToCompare.nom);
	}
	
	/* ---------- GETTERS ---------- */
	
	public ZoneDeJeu getZoneDeJeu() {
		return zoneDeJeu;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	
	public MainJoueur getMain() {
		return main;
	}

	
}
