package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	
	public static <T> T extraireV1(List<T> list){
		if (list.isEmpty()) {
			throw new IllegalArgumentException("Liste vide!");
		}
		
		Random random = new Random();
		int n = random.nextInt(list.size());
		T element = list.get(n);
		list.remove(n);
		return element;
	}
	
	public static <T> T extraireV2(List<T> list) {
		Random random = new Random();
		ListIterator<T> iter = list.listIterator();
		int n = random.nextInt(list.size());
		
		while (iter.nextIndex() < n) {
			iter.next();
		}
		T element = iter.next();
		iter.remove();
		return element;
	}
	
	public static <T> List<T> melanger(List<T> list) {
		List<T> listeMelangee = new ArrayList<>();
		while(!list.isEmpty()) {
			listeMelangee.add(extraireV1(list));
		}
		
		return listeMelangee;
	}
	
	public static <T> boolean verifierMelange(List<T> list1, List<T> list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		
		for (T element : list1) {
			int occurs1 = Collections.frequency(list1, element);
			int occurs2 = Collections.frequency(list2, element);
			
			if (occurs1 != occurs2) return false;
		}
		
		return true;
	}
	
	public static <T> List<T> rassembler(List<T> list) {
		if (!list.isEmpty()) {
			List<T> newList = new ArrayList<T>();
			for (T element : list) {
				if (!newList.contains(element)) {
					// La nouvelle liste n'a pas l'élément de list
					for (int i = 0; i < Collections.frequency(list, element); i++) {
						// Ajoute à la nouvelle liste tous les éléments identiques à la suite
						newList.add(element);
					}
				}
			}
			return newList;
		}
		return list;	
	}
	
	
	public static <T> boolean verifierRassemblement(List<T> list) {
		if (list == null || list.isEmpty()) return true;
		
		ListIterator<T> iter1 = list.listIterator();
		T prev = iter1.next();
				
		while (iter1.hasNext()) {
			T curr = iter1.next();
			
			if (!curr.equals(prev)) {
				ListIterator<T> iter2 = list.listIterator(iter1.nextIndex());
				
				while (iter2.hasNext()) {
					if (iter2.next().equals(prev)) return false;
				}
				prev = curr;
			}
		}
		
		return true;
	}


}
