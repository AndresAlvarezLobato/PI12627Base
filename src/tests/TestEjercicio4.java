package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio4;
import us.lsi.common.Pair;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio4 {

    private static Integer nMin = 100;
    private static Integer nMax = 3000; // Límite superior de entrada a
    private static Integer razon = 300;  // Incremento en cada medición
    private static Integer nIter = 50;  // Número de iteraciones por medición de tiempo
    private static Integer nIterWarmup = 10000; // Warmup para el JIT de Java
    
    

    public static void genData(Consumer<Integer> algorithm, String file) {
        Function<Integer, Long> f1 = GenData.time(algorithm);
        GenData.tiemposEjecucionAritmetica(f1, file, nMin, nMax, razon, nIter, nIterWarmup);
    }

    public static void show(Fit pl, String file, String label) {
        List<WeightedObservedPoint> data = DataFile.points(file);
        pl.fit(data);
        MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s", label, pl.getExpression()));
    }

    public static void showCombined() {
        MatPlotLib.showCombined("Tiempos",
                List.of(
                    "ficheros_generados/p1/recDouble.txt",
                    "ficheros_generados/p1/recBig.txt",
                    "ficheros_generados/p1/itDouble.txt",
                    "ficheros_generados/p1/itBig.txt"
                ), 
                List.of("Rec Double", "Rec Big", "It Double", "It Big"));
    }

    public static void main(String[] args) {
        // Generación de datos de tiempos para cada caso (a, b, c, d)
        genData(t -> Ejercicio4.funcRecDouble(t), "ficheros_generados/p1/recDouble.txt");
        genData(t -> Ejercicio4.funcRecBig(t), "ficheros_generados/p1/recBig.txt");
        genData(t -> Ejercicio4.funcItDouble(t), "ficheros_generados/p1/itDouble.txt");
        genData(t -> Ejercicio4.funcItBig(t), "ficheros_generados/p1/itBig.txt");
        
        // Mostrar las 4 gráficas individuales con su ajuste de curva (a, b, c, d)
        show(PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.))), "ficheros_generados/p1/recDouble.txt", "Rec Double");
        show(PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.))), "ficheros_generados/p1/recBig.txt", "Rec Big");
        show(PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.))), "ficheros_generados/p1/itDouble.txt", "It Double");
        show(PowerLog.of(List.of(Pair.of(2, 0.), Pair.of(3, 0.))), "ficheros_generados/p1/itBig.txt", "It Big");
        
        // Mostrar la gráfica combinada (apartado e)
        showCombined();
    }
}
