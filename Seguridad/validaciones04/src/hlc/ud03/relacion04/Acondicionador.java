package hlc.ud03.relacion04;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Acondicionador {

	/**
	 * 
	 * @param cadena
	 * @return
	 */
	public static String acondicionaElementoHtml(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return "";
		}
		return cadena.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&#34;")
				.replace("'", "&#39;");
	}

	/**
	 * 
	 * @param cadena
	 * @return
	 */
	public static String acondicionaAtributoHtml(String cadena) {
		if (cadena == null || cadena.isEmpty()) {
			return "";
		}
		return cadena.replace("&", "&amp;").replace("<", "&lt;").replace("\"", "&#34;").replace("'", "&#39;");
	}

	/**
	 * 
	 * @param cadena
	 * @return
	 */
	public static String acondicionaContenidoCss(String cadena) {
		char[] cadenaSpliteada = cadena.toCharArray();
		StringBuilder cadenaEntera = new StringBuilder();

		for (int i = 0; i < cadenaSpliteada.length; i++) {
			char c = cadenaSpliteada[i];

			if (Character.isDigit(c)) {
				cadenaEntera.append(" ");
			}

			cadenaEntera.append(c);
		}

		cadena = cadenaEntera.toString();

		return cadena.replace("\\", "\\5c").replace("\"", "\\22").replace("'", "\\27").replace("<", "\\3c")
				.replace(">", "\\3e").replace("&", "\\26").replace("(", "\\28").replace(")", "\\29")
				.replace("/", "\\2f");
	}

	/**
	 * 
	 * @param cadena
	 * @return
	 */
	public static String acondicionaUrl(String cadena) {
		try {
			cadena = URLEncoder.encode(cadena, "UTF-8").replace("%40", "@").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return cadena;
	}

	/**
	 * 
	 * @param cadena
	 * @return
	 */
	public static String acondicionaLiteralSql(String cadena) {
		System.out.println(cadena);
		cadena = cadena.replace("\\", "\\\\").replace("'", "\\'").replace("\"", "\\\"").replace("%", "\\%").replace("_",
				"\\_");
		System.out.println(cadena);

		return cadena;
	}

}
