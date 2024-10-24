package ejercicio03.ficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SumaFicheros {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        // Lista de archivos a procesar
        String[] archivos = {
            "archivos/informatica.txt",
            "archivos/gerencia.txt",
            "archivos/contabilidad.txt",
            "archivos/comercio.txt",
            "archivos/rrhh.txt"
        };

        CompletableFuture<Integer>[] futures = new CompletableFuture[archivos.length];

        // Lanzamos un CompletableFuture para cada archivo
        for (int i = 0; i < archivos.length; i++) {
            final String archivo = archivos[i];
            futures[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    return sumarArchivo(archivo);
                } catch (IOException e) {
                    System.err.println("Error al leer el archivo " + archivo + ": " + e.getMessage());
                    return 0;
                }
            });
        }

        // Esperamos a que todos los procesos terminen y recogemos los resultados
        int totalSuma = 0;
        for (CompletableFuture<Integer> future : futures) {
            totalSuma += future.get();
        }

        // Imprimimos por pantalla el resultado
        System.out.println("La suma total es: " + totalSuma);
    }

    
    // Método que suma los valores de un archivo
    private static int sumarArchivo(String archivo) throws IOException {
        int suma = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                suma += Integer.parseInt(linea.trim());
            }
        } catch (IOException e) {
            throw new IOException("No se pudo leer el archivo: " + archivo, e);
        }
        return suma;
    }
}
