package cartes;

import java.util.ArrayList;
import java.util.List;

public final class JeuDeCartes {
	private final Configuration[] typesDeCartes = new Configuration[19];
	
	public JeuDeCartes() {
        typesDeCartes[0] = new Configuration(10, new Borne(25));
        typesDeCartes[1] = new Configuration(10, new Borne(50));
        typesDeCartes[2] = new Configuration(10, new Borne(75));
        typesDeCartes[3] = new Configuration(12, new Borne(100));
        typesDeCartes[4] = new Configuration(4,	 new Borne(200));
        typesDeCartes[11] = new Configuration(4, new DebutLimite());
        typesDeCartes[6] = new Configuration(6,	 new FinLimite());
        typesDeCartes[5] = new Configuration(14, new Parade(Type.FEU));
        typesDeCartes[7] = new Configuration(6,  new Parade(Type.ESSENCE));
        typesDeCartes[8] = new Configuration(6,  new Parade(Type.CREVAISON));
        typesDeCartes[9] = new Configuration(6,  new Parade(Type.ACCIDENT));
        typesDeCartes[10] = new Configuration(5, new Attaque(Type.FEU));
        typesDeCartes[12] = new Configuration(3, new Attaque(Type.ESSENCE));
        typesDeCartes[13] = new Configuration(3, new Attaque(Type.CREVAISON));
        typesDeCartes[14] = new Configuration(3, new Attaque(Type.ACCIDENT));
        typesDeCartes[15] = new Configuration(1, new Botte(Type.ESSENCE));
        typesDeCartes[16] = new Configuration(1, new Botte(Type.CREVAISON));
        typesDeCartes[17] = new Configuration(1, new Botte(Type.ACCIDENT));
        typesDeCartes[18] = new Configuration(1, new Botte(Type.FEU));
    }

	public String affichageJeuDeCartes() {
		StringBuilder affichage = new StringBuilder();
		for (Configuration config : typesDeCartes) {
			if (config != null) {
				affichage.append(config.nbExemplaires)
				.append(" ")
				.append(config.getCarte().toString())
				.append("\n");
			}
		}
		return affichage.toString();
	}
	
	// Pour recupérer toutes les cartes et les initialiser dans Sabot plus facilement
	public Carte[] donnerCartes() {
        int totalCartes = 0;
        for (Configuration config : typesDeCartes) {
            if (config != null) {
                totalCartes += config.getNbExemplaires();
            }
        }

        Carte[] toutesLesCartes = new Carte[totalCartes];
        int index = 0;

        for (Configuration config : typesDeCartes) {
            if (config != null) {
                for (int j = 0; j < config.getNbExemplaires(); j++) {
                    toutesLesCartes[index++] = config.getCarte();
                }
            }
        }
        return toutesLesCartes;
    }
	
	public boolean checkCount() {
		int borne25 = 0;
		int borne50 = 0;
		int borne75 = 0;
		int borne100 = 0;
		int borne200 = 0;
		int feuVert = 0;
		int feuRouge = 0;
		int finLimite = 0;
		int bidonEssence = 0;
		int roueSecours = 0;
		int reparations = 0;
		int limite50 = 0;
		int panneEssence = 0;
		int crevaison = 0;
		int accident = 0;
		int citerneEssence = 0;
		int increvable = 0;
		int asVolant = 0;
		int prioritaire = 0;
		Carte[] checkCartes = donnerCartes();
		
		for (Carte carte : checkCartes) {
			if (carte instanceof Attaque) {
				switch ((((Attaque) carte).getType())) {
					case FEU : 
						feuRouge++;
						break;
						
					case ESSENCE :
						panneEssence++;
						break;
						
					case CREVAISON :
						crevaison++;
						break;
						
					case ACCIDENT :
						accident++;
						break;
				}
			} else if (carte instanceof Borne) {
				switch (((Borne) carte).getKm()) {
					case 25:
						borne25++;
						break;
						
					case 50:
						borne50++;
						break;
						
					case 75:
						borne75++;
						break;
						
					case 100:
						borne100++;
						break;
						
					case 200:
						borne200++;
						break;
				}
			} else if (carte instanceof Parade) {
				switch(((Parade) carte).getType()) {
				case FEU : 
					feuVert++;
					break;
				case ESSENCE :
					bidonEssence++;
					break;
				case CREVAISON :
					roueSecours++;
					break;
				case ACCIDENT :
					reparations++;
					break;
				}
			} else if (carte instanceof Botte) {
					switch (((Botte) carte).getType()) {
						case FEU : 
							prioritaire++;
							break;
						case ESSENCE :
							citerneEssence++;
							break;
						case CREVAISON :
							increvable++;
							break;
						case ACCIDENT :
							asVolant++;
							break;
					}
			} else if (carte instanceof DebutLimite) {
				limite50++;
			} else if (carte instanceof FinLimite) {
				finLimite++;
			}
		}
		
		return 
				borne25 == 10 &&
				borne50 == 10 &&
				borne75 == 10 &&
				borne100 == 12 &&
				borne200 == 4 &&
				feuVert == 14 &&
				feuRouge == 5 &&
				finLimite == 6 &&
				bidonEssence == 6 &&
				roueSecours == 6 &&
				reparations == 6 &&
				limite50 == 4 &&
				panneEssence == 3 &&
				crevaison == 3 &&
				accident == 3 &&
				citerneEssence == 1 &&
				increvable == 1 &&
				asVolant == 1 &&
				prioritaire == 1;
	}
	
	
	
	
	
	
	
	
	
	private class Configuration {
		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(int nbExemplaires, Carte carte) {
			this.nbExemplaires = nbExemplaires;
			this.carte = carte;
		}
		
		public Carte getCarte() {
			return carte;
		}

		public int getNbExemplaires() {
			return nbExemplaires;
		}
	}
	
	
	
	

}
