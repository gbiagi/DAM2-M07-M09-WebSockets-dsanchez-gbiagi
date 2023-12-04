package com.project;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.json.JSONObject;

import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.HashMap;

public class ControllerGame {

    @FXML
    private Rectangle card00, card01, card02, card03, card10, card11, card12, card13, card20, card21, card22, card23,
            card30, card31, card32, card33;
    @FXML
    private Text player1, player2;
    // Create a map to keep track of color assignments
    // HashMap<Color, Integer> colorCounter = new HashMap<>();
    HashMap<Rectangle, Color> cardsColors = new HashMap<>();

    public void initialize() {
        // Creamos la lista de cartas
        ArrayList<Rectangle> cards = new ArrayList<>() {
            {
                {
                    add(card00);
                    add(card01);
                    add(card02);
                    add(card03);
                    add(card10);
                    add(card11);
                    add(card12);
                    add(card13);
                    add(card20);
                    add(card21);
                    add(card22);
                    add(card23);
                    add(card30);
                    add(card31);
                    add(card32);
                    add(card33);
                }
            }
        };
        // Pintamos las cartas de gris
        for (Rectangle card : cards) {
            card.setFill(Color.SILVER);
            card.setOnMouseClicked(e -> clicked(card));
        }
        AppData.getInstance().fillCardMatrix(cards);

    }

    // Metodo para voltear la carta y ver su color asignado
    @FXML
    public void clicked(Rectangle card) {
        if (AppData.getInstance().getTurn() & (card.getFill().equals(Color.SILVER))) {
            String id = card.getId();
            int row = Integer.parseInt(id.substring(4, 5));
            int col = Integer.parseInt(id.substring(5, 6));
            String gameID = AppData.getInstance().getGameID();
            JSONObject objRequest = new JSONObject("{}");
            objRequest.put("type", "flipCard");
            objRequest.put("gameID", gameID);
            objRequest.put("row", row);
            objRequest.put("col", col);
            AppData.getInstance().getServerClient().send(objRequest.toString());
        }

    }

    public static void cambioColor(Rectangle card, String color) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), card);
        rotateTransition.setByAngle(180); // Rotate by 180 degrees
        rotateTransition.setAxis(Rotate.Y_AXIS);
        switch (color) {
            case "blue" -> card.setFill(Color.BLUE);
            case "green" -> card.setFill(Color.GREEN);
            case "yellow" -> card.setFill(Color.YELLOW);
            case "pink" -> card.setFill(Color.PINK);
            case "orange" -> card.setFill(Color.ORANGE);
            case "red" -> card.setFill(Color.RED);
            case "brown" -> card.setFill(Color.BROWN);
            case "purple" -> card.setFill(Color.PURPLE);
            case "silver" -> card.setFill(Color.SILVER);

            default -> System.out.println("Error");
        }
        // Rotar la carta con animacion
        rotateTransition.play();
    }
}
