package com.project;

public class Player {
    private String id;
    private String name;
    private String enemyID;
    private String enemyName;
    private int points;
    private boolean turn;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        points = 0;
        turn = false;
    }

    public void setTurn() {
        if (turn) {
            turn = false;
        } else {
            turn = true;
        }
    }

    public void setEnemyID(String enemyID) {
        this.enemyID = enemyID;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public void sumPoints() {
        points++;
    }

    public String getId() {
        return id;
    }

    public String getEnemyID() {
        return enemyID;
    }

    public String getName() {
        return name;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public boolean getTurn() {
        return turn;
    }

    public int getPoints() {
        return points;
    }
}
