package jeu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class Jeu {
	private Sabot sabot;			// Cartes melangees
	private Set<Joueur> joueurs;

	public Jeu() {
		JeuDeCartes jeu = new JeuDeCartes();
		Carte[] cartes = jeu.donnerCartes();
		List<Carte> listeCartes = new ArrayList<>();
		Collections.addAll(listeCartes, cartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		Carte[] cartesMelangees = listeCartes.toArray(new Carte[0]);
		this.sabot = new Sabot<>(cartesMelangees);
		joueurs = new LinkedHashSet<>();
	}
	
	public void inscrire(Joueur... joueur) {
		Collections.addAll(this.joueurs, joueur);
	}
	
	public void distribuerCartes() {
		for (Joueur joueur : joueurs) {
			for (int i = 0; i <= 6; i++) {
				joueur.donner(sabot.piocher());
			}
		}
	}
	

}
