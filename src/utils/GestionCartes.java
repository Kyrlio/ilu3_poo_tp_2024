package utils;

import java.util.ArrayList;
import java.util.Collections;
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
	
	


}
