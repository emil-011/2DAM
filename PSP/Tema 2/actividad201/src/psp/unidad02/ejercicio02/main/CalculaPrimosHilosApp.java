package psp.unidad02.ejercicio02.main;

import psp.unidad02.ejercicio02.utils.CalculaPrimos;

import java.util.ArrayList;
import java.util.List;

public class CalculaPrimosHilosApp {
    public static void main(String[] args) {
        // Validar que se pasen los parámetros necesarios
        if (args.length < 2) {
            System.out.println("Error: Debes proporcionar el rango como parámetros.");
            System.out.println("Uso: java CalculaPrimosHilosApp <inicio> <fin>");
            return;
        }

        try {
            // Convertir los argumentos a enteros
            int inicio = Integer.parseInt(args[0]);
            int fin = Integer.parseInt(args[1]);

            // Validar que el rango sea correcto
            if (inicio > fin) {
                System.out.println("Error: El inicio del rango no puede ser mayor que el fin.");
                return;
            }

            // Obtener el número de procesadores disponibles
            int numProcesadores = Runtime.getRuntime().availableProcessors();
            System.out.println("Número de procesadores disponibles: " + numProcesadores);

            // Dividir el rango entre los hilos
            int rangoTotal = fin - inicio + 1;
            int rangoPorHilo = rangoTotal / numProcesadores;
            int rangoRestante = rangoTotal % numProcesadores;

            // Lista para almacenar los hilos
            List<CalculaPrimos> hilos = new ArrayList<>();

            // Crear los hilos con los rangos divididos
            int inicioHilo = inicio;
            for (int i = 0; i < numProcesadores; i++) {
                int finHilo = inicioHilo + rangoPorHilo - 1;
                if (i < rangoRestante) {
                    finHilo++; // Añadir una unidad a los hilos que cubren el resto
                }

                // Crear el hilo con el rango asignado
                CalculaPrimos hilo = new CalculaPrimos(inicioHilo, finHilo);
                hilos.add(hilo);

                // Ajustar el inicio del siguiente hilo
                inicioHilo = finHilo + 1;
            }

            // Iniciar todos los hilos
            for (CalculaPrimos hilo : hilos) {
                hilo.start();
            }

            // Esperar a que todos los hilos terminen
            for (CalculaPrimos hilo : hilos) {
                hilo.join();
            }

            // Recoger los resultados de todos los hilos
            List<Integer> primosTotales = new ArrayList<>();
            for (CalculaPrimos hilo : hilos) {
                primosTotales.addAll(hilo.getPrimos());
            }

            // Mostrar los resultados
            System.out.println("Números primos en el rango " + inicio + " - " + fin + ": " + primosTotales);

        } catch (NumberFormatException e) {
            System.out.println("Error: Los parámetros deben ser números enteros.");
        } catch (InterruptedException e) {
            System.out.println("Error: Se produjo una interrupción en el cálculo.");
            e.printStackTrace();
        }
    }
}
