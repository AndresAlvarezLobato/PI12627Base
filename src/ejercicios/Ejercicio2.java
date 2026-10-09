package ejercicios;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio2 {
	public static List<String> ejercicio2RecursivoNoFinal(Integer a, String s){
		List<String> res = new ArrayList<>();
		if (a <= 2 || s.length() <= 2) {
			res.add(a.toString() + s);
		} else if (a%2==0) {
			res = ejercicio2RecursivoNoFinal(a/2, s.substring(0, s.length() - 2));
			res.add(a.toString());
		} else {
			res = ejercicio2RecursivoNoFinal(a/3, s.substring(0, s.length() - 1));
			res.add((a.toString() + s.substring(0, a%s.length())));
		}
		return res;
	}
	
		public static List<String> ejercicio2Iterativo(Integer a, String s){
		List<String> ac = new ArrayList<>();
		while (!(a <= 2 || s.length() <= 2)) {
			if (a%2==0) {
				ac.add(a.toString());
				a/=2;
				s = s.substring(0, s.length() - 2);
			} else {
				ac.add(a.toString() + s.substring(0, a%s.length()));
				a/=3;
				s = s.substring(0, s.length() - 1);
			}
		}
		ac.add(a.toString() + s);
		return ac;
	}
	
	public static List<String> ejercicio2RecursivoFinal(Integer a, String s) {
		return null;
	}

	public static List<String> ejercicio2NotacionFuncional(Integer a, String s){
		return null;
	}
	
}
