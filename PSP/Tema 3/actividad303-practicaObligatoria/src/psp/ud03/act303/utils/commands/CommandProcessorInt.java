package psp.ud03.act303.utils.commands;

/**
 * Interfaz que especifica los métodos que deben implementarse para manejar
 * las solicitudes de los clientes, como listar archivos, mostrar el contenido
 * de un archivo y eliminar archivos o carpetas.
 * 
 * Cada método corresponde a un comando específico que el cliente puede enviar
 * al servidor.
 * 
 * @author Emi
 * @version v1.0
 */
public interface CommandProcessorInt {
	
	/**
	 * Solicita el listado de archivos en la ruta especificada en el servidor.
	 * 
	 * El servidor devuelve:
	 * - "OK" seguido de una lista de archivos con sus tamaños en KiB si la operación es exitosa.
	 * - "KO" si la ruta no existe, no es un directorio o hay un error al acceder.
	 * 
	 * @param path Ruta absoluta o relativa a la carpeta en el servidor.
	 */
	public void commandList(String path);
	
	/**
	 * Solicita el contenido de un archivo de texto en la ruta especificada.
	 * 
	 * El servidor devuelve:
	 * - "OK" seguido del número de líneas del archivo y su contenido si la operación es exitosa.
	 * - "KO" si no se puede acceder al archivo.
	 * 
	 * @param path Ruta absoluta o relativa al archivo de texto en el servidor.
	 */
	public void commandShow(String path);
	
	/**
	 * Solicita la eliminación de un archivo o carpeta en la ruta especificada.
	 * 
	 * El servidor devuelve:
	 * - "OK" si la eliminación fue exitosa.
	 * - "KO" si la operación falla por cualquier motivo (archivo inexistente, permisos insuficientes, etc.).
	 * 
	 * @param path Ruta absoluta o relativa del archivo o carpeta a eliminar en el servidor.
	 */
	public void commandDelete(String path);
}
