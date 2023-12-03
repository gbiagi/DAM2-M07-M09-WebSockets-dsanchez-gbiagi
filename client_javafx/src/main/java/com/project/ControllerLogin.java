package com.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerLogin {
    @FXML
    private Button buttonConectar;

    @FXML
    private TextField inputServer, inputPort, inputName;

    public void initialize() {
        buttonConectar.setOnAction(e -> loginServer());
    }

    private void loginServer() {
        if (inputServer.getText().isEmpty()) {
            UtilsViews.setView("Game");
        }
    }
}
