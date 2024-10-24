package utils;

/**
 * Clase producto
 */
public class Producto {

	private String nombre;
	private double precioUnitario;
	private boolean perecedero;

	/**
	 * Constructor producto
	 * @param nombre Nombre del producto
	 * @param precioUnitario Precio por unidad
	 * @param perecedero Si caduca el producto o no
	 */
	public Producto(String nombre, double precioUnitario, boolean perecedero) {
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

}
