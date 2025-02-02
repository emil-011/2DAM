package psp.ud03.act303.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase que representa un cliente que se conecta a un servidor.
 * Implementamos AutoCloseable para garantizar que los recursos se cierren correctamente.
 */
public class Client implements AutoCloseable {

    // Socket para la conexión con el servidor
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * Constructor de la clase Client.
     * Establece la conexión con el servidor en el host y puerto especificados.
     *
     * @param host Dirección del servidor
     * @param port Puerto en el que el servidor está escuchando.
     * @throws IOException Si ocurre un error al establecer la conexión.
     */
    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Envía un comando al servidor y devuelve la respuesta inicial.
     *
     * @param command Comando a enviar al servidor (por ejemplo, "list .", "show archivo.txt").
     * @return La primera línea de la respuesta del servidor ("OK" o "KO").
     * @throws IOException Si ocurre un error al enviar el comando o recibir la respuesta.
     */
    public String sendCommand(String command) throws IOException {
        out.println(command);
        return in.readLine();
    }

    /**
     * Lee el listado de un archivo despues de un "list"
     *
     * @return Un String con el listado de archivos y sus tamaños, separados por líneas
     * @throws IOException Si ocurre un error al leer el listado
     */
    public String readList() throws IOException {
        StringBuilder listing = new StringBuilder();
        String line;
        // Leer líneas hasta encontrar una línea vacía (fin del listado)
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            listing.append(line).append("\n");
        }
        // Devolver el listado completo
        return listing.toString();
    }

    /**
     * Lee el contenido de un archivo después de un "show"
     *
     * @return Un String con el contenido del archivo, línea por línea.
     * @throws IOException Si ocurre un error al leer el contenido del archivo.
     */
    public String readShow() throws IOException {
        StringBuilder content = new StringBuilder();
        // Leemos el número de líneas del archivo
        int numLines = Integer.parseInt(in.readLine());
        for (int i = 0; i < numLines; i++) {
            content.append(in.readLine()).append("\n");
        }
        // Devolvemos el contenido completo del archivo
        return content.toString();
    }

    @Override
    public void close() throws IOException {
        // Cerrar el BufferedReader si está inicializado
        if (in != null) in.close();
        // Cerrar el PrintWriter si está inicializado
        if (out != null) out.close();
        // Cerrar el socket si está inicializado
        if (socket != null) socket.close();
    }
}