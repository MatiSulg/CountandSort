package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;


public class Main extends Application{


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label readFileDesc = new Label("Read file:");
        TextField readFilePath = new TextField("No file selected");
        Button readFileBtn = new Button("Choose file");
        Label writeFileDesc = new Label("Save File:");
        TextField writeFilePath = new TextField("No file selected");
        Button writeFileBtn = new Button("Create/Choose file");
        Label desc = new Label("1. Click \"Choose file\" Button \nand choose file you want to read \ntext from.\n" +
                "2. Click \"Create/Choose file\" button,\n either choose the text file you want\n to save the " +
                "results or choose the\n folder and type in \"File name\" below\n to create new text file. \n" +
                "3. Click \"Count and Sort\" Button\n to make program work the magic.");
        Button countAndSortBtn = new Button("Count and Sort");
        Button closeBtn = new Button("Close program");
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 440, 270);
        VBox pathFields = new VBox(10);

        // Button action for file read path search
        readFileBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if(selectedFile == null){
                readFilePath.setText("No file selected");
            } else {
                readFilePath.setText(selectedFile.getAbsolutePath());
            }
        });

        // Button action for file write path search
        writeFileBtn.setOnAction(e -> {
            AlertBox.display("Alert","This program overwrites text file." +
                    " Don't choose any important text file.");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Text dox(*.txt),","*.txt"));
            File selectedFile = fileChooser.showSaveDialog(primaryStage);

            if(selectedFile == null) {
                writeFilePath.setText("No file selected");
                countAndSortBtn.setDisable(true);
            } else if(!selectedFile.getName().contains(".txt")){
                writeFilePath.setText(selectedFile.getAbsolutePath() + ".txt");
                countAndSortBtn.setDisable(false);
            } else {
                writeFilePath.setText(selectedFile.getAbsolutePath());
                countAndSortBtn.setDisable(false);
            }
        });
        //

        // Button action to run functions, to work it's magic
        countAndSortBtn.setDisable(true);
        countAndSortBtn.setOnAction(e ->{
            List list = Tools.frequencySorter(Tools.wordCounter(DataReadWrite.dataReader(readFilePath.getText())));
            PrintWriter output = DataReadWrite.dataWriter(writeFilePath.getText());
            Tools.rowWriter(list, output);
            output.close();
            AlertBox.display("Message","Successful sort and count");
        });

        // Button action for closing program
        closeBtn.setOnAction(e -> primaryStage.close());

        //VBox
        pathFields.getChildren().addAll(readFileDesc,readFilePath,readFileBtn,writeFileDesc,writeFilePath,writeFileBtn);
        pathFields.setPadding(new Insets(0, 20, 40, 0));

        //GridPane
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 20, 10, 20));
        root.add(pathFields, 0, 0);
        root.add(countAndSortBtn, 0, 2);
        root.add(closeBtn, 1, 2);
        root.add(desc, 1, 0);

        //Stage
        primaryStage.setMinHeight(310);
        primaryStage.setMinWidth(460);
        primaryStage.setTitle("Count and Sort");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
