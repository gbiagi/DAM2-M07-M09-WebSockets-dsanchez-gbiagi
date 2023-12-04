package com.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.JSONObject;

public class ControllerRoom {
    @FXML
    private TextField inputPartida;

    @FXML
    Button buttonConectar, buttonCrearPartida;

    public void initialize() {
        buttonConectar.setOnAction(e -> joinRoom());

        buttonCrearPartida.setOnAction(e -> createRoom());
    }

    private void createRoom() {
        JSONObject obj = new JSONObject("{}");
        obj.put("type", "createGame");
        obj.put("name", AppData.getInstance().getPlayerName());
        AppData.getInstance().getServerClient().send(obj.toString());
        UtilsViews.setView("Game");
    }
    private void joinRoom() {
        JSONObject obj = new JSONObject("{}");
        obj.put("type", "joinGame");
        obj.put("gameID", inputPartida.getText());
        AppData.getInstance().setGameID(inputPartida.getText());
        obj.put("name", AppData.getInstance().getPlayerName());
        UtilsViews.setView("Game");
    }
}
