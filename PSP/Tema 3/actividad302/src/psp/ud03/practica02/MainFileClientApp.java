// psp.ud03.practica02.MainFileClientApp
package psp.ud03.practica02;

import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainFileClientApp {

    private final String serverAddress;
    private final int serverPort;

    private MainFileClientApp(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }
    
    public static void main(String[] args) {
        // Verificamos los parámetros
        String serverAddress = args.length > 0 ? args[0] : "localhost";
        int serverPort = args.length > 1 ? Integer.parseInt(args[1]) : 4321;

        MainFileClientApp app = new MainFileClientApp(serverAddress, serverPort);
        app.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la ruta del archivo: ");
        String filePath = sc.nextLine();

        try (Socket socket = new Socket(serverAddress, serverPort);
             OutputStream out = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            // Enviamos la solicitud al servidor
            out.write((filePath + "\n\r").getBytes());
            out.flush();

            // Leemos la respuesta del servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String status = reader.readLine();

            if (status.equals("OK")) {
                // El archivo existe, lo guardamos localmente
                String fileName = Paths.get(filePath).getFileName().toString();
                saveFile(in, fileName);
                System.out.println("Archivo descargado: " + fileName);
            } else {
                // El archivo no existe o no se puede acceder
                System.out.println("Error: El archivo no existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
        sc.close();
    }

    // Metodo para guardar el archivo
    private void saveFile(InputStream in, String fileName) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }
        }
    }
}