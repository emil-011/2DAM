package jframeApp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexion.Conexion;


import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SwingApp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Campos de texto
    private JTextField txtFilmId;
    private JTextField txtTitle;
    private JTextField txtReleaseYear;
    private JTextField txtLanguageId;
    private JTextField txtOriginalLanguageId;
    private JTextField txtRentalDuration;
    private JTextField txtRentalRate;
    private JTextField txtLength;
    private JTextField txtReplacementCost;
    private JTextField txtRating;
    private JLabel lblNewLabel;    
    private JTextArea txtAreaDescription2;
    private JTextArea txtAreaFeatures;
    
    private static final String SELECT_PELICULAS = "SELECT * FROM film";
    private ResultSet rs;
    private Connection connect;


    /**
     * Create the frame.
     */
    public SwingApp() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 856, 850);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblFilmId = new JLabel("Film ID:");
        lblFilmId.setBounds(15, 172, 67, 14);
        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(15, 210, 67, 14);
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(15, 248, 91, 14);
        JLabel lblReleaseYear = new JLabel("Release Year:");
        lblReleaseYear.setBounds(15, 350, 91, 14);
        JLabel lblLanguageId = new JLabel("Language ID:");
        lblLanguageId.setBounds(15, 388, 91, 14);
        JLabel lblOriginalLanguageId = new JLabel("Original Language ID:");
        lblOriginalLanguageId.setBounds(15, 426, 136, 14);
        JLabel lblRentalDuration = new JLabel("Rental Duration:");
        lblRentalDuration.setBounds(15, 459, 91, 14);
        JLabel lblRentalRate = new JLabel("Rental Rate:");
        lblRentalRate.setBounds(15, 500, 91, 14);
        JLabel lblLength = new JLabel("Length:");
        lblLength.setBounds(14, 525, 68, 14);
        JLabel lblReplacementCost = new JLabel("Replacement Cost:");
        lblReplacementCost.setBounds(15, 581, 112, 14);
        JLabel lblRating = new JLabel("Rating:");
        lblRating.setBounds(15, 624, 91, 14);
        JLabel lblSpecialFeatures = new JLabel("Special Features:");
        lblSpecialFeatures.setBounds(15, 649, 83, 14);

        // Campos de texto para cada campo de la película
        txtFilmId = new JTextField();
        txtFilmId.setBounds(137, 169, 300, 20);
        txtFilmId.setColumns(10);
        
        txtTitle = new JTextField();
        txtTitle.setBounds(137, 207, 300, 20);
        txtTitle.setColumns(10);
        
        txtReleaseYear = new JTextField();
        txtReleaseYear.setBounds(137, 347, 300, 20);
        txtReleaseYear.setColumns(10);
        
        txtLanguageId = new JTextField();
        txtLanguageId.setBounds(137, 385, 300, 20);
        txtLanguageId.setColumns(10);
        
        txtOriginalLanguageId = new JTextField();
        txtOriginalLanguageId.setBounds(147, 426, 300, 20);
        txtOriginalLanguageId.setColumns(10);
        
        txtRentalDuration = new JTextField();
        txtRentalDuration.setBounds(137, 451, 300, 20);
        txtRentalDuration.setColumns(10);
        
        txtRentalRate = new JTextField();
        txtRentalRate.setBounds(137, 497, 300, 20);
        txtRentalRate.setColumns(10);
        
        txtLength = new JTextField();
        txtLength.setBounds(137, 522, 300, 20);
        txtLength.setColumns(10);
        
        txtReplacementCost = new JTextField();
        txtReplacementCost.setBounds(137, 578, 300, 20);
        txtReplacementCost.setColumns(10);
        
        txtRating = new JTextField();
        txtRating.setBounds(137, 621, 300, 20);
        txtRating.setColumns(10);
        
        lblNewLabel = new JLabel("FILMS DB");
        lblNewLabel.setBounds(367, 55, 129, 36);
        lblNewLabel.setFont(new Font("Lexend", Font.BOLD, 24));
        
        JButton btnNewButton = new JButton("Primero");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarPrimeraPelicula();
        	}
        });
        btnNewButton.setBounds(567, 193, 129, 31);
        btnNewButton.setFont(new Font("Lexend", Font.BOLD, 18));
        btnNewButton.setBounds(567, 193, 129, 31);
        btnNewButton.setFont(new Font("Lexend", Font.BOLD, 18));
        
        JButton btnAnterior = new JButton("Anterior");
        btnAnterior.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarAnterior();
        	}
        });
        btnAnterior.setBounds(567, 300, 129, 31);
        btnAnterior.setFont(new Font("Lexend", Font.BOLD, 18));
        
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarSiguiente();
        	}
        });
        btnSiguiente.setBounds(567, 418, 129, 31);
        btnSiguiente.setFont(new Font("Lexend", Font.BOLD, 18));
        
        JButton btnUltimo = new JButton("Ultimo");
        btnUltimo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		mostrarUltimaPelicula();
        	}
        });
        btnUltimo.setBounds(567, 514, 129, 31);
        btnUltimo.setFont(new Font("Lexend", Font.BOLD, 18));
        contentPane.setLayout(null);
        contentPane.add(lblNewLabel);
        contentPane.add(btnNewButton);
        contentPane.add(btnAnterior);
        contentPane.add(btnSiguiente);
        contentPane.add(btnUltimo);
        contentPane.add(lblFilmId);
        contentPane.add(lblTitle);
        contentPane.add(lblDescription);
        contentPane.add(lblReleaseYear);
        contentPane.add(lblLanguageId);
        contentPane.add(lblOriginalLanguageId);
        contentPane.add(lblRentalDuration);
        contentPane.add(lblRentalRate);
        contentPane.add(lblLength);
        contentPane.add(lblReplacementCost);
        contentPane.add(lblRating);
        contentPane.add(lblSpecialFeatures);
        contentPane.add(txtFilmId);
        contentPane.add(txtTitle);
        contentPane.add(txtReleaseYear);
        contentPane.add(txtLanguageId);
        contentPane.add(txtOriginalLanguageId);
        contentPane.add(txtRentalDuration);
        contentPane.add(txtRentalRate);
        contentPane.add(txtLength);
        contentPane.add(txtReplacementCost);
        contentPane.add(txtRating);
        
        txtAreaDescription2 = new JTextArea();
        txtAreaDescription2.setBounds(137, 243, 300, 95);
        contentPane.add(txtAreaDescription2);
        txtAreaDescription2.setLineWrap(true);             
        txtAreaDescription2.setWrapStyleWord(true);        
        
        txtAreaFeatures = new JTextArea();
        txtAreaFeatures.setWrapStyleWord(true);
        txtAreaFeatures.setLineWrap(true);
        txtAreaFeatures.setBounds(137, 648, 300, 31);
        contentPane.add(txtAreaFeatures);
        
        cargarPeliculas();
    }
    

    
    private void cargarPeliculas() {
	    try {
	        connect = Conexion.conectar(); // Open the connection
	        PreparedStatement st = connect.prepareStatement(SELECT_PELICULAS, ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            mostrarDatos();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	    private void mostrarDatos() {
	        try {
	            // Cargar datos del ResultSet a los campos de texto
	            txtFilmId.setText(rs.getString("film_id"));
	            txtTitle.setText(rs.getString("title"));
	            txtAreaDescription2.setText(rs.getString("description"));
	            txtReleaseYear.setText(rs.getString("release_year"));
	            txtLanguageId.setText(rs.getString("language_id"));
	            txtOriginalLanguageId.setText(rs.getString("original_language_id"));
	            txtRentalDuration.setText(rs.getString("rental_duration"));
	            txtRentalRate.setText(rs.getString("rental_rate"));
	            txtLength.setText(rs.getString("length"));
	            txtReplacementCost.setText(rs.getString("replacement_cost"));
	            txtRating.setText(rs.getString("rating"));
	            txtAreaFeatures.setText(rs.getString("special_features"));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void mostrarPrimeraPelicula() {
	        try {
	            if (rs != null) {
	                rs.first();
	                mostrarDatos();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	    private void mostrarSiguiente() {
	        try {
	            if (rs.next()) {
	                mostrarDatos();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void mostrarAnterior() {
	        try {
	            if (rs.previous()) {
	                mostrarDatos();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void mostrarUltimaPelicula() {
	        try {
	            rs.last();
	            mostrarDatos();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

