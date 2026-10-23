package ejercicios;

import java.math.BigInteger;

public class Ejercicio4 {
	
	public static Double funcRecDouble(Integer a) { 
        if (a<=10) {
        	return 5.;
        }else {
        	return Math.sqrt(3*a) * funcRecDouble(a-2);
        }
	}
	
	public static BigInteger funcRecBig(Integer a) {
        if (a<=10) {
        	return BigInteger.valueOf(5);
        }else {
        	BigInteger term = BigInteger.valueOf(3L * a).sqrt();
        	return term.multiply(funcRecBig(a-2));
        }
	}
	
	public static Double funcItDouble(Integer a) {
        int base = a;
        while (base >= 10) {
            base -= 2;
        }
        Double res = 5.;
        for (int i = base + 2; i <= a; i += 2) {
            res = Math.sqrt(3 * i) * res;
        }
        return res;
    }
	
	public static BigInteger funcItBig(Integer a) {
        int base = a;
        while (base >= 10) {
            base -= 2;
        }
        BigInteger res = BigInteger.valueOf(5);
        for (int i = base + 2; i <= a; i += 2) {
            BigInteger term = BigInteger.valueOf(3L * i).sqrt();
            res = term.multiply(res);
        }
        return res;
    }

}
