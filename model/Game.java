/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yannickhuggler.minesweeper.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;

/**
 *
 * @author yannickhuggler
 */
public class Game {

    private Cell[][] field;
    private int w, cols, rows;

    private static Game instance = null;

    private Game() {

    }
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void initGrid() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int rnd = (int) (Math.random() * 8);
                boolean bomb = false;
                if (rnd == 1) {
                    bomb = true;
                }
                field[i][j] = new Cell(i, j, w, bomb);
            }
        }

    }

    public void drawGrid(Pane pane) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                pane.getChildren().add(field[i][j].drawRect());
            }
        }

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                countNeighbours(i, j);
            }
        }
    }

    public void setFieldProperty(int cols, int rows, int w) {
        field = new Cell[cols][rows];
        this.w = w;
        this.cols = cols;
        this.rows = rows;
    }

    public void gameOver(Pane pane) {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (field[i][j].getBomb() == true) {
                    pane.getChildren().add(field[i][j].drawEllpise());

                }
            }
        }
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setContentText("You lost!");
        dialog.showAndWait();
    }

    public void reveal(int x, int y, Pane pane) {
        System.out.println(x + " " + y);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (x > field[i][j].getX() && x < field[i][j].getX() + w && y > field[i][j].getY() && y < field[i][j].getY() + w) {
                    if (field[i][j].getBomb() == true) {
                        pane.getChildren().add(field[i][j].drawEllpise());
                        gameOver(pane);
                    } else {
                        if (field[i][j].getNeighbourCount() > 0) {
                            field[i][j].setRevealed();
                            pane.getChildren().add(field[i][j].drawRevealedRect());
                            pane.getChildren().add(field[i][j].drawLabel());
                            System.out.println(i + " " + j);
                        } else {
                            field[i][j].setRevealed();
                            pane.getChildren().add(field[i][j].drawRevealedRect());
                            //floodFill(i, j, pane);

                        }

                    }
                }
            }
        }
    }

    public void countNeighbours(int colIndex, int rowIndex) {
        int total = 0;

        for (int i = colIndex - 1; i <= colIndex + 1; i++) {
            for (int j = rowIndex - 1; j <= rowIndex + 1; j++) {
                if (!(i < 0 || i >= cols || j < 0 || j >= rows)) {

                    if (field[i][j].getBomb() == true) {
                        total++;
                    }
                }
            }
        }
        field[colIndex][rowIndex].setNeighbourCount(total);
    }

    public void floodFill(int colIndex, int rowIndex, Pane pane) {
        if(field[colIndex][rowIndex].getNeighbourCount() == 0) {
            pane.getChildren().add(field[colIndex][rowIndex].drawRevealedRect());
            //floodFill(colIndex - 1, rowIndex);
        } else {
            return;
        }
        
        

    }

    public Cell[][] getField() {
        return field;
    }
}
