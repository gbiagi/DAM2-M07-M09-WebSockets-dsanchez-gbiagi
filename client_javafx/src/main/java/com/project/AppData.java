package com.project;

import javafx.scene.paint.Color;

public class AppData {
    private static final AppData instance = new AppData();
    private ServerClient serverClient;
    private String playerName;
    private String gameID;;
    private String enemyName;
    private String enemyID;
    private Boolean turn;
    private int playerPoints;
    private int enemyPoints;

    private AppData() {
        serverClient = null;
        playerName = "";
        gameID = "";
        enemyName = "";
        enemyID = "";
        turn = false;
        playerPoints = 0;
        enemyPoints = 0;
    }

    public ServerClient getServerClient() {
        return serverClient;
    }

    public void setServerClient(ServerClient serverClient) {
        this.serverClient = serverClient;
    }
    public static AppData getInstance() {
        return instance;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public String getGameID() {
        return gameID;
    }
    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getEnemyID() {
        return enemyID;
    }

    public void setEnemyID(String enemyID) {
        this.enemyID = enemyID;
    }

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(int playerPoints) {
        this.playerPoints = playerPoints;
    }

    public int getEnemyPoints() {
        return enemyPoints;
    }

    public void setEnemyPoints(int enemyPoints) {
        this.enemyPoints = enemyPoints;
    }

    public Color getColor(String color, int row, int col) {

        return null;
    }
}
