/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yannickhuggler.minesweeper.model;

import javafx.scene.layout.Pane;

/**
 *
 * @author yannickhuggler
 */
public class Game {

    private Cell[][] field;
    private int w;

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
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int rnd = (int) (Math.random() * 2);
                boolean bomb = false;
                if (rnd == 1) {
                    bomb = true;
                }
                field[i][j] = new Cell(i, j, w, bomb);
            }
        }

    }

    public void drawGrid(Pane pane) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                pane.getChildren().add(field[i][j].drawRect());
            }
        }
        
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                countNeighbours(i, j);
            }
        }
    }

    public void setFieldProperty(int cols, int rows, int w) {
        field = new Cell[cols][rows];
        this.w = w;
    }

    public void reveal(int x, int y, Pane pane) {
        System.out.println(x + " " + y);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (x > field[i][j].getX() && x < field[i][j].getX() + w && y > field[i][j].getY() && y < field[i][j].getY() + w) {
                    if (field[i][j].getBomb() == true) {
                        pane.getChildren().add(field[i][j].drawEllpise());
                    } else {
                        if(field[i][j].getNeighbourCount() > 0) {
                            field[i][j].setRevealed();
                            pane.getChildren().add(field[i][j].drawRevealedRect());
                            pane.getChildren().add(field[i][j].drawLabel());
                            System.out.println(i + " " + j);
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
                if(!(i < 0 || i >= 10 || j < 0 || j >= 10)) {
                    
                    if(field[i][j].getBomb() == true) {
                        total++;
                    }
                }
            }
        }
        field[colIndex][rowIndex].setNeighbourCount(total);
    }

    public Cell[][] getField() {
        return field;
    }
}
