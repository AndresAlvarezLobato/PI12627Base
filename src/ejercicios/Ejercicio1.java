package ejercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Ejercicio1 {
	
	public static void tests(List<ParEnteros> ls) {
		ls.forEach(t -> {
			System.out.println();
			System.out.println("1) Solucion Funcional: " + funcional(t.a(), t.b()));
			System.out.println("2) Solucion Iterativo: " + iterativo(t.a(), t.b()));
			System.out.println("3) Solucion Rec. Final: " + recFinal(t.a(), t.b()));
		});
		
	}
	
	
	public static String funcional(Integer varA, Integer varB) {
	    UnaryOperator<EnteroCadena> nx = elem -> {
	        return EnteroCadena.of(
	            elem.a() + 3,
	            elem.a() % 2 == 0
	                ? elem.a() + "*"
	                : elem.a() + "!"
	        );
	    };

	    return Stream.iterate(
	            EnteroCadena.of(varA, "A"),
	            elem -> elem.a() < varB,
	            nx
	        )
	        .filter(elem -> elem.a() % 10 != 0)
	        .map(elem -> elem.s())
	        .collect(Collectors.joining("-"));
	}


	
	public static String iterativo(Integer varA, Integer varB) {
	    EnteroCadena elem = EnteroCadena.of(varA, "A");
	    String ac = ""; // Acumulador de tipo String
	    
	    while (elem.a() < varB) {
	        if (elem.a() % 10 != 0) {
	            if (ac.isEmpty()) {
	                ac = elem.s(); // Si es el primero, no ponemos guion delante
	            } else {
	                ac = ac + "-" + elem.s(); // Si ya hay elementos, concatenamos con guion
	            }
	        }
	        // Actualización del elemento
	        Integer nuevoA = elem.a() + 3;
	        String nuevaS = elem.a() % 2 == 0 ? elem.a() + "*" : elem.a() + "!";
	        elem = EnteroCadena.of(nuevoA, nuevaS);
	    }
	    return ac;
	}
	
	public static String recFinal(Integer varA, Integer varB) {
		return recFinalAux(EnteroCadena.of(varA, "A"), "", varA, varB);
			
	}
	
	public static String recFinalAux(EnteroCadena elem, String ac, Integer varA, Integer varB) {
		
	}
	
	
	
	
	
	


}
