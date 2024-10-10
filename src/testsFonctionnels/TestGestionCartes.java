package testsFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {
	public static void main(String args[]) {
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		System.out.println(listeCartes);
		System.out.println(
				"liste melangee sans erreur ? " + GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = GestionCartes.rassembler(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblee sans erreur ? " + GestionCartes.verifierRassemblement(listeCartes));
				
		List<Integer> liste1 = new ArrayList<>();
		
		List<Integer> liste2 = new ArrayList<>();
		liste2.add(1);
		liste2.add(1);
		liste2.add(2);
		liste2.add(1);
		liste2.add(3);
		// liste2 = [1;1;2;1;3]
		
		List<Integer> liste3 = new ArrayList<>();
		liste3.add(1);
		liste3.add(4);
		liste3.add(3);
		liste3.add(2);
		// liste3 = [1;4;3;2]
		
		List<Integer> liste4 = new ArrayList<>();
		liste4.add(1);
		liste4.add(1);
		liste4.add(2);
		liste4.add(3);
		liste4.add(1);
		// liste3 = [1;1;2;3;1]
		
		System.out.println("liste1 : [] -> rassemblement erreur ? " + GestionCartes.verifierRassemblement(liste1));
		System.out.println("liste2 : [1;1;2;1;3] -> rassemblement erreur ? " + GestionCartes.verifierRassemblement(liste2));
		System.out.println("liste3 : [1;4;3;2] -> rassemblement erreur ? " + GestionCartes.verifierRassemblement(liste3));
		System.out.println("liste4 : [1;1;2;3;1] -> rassemblement erreur ? " + GestionCartes.verifierRassemblement(liste4));



		
		
		
		

	}

}