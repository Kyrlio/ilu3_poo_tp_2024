package cartes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JeuDeCartes {
	Map<Carte, Integer> cartes;
	
	public JeuDeCartes() {
		cartes = new HashMap<>();
		cartes.put(new Borne(25), 10);
		cartes.put(new Borne(50), 10);
		cartes.put(new Borne(75), 10);
		cartes.put(new Borne(100), 12);
		cartes.put(new Borne(200), 4);
		cartes.put(new Parade(Type.FEU), 14);
		cartes.put(new Parade(Type.ESSENCE), 6);
		cartes.put(new Parade(Type.CREVAISON), 6);
		cartes.put(new Parade(Type.ACCIDENT), 6);
		cartes.put(new Attaque(Type.FEU), 5);
		cartes.put(new Attaque(Type.ESSENCE), 3);
		cartes.put(new Attaque(Type.CREVAISON), 3);
		cartes.put(new Attaque(Type.ACCIDENT), 3);
		cartes.put(new Botte(Type.ESSENCE), 1);
		cartes.put(new Botte(Type.CREVAISON), 1);
		cartes.put(new Botte(Type.ACCIDENT), 1);
		cartes.put(new Botte(Type.FEU), 1);
		cartes.put(new DebutLimite(), 4);
		cartes.put(new FinLimite(), 6);
    }

	public String affichageJeuDeCartes() {
		StringBuilder affichage = new StringBuilder();
		
		for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
			Carte nom = entry.getKey();
			int nombre = entry.getValue();
			affichage.append(nombre + " " + nom.toString() + "\n");
		}
		return affichage.toString();
	}
	
	// Pour recupérer toutes les cartes et les initialiser dans Sabot plus facilement
	public Carte[] donnerCartes() {
        int totalCartes = 0;
        Carte[] toutesLesCartes = new Carte[totalCartes];
        int index = 0;
        
        for (Map.Entry<Carte, Integer> entry : cartes.entrySet()) {
        	Carte nom = entry.getKey();
        	int nombre = entry.getValue();
        	totalCartes += nombre;
        	for (int i = 0; i < nombre; i++) {
        		toutesLesCartes[index] = nom;
        		index++;
        	}
        }
        return toutesLesCartes;
    }
	
	public boolean checkCount() {
		int nombreTotal = 106;
		int nombreCarte = 0;
		for (Map.Entry<Carte, Integer> entry : cartes.entrySet())
			nombreCarte += entry.getValue();
		return nombreTotal == nombreCarte;
	}

}
