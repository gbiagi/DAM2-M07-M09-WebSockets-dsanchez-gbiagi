package com.project;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


import java.awt.*;

public class ControllerGame {

    @FXML
    private Rectangle card00, card01, card02, card03, card10, card11, card12, card13, card20, card21, card22, card23, card30, card31, card32, card33;

    public void initialize() {
        card00.setFill(Color.RED);
        card00.setOnMouseClicked(e -> clicked(card00));
        card01.setOnMouseClicked(e -> clicked(card01));
        card02.setOnMouseClicked(e -> clicked(card02));
        card03.setOnMouseClicked(e -> clicked(card03));
        card10.setOnMouseClicked(e -> clicked(card10));
        card11.setOnMouseClicked(e -> clicked(card11));
        card12.setOnMouseClicked(e -> clicked(card12));
        card13.setOnMouseClicked(e -> clicked(card13));
        card20.setOnMouseClicked(e -> clicked(card20));
        card21.setOnMouseClicked(e -> clicked(card21));
        card22.setOnMouseClicked(e -> clicked(card22));
        card23.setOnMouseClicked(e -> clicked(card23));
        card30.setOnMouseClicked(e -> clicked(card30));
        card31.setOnMouseClicked(e -> clicked(card31));
        card32.setOnMouseClicked(e -> clicked(card32));
        card33.setOnMouseClicked(e -> clicked(card33));

    }
    @FXML
    public void clicked(Rectangle card) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), card);

        rotateTransition.setByAngle(180); // Rotate by 180 degrees
        rotateTransition.setAxis(Rotate.Y_AXIS);
        rotateTransition.setAutoReverse(true);

        if (card.getFill() == Color.RED) {
            card.setFill(Color.BLUE); // Back side color
        } else {
            card.setFill(Color.RED);
        }

        // Play the animation
        rotateTransition.play();    }

}
