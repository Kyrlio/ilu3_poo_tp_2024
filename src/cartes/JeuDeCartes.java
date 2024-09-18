package cartes;

import java.util.ArrayList;

public class JeuDeCartes {
	private class Configuration {
		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(int nbExemplaires, Carte carte) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}
		
		public Carte getCarte() {
			return getCarte();
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}

	private Configuration[] typesDeCartes = new Configuration[19];

	public String affichageJeuDeCartes() {
		//TODO
		return null;
	}
	
	

}
