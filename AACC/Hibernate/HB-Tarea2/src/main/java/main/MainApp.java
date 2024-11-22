package main;

import models.Existencias;
import models.Localidad;
import models.Titular;
import utils.RestauranteHibernateUtil;

public class MainApp {

	public static void main(String[] args) {
		// Insertamos localidad
//		Localidad localidad = new Localidad(31, "Rincon");
//		RestauranteHibernateUtil.insertarLocalidad(localidad);
//		Restaurante restaurante = new Restaurante("R001", "Restaurante La Casa", "1234567890", "Calle Falsa 123", 
//                Date.valueOf("2024-11-22"), "08:00 - 22:00", localidad);
//		RestauranteHibernateUtil.insertarRestaurante(restaurante);
//		Existencias hamburguesas = new Existencias("HAMB001", "Hamburguesas", 100, 5.99, "R001");
//		RestauranteHibernateUtil.insertarExistencias(hamburguesas);
		Titular titular = new Titular("11111111A","Pepin", "Calle Trebol", "R001");
		RestauranteHibernateUtil.insertarTitular(titular);
		

		
		
	}

}
