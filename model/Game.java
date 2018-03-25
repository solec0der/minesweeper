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
    }

    public void setFieldProperty(int cols, int rows, int w) {
        field = new Cell[cols][rows];
        this.w = w;
    }

    public void reveal(int x, int y, Pane pane) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (field[i][j].getX() > x && field[i][j].getX() < x + w && field[i][j].getY() > y && field[i][j].getY() < y + w) {
                    if (field[i][j].getBomb() == true) {
                        pane.getChildren().add(field[i][j].drawEllpise());
                    } else {
                        field[i - 1][j - 1].setRevealed();
                        pane.getChildren().add(field[i - 1][j - 1].drawRevealedRect());
                    }
                }
            }
        }
    }

    

    public Cell[][] getField() {
        return field;
    }
}
