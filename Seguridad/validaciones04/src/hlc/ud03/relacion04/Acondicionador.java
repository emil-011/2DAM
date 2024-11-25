package hlc.ud03.relacion04;

public class Acondicionador {

    public static String acondicionaElementoHtml(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return "";
        }

        // Reemplaza caracteres especiales por sus entidades HTML
        return cadena
                .replace("&", "&amp;")   // Ampersand
                .replace("<", "&lt;")    // Menor que
                .replace(">", "&gt;")    // Mayor que
                .replace("\"", "&#34;") // Comillas dobles
                .replace("'", "&#39;");  // Comillas simples
    }

    public static String acondicionaAtributoHtml(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return "";
        }

        // Reemplaza caracteres especiales por sus entidades HTML
        return cadena
                .replace("&", "&amp;")   // Ampersand
                .replace("\"", "&#34;") // Comillas dobles
                .replace("'", "&#39;"); // Comillas simples
    }

	public static String acondicionaContenidoCss(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String acondicionaUrl(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String acondicionaLiteralSql(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
