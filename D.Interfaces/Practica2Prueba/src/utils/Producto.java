package utils;

public class Producto {

	private String nombre;
	private double precioUnitario;
	private boolean perecedero;

	public Producto(String nombre, double precioUnitario, boolean perecedero) {
		super();
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.perecedero = perecedero;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public String isPerecedero() {
		return perecedero ? "Si" : "No";
	}

	@Override
	public String toString() {
		return "📦 Producto: " + nombre.toUpperCase() + " " + " | Precio Unitario: " + precioUnitario
				+ " € | Perecedero: " + isPerecedero();
	}

}
