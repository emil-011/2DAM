package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import models.Empleado;

public class UtilsFicheroArray {
	public static ArrayList<Empleado> leer_Empleados(String archivo) {
		ArrayList<Empleado> empleados = new ArrayList<>();
		try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
			String linea;
			lector.readLine();
			while ((linea = lector.readLine()) != null) {
				// se separa la linea en partes
				String[] datos = linea.replace("\"", "").split(",");
				// se crea el empleado con los datos obtenidos
				empleados.add(new Empleado(datos[0], Integer.valueOf(datos[1]), Integer.valueOf(datos[2])));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}

	// Método para escribir empleados en un archivo de texto
	public static void toArchivoTexto(ArrayList<Empleado> empleados, String archivo) {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
			for (Empleado empleado : empleados) {
				escritor.write(
						empleado.getEmpresa() + "," + empleado.getEdad() + "," + empleado.getNumeroEmpledados() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método para escribir empleados en un archivo binario (en orden inverso)
	public static void toArchivoBinarioInverso(ArrayList<Empleado> empleados, String archivo) {
		try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(archivo))) {
			for (int i = empleados.size() - 1; i >= 0; i--) {
				escritor.writeObject(empleados.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método para leer empleados desde un archivo binario
	public static void leer_Empleados_B(String archivo) {
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo))) {
			while (true) {
				try {
					Empleado empleado = (Empleado) lector.readObject();
					System.out.println(empleado);
					System.out.println("----------------------------");
				} catch (EOFException e) {
					break; // Fin del archivo
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
