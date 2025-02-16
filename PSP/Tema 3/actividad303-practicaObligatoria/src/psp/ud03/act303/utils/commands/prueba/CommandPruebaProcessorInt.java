package psp.ud03.act303.utils.commands.prueba;

/**
 * Interfaz que especifica los métodos que deben implementarse para manejar
 * las solicitudes de los clientes, como listar archivos, mostrar el contenido
 * de un archivo, eliminar archivos o carpetas, crear directorios, copiar, mover y renombrar.
 * 
 * Cada método corresponde a un comando específico que el cliente puede enviar
 * al servidor.
 * 
 * @author Emi
 * @version v1.1
 */
public interface CommandPruebaProcessorInt {

    /**
     * Solicita la creación de un nuevo directorio en la ruta especificada.
     * 
     * El servidor devuelve:
     * - "OK" si el directorio se creó correctamente.
     * - "KO" si el directorio ya existe, la ruta es inválida o no se pudo crear.
     * 
     * @param path Ruta absoluta o relativa del directorio a crear.
     */
    public void commandCreateDirectory(String path);

    /**
     * Solicita copiar un archivo o directorio desde una ruta de origen a una ruta de destino.
     * 
     * El servidor devuelve:
     * - "OK" si la copia fue exitosa.
     * - "KO" si la operación falla (archivo/directorio inexistente, permisos insuficientes, etc.).
     * 
     * @param sourcePath Ruta absoluta o relativa del archivo o directorio a copiar.
     * @param targetPath Ruta absoluta o relativa de destino donde se copiará el archivo o directorio.
     */
    public void commandCopy(String sourcePath, String targetPath);

    /**
     * Solicita mover un archivo o directorio desde una ruta de origen a una ruta de destino.
     * 
     * El servidor devuelve:
     * - "OK" si el movimiento fue exitoso.
     * - "KO" si la operación falla (archivo/directorio inexistente, permisos insuficientes, etc.).
     * 
     * @param sourcePath Ruta absoluta o relativa del archivo o directorio a mover.
     * @param targetPath Ruta absoluta o relativa de destino donde se moverá el archivo o directorio.
     */
    public void commandMove(String sourcePath, String targetPath);

    /**
     * Solicita renombrar un archivo o directorio en la ruta especificada.
     * 
     * El servidor devuelve:
     * - "OK" si el renombrado fue exitoso.
     * - "KO" si la operación falla (archivo/directorio inexistente, permisos insuficientes, etc.).
     * 
     * @param path Ruta absoluta o relativa del archivo o directorio a renombrar.
     * @param newName Nuevo nombre para el archivo o directorio.
     */
    public void commandRename(String path, String newName);
}