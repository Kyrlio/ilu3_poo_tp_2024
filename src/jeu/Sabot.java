package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Sabot<T extends Carte> implements Iterable<T>{
	private Carte[] cartes;
	private int nbCartes = 0; 
	private int nombreOperations = 0;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}
	
	public boolean estVide() {
		return cartes[0] == null;
	}
	
	public void ajouterCarte(Carte carte) throws IllegalAccessException {
		if (nbCartes >= cartes.length) {
			throw new IllegalAccessException("Le sabot plein ! Impossible d'ajouter la carte");
		} else {
			cartes[nbCartes] = carte;
			nbCartes++;
			nombreOperations++;
		}
	}
	
	// Renvoie la première carte du sabot et la supprime du sabot
	public T piocher() {
		Iterator<T> iter = this.iterator();
		if (!iter.hasNext()) {
			throw new NoSuchElementException("Le sabot est vide ! Impossible de piocher.");
		}
		T carte = iter.next();
		iter.remove();
		return carte;
	}
	
	public void afficherSabot() {
		for (Carte carte : cartes) {
			System.out.println(carte);
		}
	}
	
	public int getSizeSabot() {
		return cartes.length;
	}
	
	
	
	public Iterator<T> iterator(){
		return new Iterateur();
	}
	
	// ---------- CLASSE INTERNE ITERATEUR ----------
	
	private class Iterateur implements Iterator<T>{
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;

		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}

		@Override
		public T next() {
			verificationConcurrence();
			if (hasNext()) {
				T carte = (T) cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			// Décaler les cartes pour combler l'espace laissé par la carte supprimée
			for (int i = indiceIterateur-1; i < nbCartes-1; i++) {
				cartes[i] = cartes[i+1];
			}
			cartes[nbCartes-1] = null; // Supprime la carte
			nbCartes--;
			nextEffectue = false;
			indiceIterateur--;
			nombreOperations++;
			nombreOperationsReference++;
		}
		
		private void verificationConcurrence() {
			if (nombreOperations != nombreOperationsReference)
				throw new ConcurrentModificationException();
		}
		
	}
}
