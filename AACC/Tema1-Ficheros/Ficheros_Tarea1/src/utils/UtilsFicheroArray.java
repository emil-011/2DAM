package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import models.Empleado;

public class UtilsFicheroArray {

	/**
	 * Metodo que lee un archivo y crea una lista de empleados apartir de el
	 * 
	 * @return Lista de empleados
	 */
	public static List<Empleado> empleadosToArray() {
		List<Empleado> empleados = new ArrayList<>();
		try (BufferedReader lector = new BufferedReader(new FileReader("arch\\empleados.txt"))) {
			lector.readLine();
			String linea;
			while ((linea = lector.readLine()) != null) {
				String[] campos = linea.split("\",\"");
				String empresa = campos[0].replace("\"", "");
				int edad = Integer.parseInt(campos[1].replace("\"", ""));
				int numEmpleados = Integer.parseInt(campos[2].replace("\"", ""));
				empleados.add(new Empleado(empresa, edad, numEmpleados));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return empleados;
	}

	/**
	 * Metodo que pasa una escribe en un archivo la lista de empleados.
	 * 
	 * @param empleados Lista de empleados
	 * @param archivo   Ruta del arhivo
	 */
	public static void toArchivoTexto(List<Empleado> empleados, String archivo) {
		try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
			escritor.write("EMPRESA,EDAD,NUM_EMPLEADOS");
			escritor.newLine();
			for (Empleado emp : empleados) {
				escritor.write("\"" + emp.getEmpresa() + "\",\"" + emp.getEdad() + "\",\"" + emp.getNum_empleados() + "\"");
				escritor.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que pasa una escribe en un archivo la lista de empleados en binario.
	 * @param empleados Lista de empleados
	 */
	public static void toArchivoBinarioInverso(List<Empleado> empleados) {
		try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream("arch\\empleados.bin"))) {
			for (int i = empleados.size() - 1; i >= 0; i--) {
				escritor.writeObject(empleados.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Lee los empleados de un archivo y los pasa a una lista (es igual que el 1) Deberia de escribirlo directamente?
	 * @param archivo Ruta del archivo que quieres leer
	 * @return Lista de empleados
	 */
	public static List<Empleado> leerEmpleados(String archivo) {
		List<Empleado> empleados = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea = reader.readLine();
			while ((linea = reader.readLine()) != null) {
				String[] campos = linea.split("\",\"");
				String empresa = campos[0].replace("\"", "");
				int edad = Integer.parseInt(campos[1].replace("\"", ""));
				int numEmpleados = Integer.parseInt(campos[2].replace("\"", ""));
				Empleado empleado = new Empleado(empresa, edad, numEmpleados);
				empleados.add(empleado);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return empleados;
	}

	/**
	 * Lee los empleados de un archivo binario y los pasa a una lista
	 * @param archivo
	 * @return Lista de empleados (objeto)
	 */
	public static List<Empleado> leerEmpleadosB(String archivo) {
		List<Empleado> empleados = new ArrayList<>();
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo))) {

			while (true) {
				Empleado empleado = (Empleado) lector.readObject();
				empleados.add(empleado);
			}

		} catch (Exception e) {

		}
		return empleados;

	}

}
