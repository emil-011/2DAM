package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class VentanaPrincipalController {

    @FXML
    private Button btnBoton;

    @FXML
    private Label lblTexto;

    @FXML
    void btnPressed(ActionEvent event) {
    	lblTexto.setText("Hola Mundo");
    }

}
