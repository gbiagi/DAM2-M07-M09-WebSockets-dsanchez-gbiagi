package com.project;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
    private Rectangle[][] cards;
    private Text player1;
    private Text player2;
    private Text points1;
    private Text points2;

    private AppData() {
        cards = new Rectangle[4][4];
        serverClient = null;
        playerName = "Pepe";
        gameID = "131";
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

    public void setPlayer1(Text player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Text player2) {
        this.player2 = player2;
    }

    public void setPoints1(Text points1) {
        this.points1 = points1;
    }

    public void setPoints2(Text points2) {
        this.points2 = points2;
    }

    public Text getPlayer1() {
        return player1;
    }

    public Text getPlayer2() {
        return player2;
    }

    public Text getPoints1() {
        return points1;
    }

    public Text getPoints2() {
        return points2;
    }

    public Color getColor(String color, int row, int col) {

        return null;
    }

    public void fillCardMatrix(ArrayList<Rectangle> cardsArray) {
        for (Rectangle card : cardsArray) {
            int row = Integer.parseInt(card.getId().substring(4, 5));
            int col = Integer.parseInt(card.getId().substring(5, 6));
            cards[row][col] = card;
        }

    }

    public Rectangle getCard(int row, int col) {
        return cards[row][col];
    }

    public int[] getIndex(Rectangle card) {
        int[] index = new int[2];

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (cards[row][col].equals(card)) {
                    index[0] = row;
                    index[1] = col;
                    return index;
                }
            }
        }

        return null;
    }

    public String getPlayerStats() {
        String player = playerName + ": " + playerPoints + " " + turn + " " + gameID;

        return player;
    }
}