package cuentavocales.ejercicio01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CuentaVocalesApp {
    private static final String[] VOCALES = {"a", "e", "i", "o", "u"};
    private static final String ARCHIVO_SALIDA = "prueba.txt";
    private static final String RUTA_BIN = "C:\\Users\\Alumnado2DAM\\Desktop\\2DAM\\PSP\\Tema 1\\actividad107Procesos\\bin";

    public static void main(String[] args) {
        try { 	
            // Vaciar el archivo antes de comenzar el conteo
            vaciarArchivo(ARCHIVO_SALIDA);
            // Proceso para cada vocal
            for (String vocal : VOCALES) {
                ProcessBuilder pb = new ProcessBuilder("java", "-cp", RUTA_BIN, "cuentavocales.ejercicio01.CuentaLetraApp", vocal, ARCHIVO_SALIDA);
                Process process = pb.start();
                process.waitFor();
            }

            // Contar total de vocales y tiempo total
            long[] resultados = contarTotalVocales();
            escribirTotalVocales(resultados[0], resultados[1]);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    private static void vaciarArchivo(String archivoSalida) {
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            // Este constructor vacía el archivo al abrirlo
        } catch (IOException e) {
            System.err.println("Error al vaciar el archivo: " + e.getMessage());
        }
	}

	private static long[] contarTotalVocales() {
        int totalVocales = 0;
        long totalTime = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_SALIDA))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Sumar las ocurrencias y el tiempo
                String[] partes = linea.split("\\|");
                String[] conteoPartes = partes[0].split(" ");
                int contador = Integer.parseInt(conteoPartes[conteoPartes.length - 8]);
                totalVocales += contador;

                // Obtener el tiempo total
                String tiempoPart = partes[1].trim();
                String tiempoStr = tiempoPart.split(": ")[1].replace(" ms.", "");
                totalTime += Long.parseLong(tiempoStr);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo de salida: " + e.getMessage());
        }

        return new long[]{totalVocales, totalTime};
    }

    private static void escribirTotalVocales(long resultados, long totalTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_SALIDA, true))) {
            writer.write("Se han encontrado un total de: " + resultados + " | Tiempo total: " + totalTime + " ms.");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de salida: " + e.getMessage());
        }
    }
}
