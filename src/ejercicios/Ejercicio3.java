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
		return null;
	}
	
	public static Set<Integer> ejercicio3RecursivoConMemoria(Integer a, Integer b, Integer c) {
		return null;	
	}
	
	public static Set<Integer> ejercicio3Iterativo(Integer a, Integer b, Integer c) {
		return null;
	}
}
