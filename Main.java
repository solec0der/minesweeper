/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yannickhuggler.minesweeper;

import com.yannickhuggler.minesweeper.model.Game;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author yannickhuggler
 */
public class Main extends Application {

    private Game instance;
    private Pane pane;
    
    @Override
    public void start(Stage primaryStage) {
        TextInputDialog dialog = null;
        Optional<String> result;
        
        dialog = new TextInputDialog("Enter columns here...");
        dialog.setHeaderText("Define grid size");
        dialog.setContentText("How many columns?");
        result = dialog.showAndWait();
        
        int cols = Integer.parseInt(result.get());
        
        dialog = new TextInputDialog("Enter rows here...");
        dialog.setHeaderText("Define grid size");
        dialog.setContentText("How many rows?");
        result = dialog.showAndWait();
        
        int rows = Integer.parseInt(result.get());
        
        dialog = new TextInputDialog("Enter width here...");
        dialog.setHeaderText("Define grid size");
        dialog.setContentText("How wide should every cell be?");
        result = dialog.showAndWait();
        
        int w = Integer.parseInt(result.get());
        
        this.instance = Game.getInstance();
        
        
        Pane pane = new Pane();
        this.pane = pane;
        instance.setFieldProperty(cols, rows, w);
        instance.initGrid();
        instance.drawGrid(pane);
        
        
        pane.setOnMouseClicked(e -> {
            mouseClicked(e, pane);
        });

        Scene scene = new Scene(pane, cols * w, rows * w);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void mouseClicked(MouseEvent e, Pane pane) {
        
        int x = (int) e.getX();
        int y = (int) e.getY();

        instance.reveal(x, y, pane);
        
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
