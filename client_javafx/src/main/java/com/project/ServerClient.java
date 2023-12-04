package com.project;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class ServerClient extends WebSocketClient {
    public ServerClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {

    }

    @Override
    public void onMessage(String message) {
        AppData appData = AppData.getInstance();
        try {
            JSONObject objRequest = new JSONObject(message);
            String type = objRequest.getString("type");
            switch (type) {
                case "gameCreated" -> {
                    appData.setGameID(objRequest.getString("gameID"));
                    ControllerGame.pantallaInicio(appData.getPlayer1(), appData.getPlayer2(), appData.getPoints2());
                }
                case "gameStatus" -> {
                    appData.setEnemyID(objRequest.getString("enemyID"));
                    appData.setEnemyName(objRequest.getString("enemyName"));
                    appData.setTurn(objRequest.getBoolean("turn"));
                    appData.setPlayerPoints(objRequest.getInt("playerPoints"));
                    appData.setEnemyPoints(objRequest.getInt("enemyPoints"));
                    System.out.println(appData.getPlayerStats());
                    ControllerGame.pintarDatosPartida(appData.getPlayer1(), appData.getPoints1(), appData.getPlayer2(),
                            appData.getPoints2());
                }
                case "flipCard" -> {
                    String cardColor = objRequest.getString("card");
                    int row = objRequest.getInt("row");
                    int col = objRequest.getInt("col");
                    ControllerGame.cambioColor(appData.getCard(row, col), cardColor);
                }
                case "wrongCards" -> {
                    int row0 = objRequest.getInt("row0");
                    int col0 = objRequest.getInt("col0");
                    int row1 = objRequest.getInt("row1");
                    int col1 = objRequest.getInt("col1");
                    ControllerGame.cambioColor(appData.getCard(row0, col0), "silver");
                    ControllerGame.cambioColor(appData.getCard(row1, col1), "silver");
                }
                default -> System.out.println("Error: type not found");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }

}
