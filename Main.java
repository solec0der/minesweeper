/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yannickhuggler.minesweeper;

import com.yannickhuggler.minesweeper.model.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author yannickhuggler
 */
public class Main extends Application {

    private Game instance;
    
    @Override
    public void start(Stage primaryStage) {
        this.instance = Game.getInstance();
        Pane pane = new Pane();
        instance.setFieldProperty(10, 10, 30);
        
        instance.initGrid();
        instance.drawGrid(pane);
        
        pane.setOnMouseClicked(e -> {
            mouseClicked(e, pane);
        });

        Scene scene = new Scene(pane, 300, 300);

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
