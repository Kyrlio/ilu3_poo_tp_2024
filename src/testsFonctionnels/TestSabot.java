package testsFonctionnels;

import jeu.Sabot;

import java.util.Iterator;

import cartes.*;

public class TestSabot {
	public static void main(String[] args) throws IllegalAccessException {
		
		/* -------------------------------------------------- TP1 --------------------------------------------------------*/
		
		Sabot sabot = new Sabot<Carte>();
		

//		for (int i = 0; i < 106; i++) {
//			sabot.piocher();
//		}
		
		Iterator<Carte> iter = sabot.iterator();
        while (iter.hasNext()) {
            Carte cartePiochee = iter.next();
            System.out.println("Je pioche " + cartePiochee.toString());
            iter.remove();
            
            //EXCEPTION levé : ConcurrentModificationException
        	sabot.ajouterCarte(new Botte(Type.ACCIDENT));
            cartePiochee = sabot.piocher();
        }

	}

}
