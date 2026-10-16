package ejercicios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ejercicios.Ejercicio3.Tupla;


public class Ejercicio3 {
	public static record Tupla(Integer a, Integer b, Integer c) {
		public static Tupla of(Integer a, Integer b, Integer c) {
			return new Tupla(a, b, c);
		}
	}
	
	public static Set<Integer> ejercicio3RecursivoSinMemoria(Integer a, Integer b, Integer c) { 
		Set<Integer> res = new HashSet<>();
		if (a<=5 || b<=3 || c<=2) {
			res.add(a+2);
			res.add(b*3);
			res.add(c);
		}
		else if (a<=8 || b<=6 || c<=4) {
			res.add(a);
			res.add(b+2);
			res.add(c*3);
		}
		else {
			Set<Integer> set1 = ejercicio3RecursivoSinMemoria(a/2,b-3,c/4);
			Set<Integer> set2 = ejercicio3RecursivoSinMemoria(a-3,b/3,c-2);
			res.addAll(set1);
			res.addAll(set2);
		}
		return res;
	}
	
	public static Set<Integer> ejercicio3RecursivoConMemoria(Integer a, Integer b, Integer c) {
		return ejercicio3ConMemAux(a,b,c, new HashMap<>());
	}
	private static Set<Integer> ejercicio3ConMemAux(Integer a, Integer b, Integer c, Map<Tupla, Set<Integer>> m){
		Tupla key = Tupla.of(a, b, c);
		if (m.containsKey(key)){
			return m.get(key);
		}
		Set<Integer> res = new HashSet<>();
		if (a<=5 || b<=3 || c<=2) {
			res.add(a+2);
			res.add(b*3);
			res.add(c);
		}
		else if (a<=8 || b<=6 || c<=4) {
			res.add(a);
			res.add(b+2);
			res.add(c*3);
		}
		else {
			Set<Integer> set1 = ejercicio3ConMemAux(a/2,b-3,c/4,m);
			Set<Integer> set2 = ejercicio3ConMemAux(a-3,b/3,c-2,m);
			res.addAll(set1);
			res.addAll(set2);
		}
		m.put(key, res);
		return res;
	}
	
	public static Set<Integer> ejercicio3Iterativo(Integer a, Integer b, Integer c) {
		Map<Tupla, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i <= a; i++) {
	        for (int j = 0; j <= b; j++) {
	            for (int k = 0; k <= c; k++) {
	                Set<Integer> res = new HashSet<>();
	                if (i <= 5 || j <= 3 || k <= 2) {
	                    res.add(i + 2);
	                    res.add(j * 3);
	                    res.add(k);
	                } else if (i <= 8 || j <= 6 || k <= 4) {
	                    res.add(i);
	                    res.add(j + 2);
	                    res.add(k * 3);
	                } else {
	                    Set<Integer> s1 = map.get(Tupla.of(i / 2, j - 3, k / 4));
	                    Set<Integer> s2 = map.get(Tupla.of(i - 3, j / 3, k - 2));           
	                    res.addAll(s1);
	                    res.addAll(s2);
	                }          
	                map.put(Tupla.of(i, j, k), res);             
	            }
	        }
		}
		return map.get(Tupla.of(a, b, c));	
	}
}
