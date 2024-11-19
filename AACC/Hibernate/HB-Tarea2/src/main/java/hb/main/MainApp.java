package hb.main;

import hb.utils.HibernateUtil;

public class MainApp {
	
	private final static String RUTA = "Alumnado_nuevo.txt";

	public static void main(String[] args) {
		HibernateUtil.anyadirUsuario(RUTA);
	}

}
