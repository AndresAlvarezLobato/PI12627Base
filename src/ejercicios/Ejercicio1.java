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
        // Caso base: se interrumpe la recursión cuando elem.a() >= varB
        if (elem.a() >= varB) {
            return ac;
        }

        // 1. Filtrado (elem.a() % 10 != 0) y actualización del acumulador
        String nuevoAc = ac;
        if (elem.a() % 10 != 0) {
            nuevoAc = ac.isEmpty() ? elem.s() : ac + "-" + elem.s();
        }

        // 2. Cálculo del siguiente estado (UnaryOperator nx)
        int siguienteA = elem.a() + 3;
        String siguienteS = (elem.a() % 2 == 0) ? elem.a() + "*" : elem.a() + "!";
        EnteroCadena siguienteElem = EnteroCadena.of(siguienteA, siguienteS);

        // 3. Llamada recursiva final
        return recFinalAux(siguienteElem, nuevoAc, varA, varB);
    }
	
	//extra: version recursiva NO final!
    
    public static String recNoFinal(Integer varA, Integer varB) {
        return recNoFinalAux(EnteroCadena.of(varA, "A"), varB);
    }

    private static String recNoFinalAux(EnteroCadena elem, Integer varB) {
        // 1. Caso Base: Se interrumpe la recursión cuando elem.a() >= varB
        if (elem.a() >= varB) {
            return "";
        }

        // 2. Cálculo del siguiente estado (UnaryOperator nx)
        int siguienteA = elem.a() + 3;
        String siguienteS = (elem.a() % 2 == 0) ? elem.a() + "*" : elem.a() + "!";
        EnteroCadena siguienteElem = EnteroCadena.of(siguienteA, siguienteS);

        // 3. Llamada recursiva hacia el RESTO de la secuencia (NO es la última operación)
        String resto = recNoFinalAux(siguienteElem, varB);

        // 4. COMBINACIÓN POST-RECURSIVA (Al regresar de la llamada):
        // Verificamos si el elemento actual pasa el filtro (% 10 != 0)
        if (elem.a() % 10 != 0) {
            if (resto.isEmpty()) {
                return elem.s();
            } else {
                return elem.s() + "-" + resto;
            }
        } else {
            // Si no cumple el filtro, ignoramos elem.s() y devolvemos solo lo que traiga el resto
            return resto;
        }
    }


}
