package com.cgvsu.protocurvefxapp;

//import Logic.LagrangeInterpolation;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ProtoCurveController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;
    @FXML
    private Button myButton;

    ArrayList<Point2D> points = new ArrayList<Point2D>();

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        canvas.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY -> handlePrimaryClick(canvas.getGraphicsContext2D(), event);
            }
        });
        myButton.setOnAction(event -> handleButtonClick());
    }

    private void handlePrimaryClick(GraphicsContext graphicsContext, MouseEvent event) {
        final Point2D clickPoint = new Point2D(event.getX(), event.getY());

        final int POINT_RADIUS = 3;
        graphicsContext.fillOval(
                clickPoint.getX() - POINT_RADIUS, clickPoint.getY() - POINT_RADIUS,
                2 * POINT_RADIUS, 2 * POINT_RADIUS);

        points.add(clickPoint);
    }
    private void drawLagrangeCurve(GraphicsContext graphicsContext, int i) {
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setLineWidth(2.0);

        double step = 0.0001; // Шаг
        if (points.get(i).getX() <= points.get(i+1).getX()) {
            for (double x = points.get(i).getX(); x <= points.get(i+1).getX(); x += step) {
                double y = lagrangeInterpolation(x);
                graphicsContext.strokeRect(x, y, 1, 1); // отрисовываем точками
            }
        } else {
            for (double x = points.get(i+1).getX(); x <= points.get(i).getX(); x += step) {
                double y = lagrangeInterpolation(x);
                graphicsContext.strokeRect(x, y, 1, 1);
            }
        }
    }

    private double lagrangeInterpolation(double x) {
        double result = 0.0;

        for (int i = 0; i < points.size(); i++) {
            double term = points.get(i).getY();
            for (int j = 0; j < points.size(); j++) {
                if (j != i) {
                    term = term * (x - points.get(j).getX()) / (points.get(i).getX() - points.get(j).getX());
                }
            }
            result += term;
        }
        return result;
    }

    public void handleButtonClick() {
        canvas.getGraphicsContext2D().clearRect(0, 0, 1920, 1800); //ХЗ КАК ПРАВИЛЬНО, СТИРАЮ ВСЁЁЁЁЁЁ
                if (points.size() > 1) {
                    for (int i = 0; i<points.size()-1; i++){
                        drawLagrangeCurve(canvas.getGraphicsContext2D(), i);
                    }
        }

    }
}