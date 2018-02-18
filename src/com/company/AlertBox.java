package com.company;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message){
        Button closeButton = new Button("Close");
        Stage alertWindow = new Stage();
        Label label = new Label(message);
        VBox layout = new VBox(10);
        Scene scene = new Scene(layout);

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(250);

        closeButton.setOnAction(e -> alertWindow.close());

        layout.setPadding(new Insets(10, 20, 10, 20));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, closeButton);

        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
}
