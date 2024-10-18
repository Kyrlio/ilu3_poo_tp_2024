package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> pileLimites;	// Pile de Vitesse
	private List<Bataille> pileBatailles;	
	private List<Borne> collectionBornes;
	
//	public ZoneDeJeu(List<DebutLimite> pileLimites, List<Bataille> pileBatailles, List<Borne> collectionBornes) {
//		this.pileLimites = pileLimites;
//		this.pileBatailles = pileBatailles;
//		this.collectionBornes = collectionBornes;
//	}
	
	public ZoneDeJeu() {
		pileLimites = new ArrayList<>();
		pileBatailles = new ArrayList<>();
		collectionBornes = new ArrayList<>();
	}
	
	public int donnerLimitationVitesse() {
		int limite = 200;
		if (pileLimites.isEmpty() || pileLimites.getFirst() instanceof FinLimite) 
			return limite;
		else
			return 50;
	}
	
	public int donnerKmParcourus() {
		int km = 0;
		for (Borne borne : collectionBornes) {
			km += borne.getKm();
		}
		return km;
	}
	
	public void deposer(Carte carte) {
		switch (carte) {
		    case Borne borne -> collectionBornes.addFirst(borne);
		    case Limite limite -> pileLimites.addFirst(limite);
		    case Bataille bataille -> pileBatailles.addFirst(bataille);
		    default -> throw new IllegalArgumentException("La carte n'est ni une Borne, ni une Limite, ni une Bataille");    
		}
	}
	
	// Renvoie true si une borne peut etre déposée
	public boolean peutAvancer() {
		return !pileBatailles.isEmpty() && pileBatailles.getFirst().equals(Cartes.FEU_VERT);
	}
	
	private boolean estDepotFeuVertAutorise() {
		return 	pileBatailles.isEmpty() ||
				pileBatailles.getFirst().equals(Cartes.FEU_ROUGE) ||
				(pileBatailles.getFirst() instanceof Parade && !pileBatailles.getFirst().equals(Cartes.FEU_VERT));
	}
	
	// Renvoie true si le jouer n'est pas bloqué
	// borne <= vitesse limite
	// et somme bornes <= 1000
	private boolean estDepotBorneAutorise(Borne borne) {
		return 	peutAvancer() &&
				borne.getKm() <= donnerLimitationVitesse() &&
				donnerKmParcourus() + borne.getKm() <= 1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite && (pileLimites.isEmpty() || pileLimites.getFirst() instanceof FinLimite)) return true;
		if (limite instanceof FinLimite && pileLimites.getFirst() instanceof DebutLimite) return true;
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque && peutAvancer()) return true;
		if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			} else if (!pileBatailles.isEmpty() &&
						pileBatailles.getFirst() instanceof Attaque &&
						pileBatailles.getFirst().getClass().equals(bataille.getClass())) 
				return true;
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
		if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
		if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
		return false;
	}
	

}
