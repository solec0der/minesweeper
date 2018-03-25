/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yannickhuggler.minesweeper.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 *
 * @author yannickhuggler
 */
public class Cell {

    private int i, j, w, x, y;
    private boolean revealed = false;
    private boolean bomb;
    private int neighbourCount;

    public Cell(int i, int j, int w, boolean bomb) {
        this.i = i;
        this.j = j;
        this.w = w;
        this.x = i * w;
        this.y = j * w;
        this.bomb = bomb;
    }

    public Rectangle drawRect() {
        Rectangle rect = new Rectangle(x, y, w, w);

        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);

        rect.setVisible(true);
        return rect;
    }

    public Rectangle drawRevealedRect() {
        Rectangle rect = new Rectangle(x, y, w, w);

        rect.setFill(Color.GRAY);
        rect.setStroke(Color.BLACK);

        rect.setVisible(true);
        return rect;
    }

    public Ellipse drawEllpise() {

        Ellipse ellipse = new Ellipse(x + w * 0.5, y + w * 0.5, w * 0.25, w * 0.25);

        ellipse.setFill(Color.GRAY);
        ellipse.setStroke(Color.BLACK);

        ellipse.setVisible(true);

        return ellipse;
    }

    public Label drawLabel() {
        Label label = new Label(Integer.toString(neighbourCount));
        label.setLayoutX(x + w * 0.2);
        label.setLayoutY(y - w * 0.1);
        label.setTextFill(Color.WHITE);
        label.setPrefSize(w, w);
        label.setFont(new Font("Arial", w));
        return label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setRevealed() {
        System.out.println(neighbourCount);
        revealed = true;

    }

    public void setNeighbourCount(int count) {
        this.neighbourCount = count;
    }
    
    public boolean getBomb() {
        return bomb;
    }
    
    public int getNeighbourCount() {
        return neighbourCount;
    }
}
