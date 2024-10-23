package models;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Empleado implements Serializable{
	private String empresa;
	private int edad;
	private int numeroEmpledados;
	public String getEmpresa() {
		return empresa;
	}
	public int getEdad() {
		return edad;
	}
	public int getNumeroEmpledados() {
		return numeroEmpledados;
	}
	@Override
	public String toString() {
		return "Empresa: " + empresa + "\nEdad: " + edad + "\nNumero Empledados: " + numeroEmpledados;
	}
	public Empleado(String empresa, int edad, int numeroEmpledados) {
		super();
		this.empresa = empresa;
		this.edad = edad;
		this.numeroEmpledados = numeroEmpledados;
	}
	
	

}
