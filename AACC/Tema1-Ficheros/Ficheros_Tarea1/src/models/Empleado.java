package models;

import java.io.Serializable;

public class Empleado implements Serializable {
	
	private String empresa;
	private int edad;
	private int num_empleados;

	/**
	 * Empleado
	 * @param empresa Nombre de la empresa
	 * @param edad Edad del empleado
	 * @param num_empleados Numero de personas que tiene a cargo el empleado
	 */
	public Empleado(String empresa, int edad, int num_empleados) {
		this.empresa = empresa;
		this.edad = edad;
		this.num_empleados = num_empleados;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getNum_empleados() {
		return num_empleados;
	}

	public void setNum_empleados(int num_empleados) {
		this.num_empleados = num_empleados;
	}

	@Override
	public String toString() {
		return "Empresa: " + empresa + 
				"\nEdad " + edad +
				"\nNúmero Empleados" + num_empleados;
				
	}
	
	
	
}
