import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoggerSwingApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUsername;
    private JPasswordField passwordField;

    // Ruta de la base de datos SQLite
    private static final String URL = "jdbc:sqlite:db/loggerDB.db";

    // Consulta SQL para verificar el login
    private static final String SELECT_USER = "SELECT * FROM users WHERE username = ? AND password = ?";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoggerSwingApp frame = new LoggerSwingApp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoggerSwingApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(800, 400, 362, 299);
        setTitle("Login");
        
        // Cargar el icono de la ventana
        try {
            BufferedImage iconImage = ImageIO.read(new File("images/icon.png"));
            setIconImage(iconImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear un panel personalizado con imagen de fondo
        contentPane = new JPanel() {
            private BufferedImage fondoImagen;

            {
                try {
                    // Cargar la imagen de fondo
                    fondoImagen = ImageIO.read(new File("images/mapamundi.png")); // Cambia la ruta a tu imagen
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondoImagen != null) {
                    g.drawImage(fondoImagen, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        contentPane.setLayout(null);
        
        String fuente = "Monospaced";
        

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(new Color(152, 148, 61));
        lblUsername.setFont(new Font(fuente, Font.BOLD, 26));
        lblUsername.setBounds(33, 33, 143, 32);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(new Color(152, 148, 61));
        lblPassword.setFont(new Font(fuente, Font.BOLD, 26));
        lblPassword.setBounds(33, 88, 143, 32);
        contentPane.add(lblPassword);
        
        
        textUsername = new JTextField();
        textUsername.setHorizontalAlignment(SwingConstants.CENTER);
        textUsername.setForeground(new Color(152, 148, 61));
        textUsername.setFont(new Font(fuente, Font.BOLD | Font.ITALIC, 18));
        textUsername.setBackground(new Color(3, 49, 118));
        textUsername.setBounds(173, 38, 150, 25);
        textUsername.setOpaque(false);
        contentPane.add(textUsername);
        textUsername.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setForeground(new Color(152, 148, 61));
        passwordField.setOpaque(false);
        passwordField.setFont(new Font(fuente, Font.BOLD, 24));
        passwordField.setBackground(new Color(3, 49, 118));
        passwordField.setBounds(173, 93, 150, 25);
        contentPane.add(passwordField);

        JCheckBox chckbxRemember = new JCheckBox("Remember Me");
        chckbxRemember.setForeground(new Color(152, 148, 61));
        chckbxRemember.setBackground(new Color(0,0,0));
        chckbxRemember.setOpaque(false);
        chckbxRemember.setFont(new Font(fuente, Font.BOLD, 16));
        chckbxRemember.setBounds(180, 142, 143, 23);
        contentPane.add(chckbxRemember);

        JButton btnLogin = new JButton("LOGIN");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (checkLogin(textUsername.getText(), new String(passwordField.getPassword()))) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed. Invalid username or password.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnLogin.setForeground(new Color(255, 255, 255));
        btnLogin.setBackground(new Color(3, 49, 118));
        btnLogin.setFont(new Font(fuente, Font.BOLD, 18));
        btnLogin.setBounds(48, 183, 115, 30);
        contentPane.add(btnLogin);

        JButton btnRegister = new JButton("REGISTER");
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFont(new Font(fuente, Font.BOLD, 18));
        btnRegister.setBackground(new Color(3, 49, 118));
        btnRegister.setBounds(193, 183, 130, 30);
        contentPane.add(btnRegister);

        setContentPane(contentPane);
    }

    /**
     * Método para verificar las credenciales de login.
     */
    private boolean checkLogin(String username, String password) throws SQLException {
        Connection conection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Conectamos a la base de datos SQLite
            conection = DriverManager.getConnection(URL);
            
            // Preparamos la sentencia SQL
            pstmt = conection.prepareStatement(SELECT_USER);
            
            // Establecemos los valores de los (? )
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // Ejecutar la consulta
            rs = pstmt.executeQuery();
            
            // Si hay un resultado, las credenciales son correctas
            return rs.next();
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conection != null) conection.close();
        }
    }
}
