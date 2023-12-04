package com.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URI;
import java.net.URISyntaxException;

public class ControllerLogin {
    @FXML
    private Button buttonConectar;

    @FXML
    private TextField inputServer, inputPort, inputName;

    public void initialize() {
        buttonConectar.setOnAction(e -> loginServer());
    }

    private void loginServer() {
        AppData appData = AppData.getInstance();
        String server = inputServer.getText();
        String port = inputPort.getText();
        String name = inputName.getText();
        appData.setServerClient(connectWebSocket(server, port));
        appData.getServerClient().connect();
        appData.setPlayerName(name);
        UtilsViews.setView("SetMatch");
    }

    private ServerClient connectWebSocket(String ip, String port) {
        URI uri;
        try {
            uri = new URI("ws://" + ip + ":" + port + "/websocket");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return new ServerClient(uri);
    }
}
