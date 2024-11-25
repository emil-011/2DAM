package psp.unidad02.ejercicio02.utils;

import java.util.ArrayList;
import java.util.List;

public class CalculaPrimos extends Thread {
    private static int contadorHilos = 1;  // Contador estático para asignar número a los hilos
    private int inicio;
    private int fin;
    private List<Integer> primos;
    private int numeroHilo;  // Almacenará el número del hilo

    // Constructor para inicializar el rango
    public CalculaPrimos(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
        this.primos = new ArrayList<>();
        this.numeroHilo = contadorHilos++;  // Asigna un número único al hilo y aumenta el contador
    }

    // Método que verifica si un número es primo
    private boolean esPrimo(int numero) {
        if (numero <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0)
                return false;
        }
        return true;
    }

    // Método run que se ejecutará en el hilo
    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                primos.add(i);
                System.out.println("Thread " + numeroHilo + ". Nuevo primo: " + i);
            }
        }
    }

    // Getter para obtener la lista de números primos
    public List<Integer> getPrimos() {
        return primos;
    }
}
