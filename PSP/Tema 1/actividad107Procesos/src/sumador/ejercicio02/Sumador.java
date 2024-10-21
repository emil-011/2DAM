package sumador.ejercicio02;

public class Sumador {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Se requieren dos parámetros: <Entero> <Entero>");
            return;
        }

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        int suma = 0;
        for (int i = num1; i <= num2; i++) {
            suma += i;
        }

        // Imprime el resultado en la salida estándar
        System.out.println("La suma de " + num1 + " a " + num2 + " es: " + suma);
    }
}
