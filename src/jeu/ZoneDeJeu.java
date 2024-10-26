package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cartes.*;

public class ZoneDeJeu {
	private List<Limite> pileLimites;	// Pile de Vitesse
	private List<Bataille> pileBatailles;	
	private List<Borne> collectionBornes;
	private Set<Botte> ensBotte;
	
	public ZoneDeJeu() {
		pileLimites = new ArrayList<>();
		pileBatailles = new ArrayList<>();
		collectionBornes = new ArrayList<>();
		ensBotte = new HashSet<>();
	}
	
	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty() || pileLimites.getFirst() instanceof FinLimite || ensBotte.contains(new Botte(Type.FEU))) 
			return 200;
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
		    case Botte botte -> ensBotte.add(botte);
		    default -> throw new IllegalArgumentException("La carte n'est ni une Borne, ni une Limite, ni une Bataille, ni une Botte");    
		}
	}
	
	// Renvoie true si une borne peut etre déposée
	public boolean peutAvancer() {
		if (pileBatailles.isEmpty() && estPrioritaire()) return true;
		
		if (!pileBatailles.isEmpty()) {
			Bataille first = pileBatailles.getFirst();
			if (first.equals(Cartes.FEU_VERT)) return true;
			if (first instanceof Parade && estPrioritaire()) return true;
			if (first.equals(new Attaque(Type.FEU)) && estPrioritaire()) return true;
			if (first instanceof Attaque && !first.equals(new Attaque(Type.FEU))) {
				Type firstType = first.getType();
				Botte botte = new Botte(firstType);
				if (ensBotte.contains(botte) && estPrioritaire()) return true;
			}
		}
		
		return false;
		}
	
	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) return false;
		
		if (pileBatailles.isEmpty()) return true;
		
		Bataille first = pileBatailles.getFirst();
		if (first.equals(Cartes.FEU_ROUGE)) return true;
		else if (first instanceof Parade && !first.equals(Cartes.FEU_VERT)) return true;
		else if (first instanceof Attaque) {
			Type firstType = first.getType();
			if (ensBotte.contains(new Botte(firstType))) return true;
		}
		
		return false;
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
		if (ensBotte.contains(Cartes.PRIORITAIRE)) return false;
		if (limite instanceof DebutLimite && (pileLimites.isEmpty() || pileLimites.getFirst() instanceof FinLimite)) return true;
		if (limite instanceof FinLimite && pileLimites.getFirst() instanceof DebutLimite) return true;
		if (estPrioritaire()) return false;
		return false;
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque && peutAvancer()) {
			if (pileBatailles.isEmpty() || !pileBatailles.getFirst().equals(bataille)) return true;
			else return false;
		}
		else if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			} else if (!pileBatailles.isEmpty() &&
						pileBatailles.getFirst() instanceof Attaque &&
						pileBatailles.getFirst().getClass().equals(bataille.getClass())) 
				return true;
		}
		Type type = bataille.getType();
		if (ensBotte.contains(new Botte(type))) return false;
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		switch (carte) {
			case Borne borne -> {return estDepotBorneAutorise(borne);}
			case Limite limite -> {return estDepotLimiteAutorise(limite);}
			case Bataille bataille -> {return estDepotBatailleAutorise(bataille);}
			case Botte botte when !ensBotte.contains(botte) -> {return true;}
		default -> throw new IllegalArgumentException("Unexpected value: " + carte);
		}
	}
	
	public boolean estPrioritaire() {
		return ensBotte.contains(Cartes.PRIORITAIRE);
	}
	

}
